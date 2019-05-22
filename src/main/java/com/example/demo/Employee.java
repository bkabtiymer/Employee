package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotNull
//    @Size(min=2)
    private long id;
//    @NotNull
//    @Size(min = 2)
    private String name;
//    @NotNull
//    @Size(min = 2)
    private String job_title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "department_id")
    private Department department;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
