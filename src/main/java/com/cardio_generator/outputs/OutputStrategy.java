package com.cardio_generator.outputs;

/**
 * The OutputStrategy interface adds a contract for outputting patient data.
 * Implementing classes have to provide implementation for the output method to handle the data output.
 */
public interface OutputStrategy {

    /**
     * Outputs given patient data based on provided parameters.
     *
     * @param patientId The ID of the patient whose data is being outputted.
     * @param timestamp Timestamp shwoing when data was recorded.
     * @param label     The label describing the type or category of the data.
     * @param data      The actual data which will be outputted.
     */
    void output(int patientId, long timestamp, String label, String data);
}
