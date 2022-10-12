package com.bike.retoBikes.Model.DTOs;

public class CountStatus {
    private Long completed;
    private  Long cancelled;

    //contructor

    public CountStatus(Long completed, Long cancelled) {
        this.completed = completed;
        this.cancelled = cancelled;
    }
    //getters y setters

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public Long getCancelled() {
        return cancelled;
    }

    public void setCancelled(Long cancelled) {
        this.cancelled = cancelled;
    }
}
