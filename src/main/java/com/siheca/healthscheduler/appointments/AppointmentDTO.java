package com.siheca.healthscheduler.appointments;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime appointmentDate,
        String status,
        String notes
) {}
