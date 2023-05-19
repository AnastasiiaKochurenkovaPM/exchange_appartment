package com.exchangeap.exchappart.models;

import jakarta.persistence.*;

@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String fromsearchfloor;
    @Column(nullable = false)
    private String tosearchfloor;
    @Column(nullable = false)
    private int searchrooms;
    @Column(nullable = false)
    private String searchregion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSearchrooms() {
        return searchrooms;
    }

    public void setSearchrooms(int searchrooms) {
        this.searchrooms = searchrooms;
    }

    public String getFromsearchfloor() {
        return fromsearchfloor;
    }

    public void setFromsearchfloor(String fromsearchfloor) {
        this.fromsearchfloor = fromsearchfloor;
    }

    public String getTosearchfloor() {
        return tosearchfloor;
    }

    public void setTosearchfloor(String tosearchfloor) {
        this.tosearchfloor = tosearchfloor;
    }

    public String getSearchregion() {
        return searchregion;
    }

    public void setSearchregion(String searchregion) {
        this.searchregion = searchregion;
    }

    public Search() {
    }

    public Search(String fromsearchfloor, String tosearchfloor, int searchrooms, String searchregion) {
        this.fromsearchfloor = fromsearchfloor;
        this.tosearchfloor = tosearchfloor;
        this.searchrooms = searchrooms;
        this.searchregion = searchregion;
    }
}
