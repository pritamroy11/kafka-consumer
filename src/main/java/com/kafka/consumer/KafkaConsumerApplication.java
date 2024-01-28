package com.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kafka.consumer.entity.Student;

import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	
	@Bean
	public Many<Student> studentSinkConfig() {
		return Sinks.many().multicast().directBestEffort();
	}

}
