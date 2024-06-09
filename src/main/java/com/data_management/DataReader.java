package com.data_management;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public interface DataReader {
    /**
     * Reads data from a specified source and stores it in the data storage.
     * 
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    void readData(DataStorage dataStorage) throws IOException;
    
}

public class FileDataReader implements DataReader {
    private String filePath;

    public FileDataReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int patientId = Integer.parseInt(data[0]);
                double measurementValue = Double.parseDouble(data[1]);
                String recordType = data[2];
                long timestamp = Long.parseLong(data[3]);
                dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
            }
        }
    }
}

