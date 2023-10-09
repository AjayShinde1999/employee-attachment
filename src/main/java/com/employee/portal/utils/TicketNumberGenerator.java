package com.employee.portal.utils;

import java.util.Random;

public class TicketNumberGenerator {
    public static String generateTicketNumber() {
        Random random = new Random();
//        int ticketNumber = random.nextInt(9000) + 1000;
//        return String.valueOf(ticketNumber);

        Random rand = new Random();
        int ticketNo = random.ints(1000, 10000).limit(1).findFirst().getAsInt();
        return String.valueOf(ticketNo);


    }
}
