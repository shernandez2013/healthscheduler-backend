package com.siheca.healthscheduler.patient.service;

import com.siheca.healthscheduler.patient.dto.PatientDTO;
import com.siheca.healthscheduler.patient.exception.PatientNotFoundException;
import com.siheca.healthscheduler.patient.mapper.PatientMapper;
import com.siheca.healthscheduler.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final MessageSource messageSource;


    public List<PatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElseThrow(() -> new PatientNotFoundException(getMessage("patient.notfound", id)));
    }

    @Transactional
    public PatientDTO save(PatientDTO patientDTO) {
        return patientMapper.toDTO(patientRepository.save(patientMapper.toEntity(patientDTO)));
    }

    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFirstName(patientDTO.getFirstName());
                    existingPatient.setLastName(patientDTO.getLastName());
                    existingPatient.setEmail(patientDTO.getEmail());
                    existingPatient.setPhone(patientDTO.getPhone());
                    return patientMapper.toDTO(patientRepository.save(existingPatient));
                }).orElseThrow(() -> new PatientNotFoundException(getMessage("patient.notfound", id)));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(getMessage("patient.notfound", id));
        }
        patientRepository.deleteById(id);
    }

    public String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}

