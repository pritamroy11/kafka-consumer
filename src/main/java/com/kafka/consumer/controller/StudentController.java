package com.kafka.consumer.controller;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.kafka.consumer.entity.Student;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks.Many;

@Controller
public class StudentController {
	
	@Autowired
	Many<Student> studentSink;
	
	@QueryMapping
	public Mono<String> welcome() {
		return Mono.just("Welcome to GraphQL Kafka Consumer Service!");
	}
	
	@SubscriptionMapping
	public Publisher<Student> studentData() {
		return studentSink.asFlux();
	}

}
