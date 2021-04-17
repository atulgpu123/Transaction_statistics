package com.n26.enums;

import java.lang.constant.Constable;

public enum ErrorEnum implements Constable{

	ERROR_INVALID_TYPE("ERROR_INVALID_TYPE", "field is not parsable or transaction date is in future", "FUNCTIONAL"),
	ERROR_TRANSACTION_OLDER("ERROR_TRANSACTION_OLDER", "transaction is older than 60 seconds", "FUNCTIONAL");

	String code;
	String desc;
	String type;

	
	private ErrorEnum(String code, String desc, String type) {
		this.code = code;
		this.desc = desc;
		this.type = type;
	}

	public String getCode()
	{
		return code;
	}
	public String getDesc()
	{
		return desc;
	}
	public String getType()
	{
		return type;
	}
}
