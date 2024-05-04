package com.example.demo.rating;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rate") // Mapowanie do tabeli "rates"
public class Rating {

    @Id
    @SequenceGenerator(name = "rate_sequence", sequenceName = "rate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_sequence")
    private int id;

    private int rate;
    private int groupId;

    private Date date;

    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rate=" + rate +
                ", groupId=" + groupId +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Rating(int rate, int groupId, String comment) {
        this.rate = rate;
        this.groupId = groupId;
        this.date = new Date();
        this.comment = comment;
    }

    public Rating(int id, int rate, int groupId, Date date, String comment) {
        this.id = id;
        this.rate = rate;
        this.groupId = groupId;
        this.date = date;
        this.comment = comment;
    }

    public Rating() {
    }
}

