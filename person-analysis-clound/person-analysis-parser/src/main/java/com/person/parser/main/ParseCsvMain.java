package com.person.parser.main;

import com.person.parser.core.ParseCsvService;
import com.person.parser.kafka.KafkaProducerService;

public class ParseCsvMain {
	public static void main(String[] args) {
		new KafkaProducerService();
		new ParseCsvService("D:\\temp\\1800w-2000w.csv").parseCSVFile(34);
	}
}
