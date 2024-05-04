package com.example.demo.group;

import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @SequenceGenerator(name = "group_sequance", sequenceName = "group_sequance", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "group_sequance")
    private int id;
    private String className;
    private int maxCapacity;
    private float fillPercentage;
    private int numberOfRates;
    private float averageRate;


    public Group(int id, String className, int maxCapacity) {
        this.id = id;
        this.className = className;
        this.maxCapacity = maxCapacity;
    }

    public Group(int i, String math, int fillPercentage, float v, int averageRate) {
    }

    public Group(String className, int maxCapacity) {
        this.className = className;
        this.maxCapacity = maxCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public float getFillPercentage() {
        return fillPercentage;
    }

    public void setFillPercentage(float fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public int getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", fillPercentage=" + fillPercentage +
                ", numberOfRates=" + numberOfRates +
                ", averageRate=" + averageRate +
                '}';
    }

    public Group(int id, String className, int maxCapacity, float fillPercentage, int numberOfRates, float averageRate) {
        this.id = id;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.fillPercentage = fillPercentage;
        this.numberOfRates = numberOfRates;
        this.averageRate = averageRate;
    }

    public Group() {
    }

    public Group(String className, int maxCapacity, float fillPercentage, int numberOfRates, float averageRate) {
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.fillPercentage = fillPercentage;
        this.numberOfRates = numberOfRates;
        this.averageRate = averageRate;
    }
}
