package com.example.demo.support;

public enum ErrorCode implements ErrorCodeI {
	// 編碼規則: 系統代號(1碼大寫英文)+主訊息代號(3碼數字)+子訊息代號(1碼數字)
	// 系統代號: member:M, coach/carry:C, tournament:T, system:S
	// 主訊息代號前1碼: auth:1, member:2, coach:3, carry:4, classroom:5, data-analysis:6,
	// tournament:7, match:8, system:9

	/** 成功 **/
	OK("OK"),

	/** 註解 **/
	S9000("S9000"),
	/** 註解 **/
	S9010("S9010"),
	/** 註解 **/
	S9980("S9980"),
	/** 註解 **/
	S9990("S9990"),
	/** 介接資料異常 **/
    Y1001("Y1001"),
	;
	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return String.valueOf(this.code);
	}

	@Override
	public String toString() {
		return String.valueOf(this.code);
	}
}
