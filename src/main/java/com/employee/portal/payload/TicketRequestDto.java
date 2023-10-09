package com.employee.portal.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class TicketRequestDto {

    private Long id;
    private String ticketNo;

    @NotBlank(message = "name is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name should only contain alphabetic characters")
    private String name;

    private Date date;

    @NotBlank(message = "subject is required")
    private String subject;

    private MultipartFile pdf;
}
