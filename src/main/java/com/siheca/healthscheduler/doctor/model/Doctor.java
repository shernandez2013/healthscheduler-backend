package com.siheca.healthscheduler.doctor.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the doctor", example = "1")
    private Long id;

    @Schema(description = "Doctor's first name", example = "John")
    private String firstName;

    @Schema(description = "Doctor's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Doctor's specialty", example = "Cardiologist")
    private String specialty;

    @Schema(description = "Doctor's email address", example = "john.doe@hospital.com")
    private String email;

    @Schema(description = "Doctor's phone number", example = "55 5233-9876")
    private String phone;




}

