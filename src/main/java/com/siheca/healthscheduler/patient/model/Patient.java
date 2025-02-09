package com.siheca.healthscheduler.patient.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a patient in the hospital system.")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the patient", example = "1")
    private Long id;
    @Schema(description = "Patient's first name", example = "John")
    private String firstName;
    @Schema(description = "Patient's last name", example = "Doe")
    private String lastName;
    @Schema(description = "Patient's email address", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Patient's phone number", example = "55 5233-1234")
    private String phone;
}
