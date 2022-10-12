package com.bike.retoBikes.Model.DTOs;

import com.bike.retoBikes.Model.Client;

public class CountClient {
    private Long total;
    private Client client;

    //constructor
    public CountClient(Long total, Client client) {
        this.total = total;
        this.client = client;
    }
//crear getters y setters por si acaso
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
