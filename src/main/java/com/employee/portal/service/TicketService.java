package com.employee.portal.service;


import com.employee.portal.payload.TicketRequestDto;
import com.employee.portal.payload.TicketResponseDto;

import java.io.IOException;


public interface TicketService {

    void createNewTicket(TicketRequestDto ticketRequestDto) throws IOException;

    TicketResponseDto getTicketById(Long ticketId);
    byte[] getTicketFileContent(Long ticketId);
}
