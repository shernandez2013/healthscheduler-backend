package com.siheca.healthscheduler.appointments

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AppointmentRepository : JpaRepository<Appointment?, Long?>