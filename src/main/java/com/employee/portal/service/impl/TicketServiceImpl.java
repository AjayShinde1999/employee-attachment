package com.employee.portal.service.impl;

import com.employee.portal.model.Ticket;
import com.employee.portal.payload.TicketRequestDto;
import com.employee.portal.payload.TicketResponseDto;
import com.employee.portal.repository.TicketRepository;
import com.employee.portal.service.TicketService;
import com.employee.portal.utils.TicketNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewTicket(TicketRequestDto ticketRequestDto) throws IOException {
        Ticket ticket = mapToEntity(ticketRequestDto);
        ticket.setTicketNo(TicketNumberGenerator.generateTicketNumber());
        ticket.setDate(new Date());
        ticket.setPdf(ticketRequestDto.getPdf().getBytes());
        ticketRepository.save(ticket);
    }

    @Override
    public TicketResponseDto getTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            TicketResponseDto responseDto = mapToDto(ticket);

            // Generate a link to download the file
            String downloadLink = "/api/ticket/" + ticketId + "/file";
            responseDto.setFileDownloadLink(downloadLink);

            return responseDto;
        } else {
            return null;
        }
    }

    @Override
    public byte[] getTicketFileContent(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        return (ticket != null) ? ticket.getFile() : null;
    }

    public Ticket mapToEntity(TicketRequestDto dto) {
        return modelMapper.map(dto, Ticket.class);
    }

    private TicketResponseDto mapToDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketResponseDto.class);
    }
}
