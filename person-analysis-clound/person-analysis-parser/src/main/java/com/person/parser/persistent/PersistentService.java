package com.person.parser.persistent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.person.common.entity.Person;
import com.person.parser.queue.MsgBlockingQueue;

public class PersistentService {
	private static Logger logger = Logger.getLogger(PersistentService.class);
	
	private BlockingQueue<String> jsonQueue = MsgBlockingQueue.getInstance().getInstance().getJsonQueue();
	public void save2Queue(String[] sourceData) {
		if (ArrayUtils.isNotEmpty(sourceData)) {
			String json = this.convert2Json(sourceData);
			if (null != json) {
				try {
					jsonQueue.put(json);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	private String convert2Json(String[] sourceData) {
		if (ignoreTitleLine(sourceData) == null) {
			return null;
		}
		List<String> valueList = Arrays.asList(sourceData);
		Person p = fillPersonBean(valueList);

		return JSON.toJSONString(p);
	}

	private String[] ignoreTitleLine(String[] sourceData) {
		if (StringUtils.contains(sourceData[0], "Name")) {
			return null;
		}
		return sourceData;
	}

	private Person fillPersonBean(List<String> valueList) {
		Person p = new Person();
		p.setName(valueList.get(0));
		p.setCardNo(valueList.get(1));
		p.setDescriot(valueList.get(2));
		p.setCtfTp(valueList.get(3));
		p.setCtfId(valueList.get(4));
		p.setGender(valueList.get(5));
		p.setBirthday(valueList.get(6));
		p.setAddress(valueList.get(7));
		p.setZip(valueList.get(8));
		p.setDirty(valueList.get(9));
		p.setDistrict1(valueList.get(10));
		p.setDistrict2(valueList.get(11));
		p.setDistrict3(valueList.get(12));
		p.setDistrict4(valueList.get(13));
		p.setDistrict5(valueList.get(14));
		p.setDistrict6(valueList.get(15));
		p.setFirstNm(valueList.get(16));
		p.setLastNm(valueList.get(17));
		p.setDuty(valueList.get(18));
		p.setMobile(valueList.get(19));
		p.setTel(valueList.get(20));
		p.setFax(valueList.get(21));
		p.setEMail(valueList.get(22));
		p.setNation(valueList.get(23));
		p.setTaste(valueList.get(24));
		p.setEducation(valueList.get(25));
		p.setCompany(valueList.get(26));
		p.setCTel(valueList.get(27));
		p.setCAddress(valueList.get(28));
		p.setCZip(valueList.get(29));
		p.setFamily(valueList.get(30));
		p.setVersion(valueList.get(31));
		p.setId(valueList.get(32));
		return p;
	}
}
