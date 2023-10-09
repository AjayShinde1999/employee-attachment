package com.employee.portal.controller;

import com.employee.portal.payload.TicketRequestDto;
import com.employee.portal.payload.TicketResponseDto;
import com.employee.portal.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createTicket(@Valid TicketRequestDto ticketRequestDto) throws IOException {
        ticketService.createNewTicket(ticketRequestDto);
        return "Ticket Created";
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable Long ticketId) {
        TicketResponseDto ticketResponseDto = ticketService.getTicketById(ticketId);
        if (ticketResponseDto != null) {
            return ResponseEntity.ok(ticketResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{ticketId}/file")
    public ResponseEntity<byte[]> downloadTicketFile(@PathVariable Long ticketId) {
        byte[] fileContent = ticketService.getTicketFileContent(ticketId);

        if (fileContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set content type to PDF
            headers.setContentDispositionFormData("attachment", "ticket.jpg"); // Specify the file name

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
