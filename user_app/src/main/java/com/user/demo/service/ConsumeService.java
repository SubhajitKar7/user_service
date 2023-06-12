package com.user.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService 
{
	@KafkaListener(topics="MovieBookingApp", groupId="movieUser",autoStartup = "false")
	public void consumeFromTopic(String message)
	{
		System.out.println("Consumer message: "+ message);
	}

}
