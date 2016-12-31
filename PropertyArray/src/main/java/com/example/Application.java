package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@RestController
class WithEnvCtrl {

	@Autowired
	private Environment env;

	@RequestMapping(value = "/with_env", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public String test() {

		System.err.println(env.getProperty("this.is.array[0]"));
		System.err.println(env.getProperty("this.is.array", List.class));
		System.err.println(env.getProperty("this.is.array", String[].class));

		return env.getProperty("this.is.array[0]");
	}
}

@RestController
class WithValueAnnotation {

	@Value("${this.is.array[0]}")
	private String first;
	@Value("${this.is.array}")
	private List<String> list;
	@Value("${this.is.array}")
	private String[] array;

	@RequestMapping(value = "/with_value_annotation", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public String test() {

		System.err.println(first);
		System.err.println(list);
		System.err.println(array);

		return first;
	}
}