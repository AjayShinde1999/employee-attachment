package com.employee.portal.payload;

import lombok.Data;

import java.util.Date;

@Data
public class TicketResponseDto {

    private Long id;
    private String ticketNo;
    private String name;
    private Date date;
    private String subject;
    private String fileDownloadLink;
}
