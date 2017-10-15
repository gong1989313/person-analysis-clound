package com.person.parser.main;

import java.io.IOException;

import com.person.parser.core.ParseCsvService;

public class ParseCsvMain {
	public static void main(String[] args) {
		try {
			ParseCsvService pcs = new ParseCsvService("D:\\temp\\1800w-2000w.csv");
			pcs.parseCSVFile(34);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
