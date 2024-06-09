package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * AlertGenerator implements the PatientDataGenerator interface - generates synthetic alerts for the patients
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random RANDOM_GENERATOR = new Random();
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * AlertGenerator Constructor with a number of patients
     *
     * @param patientCount the number of patients for which alert data will be generated.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates the alert data for the specific patient and outputs it using the output strategy
     *
     * @param patientId ID of the patient which the alert data is being generated for
     * @param outputStrategy the output strategy used to output the generated alert data
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double lambda = 0.1; // Average alerts per period
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            //Error handling if something went wrong while generating data
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
