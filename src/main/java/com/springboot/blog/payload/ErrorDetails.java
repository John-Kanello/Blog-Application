package com.springboot.blog.payload;

import java.util.Date;

public class ErrorDetails {

    // If I want to send more fields such as the status code, I can define them in this class

    private Date timestamp;
    private String message;
    private String details;

    private int status;

    public ErrorDetails(Date timestamp, String message, String details, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }
}
