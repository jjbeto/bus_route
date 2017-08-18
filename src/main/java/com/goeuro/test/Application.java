package com.goeuro.test;

import com.goeuro.test.exception.DataSourceFileChangedException;
import com.goeuro.test.service.FileDatasourceAbstractService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

@SpringBootApplication
public class Application extends FileDatasourceAbstractService {

	private static ConfigurableApplicationContext app = null;
	private static String[] arguments = {};

	/**
	 * Only starts the app if a file is informed (def file is available in case of lack of data for tests)
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		arguments = args;
		if (arguments == null || arguments.length == 0) {
			URL resource = Application.class.getClassLoader().getResource("test_data_1.txt");
			loadApplication(resource.getFile());
		} else {
			loadApplication(arguments[0]);
		}
	}

	private static void loadApplication(String dataFile) {
		if (loadFileData(dataFile)) {
			runApplication();
		} else {
			throw new IllegalArgumentException("Data file provided not found!");
		}
	}

	public static void runApplication() {
		app = SpringApplication.run(Application.class, arguments);
	}

	public static void terminateApplication() {
		SpringApplication.exit(app, new DataSourceFileChangedException());
	}

}
