package com.example.fileuploaddownload.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Firmbase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String location;
    private double versionName;

    public Firmbase() {
    }

    public Firmbase(String location, double versionName) {
        this.location = location;
        this.versionName = versionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getVersionName() {
        return versionName;
    }

    public void setVersionName(double versionName) {
        this.versionName = versionName;
    }

}
