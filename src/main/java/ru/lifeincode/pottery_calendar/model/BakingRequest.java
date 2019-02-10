package ru.lifeincode.pottery_calendar.model;

import java.util.Date;

public class BakingRequest {

    private enum StatusList {PLANNED,ACTIVE,ENDED,CANCELED};

    private int id;
    private User user;
    private Date startDate;
    private Date endDate;
    private int amount;
    private StatusList status;
    private String comment;

    public BakingRequest(User user, Date startDate, Date endDate, int amount, String comment) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.comment = comment;
        this.status=StatusList.PLANNED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
