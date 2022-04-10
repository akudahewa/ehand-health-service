package com.zerotoproduction.bucketlist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "dispensary_users")
public class DispensaryUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dispensary_id", referencedColumnName = "dispensary_id")
    private Dispensary dispensary;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    public DispensaryUser(){}

    public DispensaryUser(Dispensary dispensary, String status, String userId, String name,String role) {
        this.dispensary = dispensary;
        this.status = status;
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dispensary getDispensary() {
        return dispensary;
    }

    public void setDispensary(Dispensary dispensary) {
        this.dispensary = dispensary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
