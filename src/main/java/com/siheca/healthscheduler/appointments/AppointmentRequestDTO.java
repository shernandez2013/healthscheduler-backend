package com.siheca.healthscheduler.appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentRequestDTO {
    Long doctorId;
    Long patientId;
    LocalDateTime appointmentDate;
    String status;
    String notes;
}
