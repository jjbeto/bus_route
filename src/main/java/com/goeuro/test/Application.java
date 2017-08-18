package com.goeuro.test;

import com.goeuro.test.exception.DataSourceFileChangedException;
import com.goeuro.test.service.FileDatasourceAbstractService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

@SpringBootApplication
public class Application extends FileDatasourceAbstractService {

	private static ConfigurableApplicationContext app = null;
	public static String[] ARGUMENTS = {};

	/**
	 * Only starts the app if a file is informed (def file is available in case of lack of data for tests)
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ARGUMENTS = args;
		if (ARGUMENTS == null || ARGUMENTS.length == 0) {
			URL resource = Application.class.getClassLoader().getResource("test_data_1.txt");
			ARGUMENTS = new String[1];
			ARGUMENTS[0] = resource.getFile();
		}
		loadApplication(ARGUMENTS[0]);
	}

	private static void loadApplication(String dataFile) {
		if (loadFileData(dataFile)) {
			runApplication();
		} else {
			throw new IllegalArgumentException("Data file provided not found!");
		}
	}

	public static void runApplication() {
		app = SpringApplication.run(Application.class, ARGUMENTS);
	}

	public static void terminateApplication() {
		SpringApplication.exit(app, new DataSourceFileChangedException());
	}

}
