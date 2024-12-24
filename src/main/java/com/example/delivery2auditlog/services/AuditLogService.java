package com.example.delivery2auditlog.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuditLogService {

    private static final String LOG_FILE_PATH = "audit.log";

    @RabbitListener(queues = "auditDiscountQueue")
    public void logDiscountMessage(String message) {
        String logEntry = "[DISCOUNT] " + LocalDateTime.now() + " - " + message;
        System.out.println(logEntry);
        writeLogToFile(logEntry);
    }
    @RabbitListener(queues = "auditCoordinatesQueue")
    public void logCoordinatesMessage(String message) {
        String logEntry = "[COORDINATES] " + LocalDateTime.now() + " - " + message;
        System.out.println(logEntry);
        writeLogToFile(logEntry);
    }
    @RabbitListener(queues = "auditNoticeQueue")
    public void logNoticeMessage(String message) {
        String logEntry = "[NOTICE] " + LocalDateTime.now() + " - " + message;
        System.out.println(logEntry);
        writeLogToFile(logEntry);
    }
    @RabbitListener(queues = "auditDeliveryQueue")
    public void logDeliveryMessage(String message) {
        String logEntry = "[DELIVERY] " + LocalDateTime.now() + " - " + message;
        System.out.println(logEntry);
        writeLogToFile(logEntry);
    }

    private void writeLogToFile(String logEntry) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(logEntry + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write log entry to file: " + e.getMessage());
        }
    }
}
