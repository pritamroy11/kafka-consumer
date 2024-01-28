package com.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.entity.Student;

import reactor.core.publisher.Sinks.Many;

@Service
public class KafkaConsumerService {
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	Many<Student> studentSink;
	
	@KafkaListener(topics = {"graphql-topic"})
	public void consumeStudentData(ConsumerRecord<String, String> data) throws JsonMappingException, JsonProcessingException {
		Student student = mapper.readValue(data.value(), Student.class);
		studentSink.tryEmitNext(student);
	}

}
