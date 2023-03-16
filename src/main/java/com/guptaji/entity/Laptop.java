package com.guptaji.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Laptop extends PanacheEntity {

    // Here we are going with Panache Entity method so a field with name 'id' will automatically come here

    private String name;
    private String brandName;
    private int ram;
    private int externalStorage;

    public Laptop(String name, String brandName, int ram, int externalStorage) {
        this.name = name;
        this.brandName = brandName;
        this.ram = ram;
        this.externalStorage = externalStorage;
    }

    public Laptop() {
        // default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getExternalStorage() {
        return externalStorage;
    }

    public void setExternalStorage(int externalStorage) {
        this.externalStorage = externalStorage;
    }
}
