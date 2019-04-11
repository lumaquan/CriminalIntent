package com.example.criminalintent2;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID id;
    private String title;
    private Date date;
    private boolean isSolved;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        this.id = id;
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Crime)) return false;
        return ((Crime) obj).getId().equals(id);
    }
}
