package com.example.demo.teacher;

import jakarta.persistence.*;

@Entity
@Table
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacher_sequance", sequenceName = "teacher_sequance", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teacher_sequance")
    private int id;
    private String firstName;

    private String lastName;
    private TeacherCondition condition;

    private int yearOfBirth;
    private Integer groupId;


    public Teacher(int id, String firstName, String lastName, TeacherCondition condition, int yearOfBirth, Integer groupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
        this.yearOfBirth = yearOfBirth;
        this.groupId = groupId;
    }

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, TeacherCondition condition, int yearOfBirth, Integer groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
        this.yearOfBirth = yearOfBirth;
        this.groupId = groupId;
    }



    public Teacher(String firstName, Integer groupId) {
        this.firstName = firstName;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TeacherCondition getCondition() {
        return condition;
    }

    public void setCondition(TeacherCondition condition) {
        this.condition = condition;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", condition=" + condition +
                ", yearOfBirth=" + yearOfBirth +
                ", groupId=" + groupId +
                '}';
    }
}
