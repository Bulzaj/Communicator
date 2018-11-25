package com.sadsoft.communicator.model.dto;

import lombok.Data;

@Data
public class MessageRequestDto {

    private String messageBody;
    private String receiversName;
}
