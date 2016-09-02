package com.lug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class UavManageSystemApplication {

	private static Logger log = LoggerFactory.getLogger(UavManageSystemApplication.class);

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(UavManageSystemApplication.class);
		app.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> aClass, PrintStream printStream) {
				printStream.print("\n\n>>>>>This is the uav manage system banner!\n\n");
			}
		});
//		app.setBannerMode(Banner.Mode.OFF);
//		app.setLogStartupInfo(false);
		app.addListeners(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent applicationEvent) {

			}
		});
		app.run(args);
//		SpringApplication.run(UavManageSystemApplication.class, args);
	}

	@Value("${server.ip}")
	String serverIp;

	@Bean
	CommandLineRunner values(){
		return args -> {
			log.info(" > The Server IP is: " + serverIp);
		};
	}
}
