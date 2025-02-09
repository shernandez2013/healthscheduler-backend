package com.siheca.healthscheduler.doctor.service;

import com.siheca.healthscheduler.doctor.DoctorRepository;
import com.siheca.healthscheduler.doctor.dto.DoctorDTO;
import com.siheca.healthscheduler.doctor.exception.DoctorNotFoundException;
import com.siheca.healthscheduler.doctor.mapper.DoctorMapper;
import com.siheca.healthscheduler.patient.exception.PatientNotFoundException;
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
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final MessageSource messageSource;


    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO)
                .orElseThrow(() -> new DoctorNotFoundException(getMessage("doctor.notfound", id)));
    }

    @Transactional
    public DoctorDTO save(DoctorDTO doctorDTO) {
        return doctorMapper.toDTO(doctorRepository.save(doctorMapper.toEntity(doctorDTO)));
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(doctorDTO.getFirstName());
                    existingDoctor.setLastName(doctorDTO.getLastName());
                    existingDoctor.setEmail(doctorDTO.getEmail());
                    existingDoctor.setPhone(doctorDTO.getPhone());
                    return doctorMapper.toDTO(doctorRepository.save(existingDoctor));
                }).orElseThrow(() -> new DoctorNotFoundException(getMessage("doctor.notfound", id)));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException(getMessage("doctor.notfound", id));
        }
        doctorRepository.deleteById(id);
    }

    public String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}

