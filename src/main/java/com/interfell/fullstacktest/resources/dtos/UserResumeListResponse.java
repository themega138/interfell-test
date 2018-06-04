package com.interfell.fullstacktest.resources.dtos;

import java.util.Date;
import java.util.List;

public class UserResumeListResponse {

    private List<UserResume> data;

    private Date date;

    public UserResumeListResponse(List<UserResume> data, Date date) {
        this.data = data;
        this.date = date;
    }

    public List<UserResume> getData() {
        return data;
    }

    public void setData(List<UserResume> data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
