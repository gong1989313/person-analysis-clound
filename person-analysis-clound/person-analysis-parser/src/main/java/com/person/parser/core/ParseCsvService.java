package com.person.parser.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.person.parser.enums.StatusTypeEnum;
import com.person.parser.persistent.PersistentService;
import com.person.parser.utils.ParseUtil;

public class ParseCsvService {
	private static Logger logger = Logger.getLogger(ParseCsvService.class);
	
	private BufferedReader br = null;
	private PersistentService persistentService = new PersistentService();

	public ParseCsvService(String path) {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void parseCSVFile(int fieldNum) {
		int count = 0, size;//, index = 1;

		String[] parms = new String[fieldNum - 1];
		String[] bakParms = null;
		size = parms.length - 1;
		StringBuilder temp = new StringBuilder();
		StringBuilder quotesFieldBak = new StringBuilder();
		StatusTypeEnum status = StatusTypeEnum.NewFieldStart;
		int ch = 0, pos = -1;
		boolean quotesText = false, isQutesFlag = false;
		try {
			while ((ch = br.read()) != -1) {
				if (ch == ',') {
					if (status == StatusTypeEnum.QuotesField) {
						temp.append((char) ch);
					} else {
						if (status == StatusTypeEnum.NewFieldStart) {
							parms[count] = temp.toString();
							temp.delete(0, temp.length());
							count++;
						}
						if (status == StatusTypeEnum.NonQuotesField) {
							parms[count] = temp.toString();
							temp.delete(0, temp.length());
							count++;
							status = StatusTypeEnum.NewFieldStart;
						}
					}
				} else {
					if (ch == '\"' || ch == '\'') {
						if (status == StatusTypeEnum.QuotesField) {
							temp.append((char) ch);
							status = StatusTypeEnum.NonQuotesField;
							quotesText = true;
							continue;
						}
						if (status == StatusTypeEnum.NewFieldStart) {
							status = StatusTypeEnum.QuotesField;
							if (!isQutesFlag) {
								// bakup parms
								bakParms = ParseUtil.bakArray(parms, count);
								pos = count;
							}
							isQutesFlag = true;
						}
						if (status == StatusTypeEnum.NonQuotesField) {
							temp.append((char) ch);
							if (quotesText) {
								status = StatusTypeEnum.NonQuotesField;
								quotesText = false;
							} else {
								status = StatusTypeEnum.QuotesField;
							}
						}
					}
					if (ch == '\r' || ch == '\n') {
						if (count == size) {
							parms[count] = temp.toString();
							temp.delete(0, temp.length());
							quotesFieldBak.delete(0, quotesFieldBak.length());
							isQutesFlag = false;
							pos = -1;
							count++;
						}
						if (isQutesFlag && count < size) {
							parms = ParseUtil.bakQuotesFieldProcess(parms, pos, quotesFieldBak);
							quotesFieldBak.delete(0, quotesFieldBak.length());
							isQutesFlag = false;
							pos = -1;
							count = size + 1;
						}
					}
					if (count == size + 1) {
						if (isQutesFlag && ch != '\r') {
							while ((ch = br.read()) != '\r') {
								quotesFieldBak.append((char) ch);
							}
							parms = ParseUtil.specialCharProcess(parms, bakParms, pos, quotesFieldBak);
							quotesFieldBak.delete(0, quotesFieldBak.length());
							isQutesFlag = false;
							pos = -1;
						}
						persistentService.save2Queue(parms);
						temp.delete(0, temp.length());
						count = 0;
						status = StatusTypeEnum.NewFieldStart;
						//index++;
					}
					if (status == StatusTypeEnum.NewFieldStart) {
						if (ch == '\r' || ch == '\n') {
							continue;
						}
						temp.append((char) ch);
					}
					if (status == StatusTypeEnum.QuotesField) {
						temp.append((char) ch);
					}
				}
				if (isQutesFlag) {
					quotesFieldBak.append((char) ch);
				}
			}
			persistentService.save2Queue(parms);
		} catch (IOException e) {
			logger.error("parse cause exception.");
			e.printStackTrace();
			System.exit(1);
		}
	}

}
