package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;


/**
 * The PatientDataGenerator interface sets requirements for making patient data.
 * Implementing classes that must create a generate method to simulate patient data.
 */
public interface PatientDataGenerator {

    /**
     * Generates data for the patients using their ID and their chosen method for showing or saving the data.
     * 
     * @param patientId ID of the patient that the data is being generated for.
     * @param outputStrategy output strategy used to display or save the generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
