package com.haipdm.todolist.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="task")
public class Task{

//    private static final long serialVersionUID = 1L;
@Id
//    @Column(name = "id",nullable = false)
    private int id;

//    @Column(name = "taskName",nullable = false)
    private String task_name;

    private String description;

//    @Column(name = "startDate",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

//    @Column(name = "finishDate",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date end_date;

//    @Column(name = "taskStatus",nullable = false)
    private int status;

//    @Column(name = "priorityLevel",nullable = false)
    private int priority;

//    @Column(name = "flagDelete",nullable = false)
    private int flag_delete;


    public Task() {
        super();
    }

    public Task(int id, String task_name, String description, Date start_date, Date end_date, int status, int priority, int flag_delete) {
        this.id = id;
        this.task_name = task_name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.priority = priority;
        this.flag_delete = flag_delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getFlag_delete() {
        return flag_delete;
    }

    public void setFlag_delete(int flag_delete) {
        this.flag_delete = flag_delete;
    }
}
