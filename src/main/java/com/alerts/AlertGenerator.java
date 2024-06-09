package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        // Implementation goes here
        List<PatientRecord> records = patient.getRecords(Long.MIN_VALUE, Long.MAX_VALUE);
        checkBloodPressureAlerts(patient, records);
        checkBloodSaturationAlerts(patient, records);
        checkECGAlerts(patient, records)
    }

    private void checkBloodPressureAlerts(Patient patient, List<PatientRecord> records) {
        double previousSystolic = 0;
        double previousDiastolic = 0;
        int trendCount = 0;

        for (PatientRecord record : records) {
            if (record.getRecordType().equals("BloodPressure")) {
                double systolic = record.getMeasurementValue(); // Assuming systolic value
                double diastolic = record.getMeasurementValue(); // Assuming diastolic value

                if (systolic > 180 || systolic < 90 || diastolic > 120 || diastolic < 60) {
                    triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Critical Blood Pressure", record.getTimestamp()));
                }

                if (previousSystolic != 0 && Math.abs(systolic - previousSystolic) > 10) {
                    trendCount++;
                } else {
                    trendCount = 0;
                }

                if (trendCount >= 3) {
                    triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Blood Pressure Trend", record.getTimestamp()));
                    trendCount = 0;
                }

                previousSystolic = systolic;
                previousDiastolic = diastolic;
            }
        }
    }

    private void checkBloodSaturationAlerts(Patient patient, List<PatientRecord> records) {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("BloodSaturation")) {
                double saturation = record.getMeasurementValue();

                if (saturation < 92) {
                    triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Low Blood Saturation", record.getTimestamp()));
                }

            }
        }
    }

    private void checkECGAlerts(Patient patient, List<PatientRecord> records) {
        // Implement ECG alert logic
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        System.out.println("Alert triggered for Patient ID: " + alert.getPatientId() +
                ", Condition: " + alert.getCondition() +
                ", Timestamp: " + alert.getTimestamp());
    }
}
