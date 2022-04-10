package com.zerotoproduction.bucketlist.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor

public class DispensaryUserDTO implements Serializable {

    private Long dispensary;
    private String userId;
    private String name;
    private String role;
    private String status;

    public Long getDispensary() {
        return dispensary;
    }

    public void setDispensary(Long dispensary) {
        this.dispensary = dispensary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
