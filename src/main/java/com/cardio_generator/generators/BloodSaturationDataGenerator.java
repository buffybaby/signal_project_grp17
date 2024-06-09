package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * The BloodSaturationDataGenerator class implements the PatientDataGenerator interface and provides a given
 * functionality to then generate blood saturation data for given patients.
 *
 * <p><strong>Overview:</strong></p>
 * This class simulates blood saturation values for a given number of patients by then generating small
 * changes around the given baseline values.
 *
 * <p><strong>Usage:</strong></p>
 * This class is used by the main application or data processing components to simulate and output blood
 * saturation data for the given patients.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {
    private static final Random random = new Random();
    private int[] lastSaturationValues;

    /**
     * Constructs a new BloodSaturationDataGenerator with the given number of patients.
     *
     * @param patientCount number of patients whose blood saturation data will be generated.
     */
    public BloodSaturationDataGenerator(int patientCount) {
        lastSaturationValues = new int[patientCount + 1];

        // Initialize with baseline saturation values for each patient
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); // Initializes with a value between 95 and 100
        }
    }

    /**
     * Generates blood saturation data for the specific given patient and then outputs it using provided
     * output strategy.
     *
     * @param patientId ID of the patient for which the data is being generated for.
     * @param outputStrategy the output strategy to use for displaying or saving the generated data.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); // This will print the stack trace to help identify where the error occurred.
        }
    }
}
