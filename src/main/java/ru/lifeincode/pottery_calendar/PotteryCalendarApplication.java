package ru.lifeincode.pottery_calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.lifeincode.pottery_calendar")
public class PotteryCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotteryCalendarApplication.class, args);
	}

}

