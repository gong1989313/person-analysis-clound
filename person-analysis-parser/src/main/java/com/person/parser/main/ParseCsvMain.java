package com.person.parser.main;

import com.person.parser.core.ParseCsvService;
import com.person.parser.kafka.KafkaProducerService;

public class ParseCsvMain {
	public static void main(String[] args) {
		new KafkaProducerService();
		new ParseCsvService("E:\\文档资料\\工作资料\\数据\\2000W\\1800w-2000w.csv").parseCSVFile(34);
	}
}
