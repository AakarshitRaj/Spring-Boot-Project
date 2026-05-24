//package com.student.entity;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name ="students")
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Getter
//@Setter
//public class Student {
//@Id
//@GeneratedValue(strategy=GenerationType.IDENTITY)
//
//private Long id;
//private String name;
//private String email;
//private String course;
//private int age;
//
//}

package com.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String course;
    private int age;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(Long id, String name, String email, String course, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.age = age;
    }

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }

    // ---------------- SETTERS ----------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
