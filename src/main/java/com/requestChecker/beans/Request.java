package com.requestChecker.beans;

import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class Request {
    private String protocol;
    private String requestType;
    private String remoteAddress;
    private String hash;
    private Object requestBody;
    private long timestamp;

    public Request(String protocol, String requestType, String remoteAddress, String hash, Object requestBody, long timestamp) {
        this.protocol = protocol;
        this.requestType = requestType;
        this.remoteAddress = remoteAddress;
        this.hash = hash;
        this.requestBody = requestBody;
        this.timestamp = timestamp;
    }

    public Request() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "protocol='" + protocol + '\'' +
                ", requestType='" + requestType + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", hash='" + hash + '\'' +
                ", requestBody=" + requestBody +
                '}';
    }
}
