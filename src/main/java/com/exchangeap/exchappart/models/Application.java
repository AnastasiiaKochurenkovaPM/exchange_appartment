package com.exchangeap.exchappart.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int rooms;
    @Column(nullable = false)
    private float area;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String floor;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String wantfloor;
    @Column(nullable = false)
    private String wantregion;
    @Column(nullable = false)
    private String number;
    @CreationTimestamp()
    private Instant createDateTime;

    @UpdateTimestamp
    private Instant updateDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWantfloor() {
        return wantfloor;
    }

    public void setWantfloor(String wantfloor) {
        this.wantfloor = wantfloor;
    }

    public String getWantregion() {
        return wantregion;
    }

    public void setWantregion(String wantregion) {
        this.wantregion = wantregion;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Instant createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Instant getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Instant updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Application() {
    }
    public Application(
            int rooms,
            float area,
            String city,
            String floor,
            String wantfloor,
            String region,
            String wantregion,
            String number) {
        this.rooms = rooms;
        this.floor = floor;
        this.wantfloor = wantfloor;
        this.area = area;
        this.city = city;
        this.region = region;
        this.wantregion = wantregion;
        this.number = number;
    }
}
