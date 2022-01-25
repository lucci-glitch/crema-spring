package com.crema.creamaspring.payloads.responses;

import lombok.Data;

@Data
public class MessageResponse {
    private String text;
    private String sender;

    public MessageResponse(String text) {
        this.text = text;
    }
}
