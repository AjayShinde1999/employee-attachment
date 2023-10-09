package com.employee.portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket_no")
    private String ticketNo;
    @Column(name = "name")
    private String name;
    private Date date;
    @Column(name = "subject")
    private String subject;
    @Lob
    private byte[] pdf;
    public byte[] getFile() {
        return pdf;
    }
}
