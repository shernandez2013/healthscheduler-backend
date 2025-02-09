package com.siheca.healthscheduler.appointments;

import com.siheca.healthscheduler.doctor.model.Doctor;
import com.siheca.healthscheduler.patient.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDTO toDTO(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getNotes()
        );
    }

    public Appointment toEntity(AppointmentRequestDTO dto, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        return appointment;
    }
}
