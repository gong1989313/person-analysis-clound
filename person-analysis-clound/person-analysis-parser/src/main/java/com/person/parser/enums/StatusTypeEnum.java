package com.person.parser.enums;

public enum StatusTypeEnum {
	// ���ֶο�ʼ  
    NewFieldStart,  
    // �������ֶ�  
    NonQuotesField,  
    // �����ֶ�  
    QuotesField,  
    // �ֶηָ�  
    FieldSeparator,  
    // �����ֶ��е�����  
    QuoteInQuotesField,  
    // �зָ����ַ�1���س�  
    RowSeparator,  
    // �﷨����  
    Error
}
