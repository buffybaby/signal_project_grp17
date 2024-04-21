package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    // it is good practice to name constants in full upercase and underscores for gaps.
    public static final Random RANDOM_GENERATOR = new Random();
    private boolean[] alertStates; //changed the variable name to camelCase format  
    // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; //variable to camelCase
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) { //variable to camelCase
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false; //variable to camelCase
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double lambda = 0.1; //changed the variable name to camelCase format
                // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); //changed to lambda since thats the variables new name
                // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p; //changed to RANDOM_GENERATOR since thats the constants new name.

                if (alertTriggered) {
                    alertStates[patientId] = true; //variable to camelCase
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
