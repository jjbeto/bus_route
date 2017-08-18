package com.goeuro.test.exception;

import org.springframework.boot.ExitCodeGenerator;

public class DataSourceFileChangedException extends RuntimeException implements ExitCodeGenerator {

    @Override
    public int getExitCode() {
        return 0;
    }

}
