package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;


/**
 * The PatientDataGenerator interface sets requirements for making patient data.
 * Implementing classes must create a generate method to simulate patient data.
 */
public interface PatientDataGenerator {

    /**
     * Generates data for patients using their ID and the chosen method for showing or saving the data.
     * @param patientId ID of patient that the data is being generated.
     * @param outputStrategy output strategy for use to display or save generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
