package com.example.demo.support;

import java.util.Arrays;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestResponse {
    @JsonProperty("header")
    private RestResponseHeader header = new RestResponseHeader();

    @JsonProperty("body")
    private Object body;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("trace")
    private Object trace;

    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonIgnore
    private HttpHeaders httpHeaders = new HttpHeaders();

    private RestResponse() {}

    public static RestResponse ok() {
        RestResponse apiRes = new RestResponse();
        apiRes.header.error(ErrorCode.OK);
        apiRes.httpStatus = HttpStatus.OK;

        return apiRes;
    }

    public static RestResponse error(ErrorCodeI errorCode) {
        RestResponse apiRes = new RestResponse();
        apiRes.header.error(errorCode);
        apiRes.httpStatus = HttpStatus.OK;

        return apiRes;
    }

    public static RestResponse error(ErrorCodeI errorCode, String variable) {
        RestResponse apiRes = new RestResponse();
        apiRes.header.error(errorCode, new Object[] {variable});
        apiRes.httpStatus = HttpStatus.OK;

        return apiRes;
    }

    public static RestResponse error(ErrorCodeI errorCode, String[] variables) {
        RestResponse apiRes = new RestResponse();
        apiRes.header.error(errorCode, (Object[]) variables);
        apiRes.httpStatus = HttpStatus.OK;

        return apiRes;
    }

    public RestResponse redirect(String redirect) {
        this.header.redirect(redirect);
        return this;
    }

    public <T> RestResponse trace(T trace) {
        this.trace = trace;
        return this;
    }

    public <T> RestResponse body(T body) {
        this.body = body;
        return this;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    public RestResponse httpStatus(HttpStatus status) {
        this.httpStatus = status;
        return this;
    }

    public RestResponse httpHeader(String name, String... values) {
        this.httpHeaders.addAll(name, Arrays.asList(values));
        return this;
    }

    @JsonIgnore
    public ResponseEntity<Object> toResponseEntity() {
        BodyBuilder bodyBuilder = ResponseEntity.status(this.httpStatus);
        if (!httpHeaders.isEmpty()) {
            bodyBuilder.headers(httpHeaders);
        }

        return bodyBuilder.body(this);
    }

    @JsonIgnore
    public ResponseEntity<Map<String, Object>> toMap() {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(this, new TypeReference<Map<String, Object>>() {});

        BodyBuilder bodyBuilder = ResponseEntity.status(this.httpStatus);
        if (!httpHeaders.isEmpty()) {
            bodyBuilder.headers(httpHeaders);
        }

        return bodyBuilder.body(map);
    }

}
