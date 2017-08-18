package com.goeuro.test.service;

import com.goeuro.test.Application;

import java.io.File;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Responsable to continuous load data from a file and check for modifications
 */
public abstract class FileDatasourceAbstractService {

    public final static boolean loadFileData(String path) {
        File file = new File(path);
        if (file.exists()) {
            Runnable checkExecution = () -> { checkFileData(file); };
            new Thread(checkExecution).start();
            return true;
        } else {
            return false;
        }
    }

    private final static void checkFileData(File file) {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = file.toPath().getParent();
            path.register(watcher, ENTRY_MODIFY, ENTRY_DELETE, ENTRY_CREATE);
            while (true) {
                WatchKey key;
                try {
                    key = watcher.poll(25, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    return;
                }
                if (key == null) {
                    Thread.yield();
                    continue;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    } else if (isFileEligeble(kind) && filename.toString().equals(file.getName())) {
                        onFileDataChange();
                    }
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            }
        } catch (Throwable e) {
            // cannot load watcher!
        }
    }

    public final static boolean isFileEligeble(WatchEvent.Kind<?> kind) {
        return kind == ENTRY_MODIFY || kind == ENTRY_DELETE || kind == ENTRY_CREATE;
    }

    public static void onFileDataChange() {
        Application.terminateApplication();
        Application.runApplication();
    }

}
