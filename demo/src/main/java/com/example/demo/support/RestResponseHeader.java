package com.example.demo.support;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestResponseHeader {
	@JsonIgnore
	private static Map<String, Err_MsgEntity> msgData;

	// TODO 假資料
	class Err_MsgEntity {
		String err_msg = "";
	}

	@JsonProperty("status")
	@JsonInclude(Include.NON_NULL)
	private String code;

//	@JsonProperty("message")
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private String msg;

//	@JsonProperty
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private String redirect;

	public static void setMsgData(Map<String, Err_MsgEntity> msgData) {
		RestResponseHeader.msgData = msgData;
	}

	public RestResponseHeader error(ErrorCodeI errorCode) {
		this.code = errorCode.getCode();
		if (errorCode == null || errorCode == ErrorCode.OK) {
			this.msg = "";
			return this;
		}

		this.msg = "操作錯誤，請聯繫系統管理者。";
		if (msgData != null && msgData.containsKey(errorCode.getCode())) {
			this.msg = msgData.get(errorCode.getCode()).err_msg;
		}
		this.msg = String.format("%s (Code: %s)", this.msg, errorCode.getCode());

		return this;
	}

	public RestResponseHeader error(ErrorCodeI errorCode, Object... str) {
		this.code = errorCode.getCode();
		if (errorCode == null || errorCode == ErrorCode.OK) {
			this.msg = "";
			return this;
		}

		String tmpMsg = "";
		if (msgData != null && msgData.containsKey(errorCode.getCode())) {
			tmpMsg = msgData.get(errorCode.getCode()).err_msg;
			tmpMsg = String.format(tmpMsg, str);
		} else {
			String t = "";
			String p = "";
			for (Object s : str) {
				t += String.valueOf(s) + p;
				p = ",";
			}
			tmpMsg = "操作錯誤，請聯繫系統管理者。[" + t + "]";

		}
		this.msg = String.format("%s (Code: %s)", tmpMsg, errorCode.getCode());

		return this;
	}

	public RestResponseHeader redirect(String redirect) {
		this.redirect = redirect;

		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
