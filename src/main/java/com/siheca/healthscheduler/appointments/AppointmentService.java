package com.siheca.healthscheduler.appointments;

import com.siheca.healthscheduler.doctor.DoctorRepository;
import com.siheca.healthscheduler.doctor.model.Doctor;
import com.siheca.healthscheduler.patient.model.Patient;
import com.siheca.healthscheduler.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;
    private final MessageSource messageSource;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository,
                              AppointmentMapper appointmentMapper,
                              MessageSource messageSource) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
        this.messageSource = messageSource;
    }

    @Transactional
    public AppointmentDTO createAppointment(AppointmentRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new AppointmentNotFoundException(getMessage("doctor.notfound")));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new AppointmentNotFoundException(getMessage("patient.notfound")));

        Appointment appointment = appointmentMapper.toEntity(dto, doctor, patient);
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    public AppointmentDTO getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(getMessage("appointment.notfound")));
        return appointmentMapper.toDTO(appointment);
    }

    public Page<AppointmentDTO> getAllAppointments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("appointmentDate").ascending());
        return appointmentRepository.findAll(pageable)
                .map(appointmentMapper::toDTO);
    }


    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentRequestDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(getMessage("appointment.notfound")));

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Transactional
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(getMessage("appointment.notfound")));
        appointmentRepository.delete(appointment);
    }

    public String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}
