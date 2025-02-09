CREATE TABLE public.patient
(
    id         SERIAL PRIMARY KEY,    -- Auto-incremental unique identifier for the patient
    first_name VARCHAR(255) NOT NULL, -- Patient's first name
    last_name  VARCHAR(255) NOT NULL, -- Patient's last name
    email      VARCHAR(255) NOT NULL, -- Patient's email address
    phone      VARCHAR(255) NOT NULL  -- Patient's phone number
);

-- Add an index on the email field to ensure faster lookups by email
CREATE INDEX idx_patient_email ON public.patient (email);