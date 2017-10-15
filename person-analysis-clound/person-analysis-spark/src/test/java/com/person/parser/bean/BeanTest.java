package com.person.parser.bean;

import java.beans.PropertyDescriptor;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.person.common.entity.Person;

public class BeanTest {

	@Test
	public void test() {
		Person obj = new Person();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(obj.getClass());
		for(PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}
	}

}
