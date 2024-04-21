package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The FileOutputStrategy class implements the OutputStrategy interface and provides functional output
 *  for patients data to the file.
 */

// Good practice to have classes named with an uppercase letter
public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory; // changed the variable name to camelCase

    /**
     * A ConcurrentHashMap which stores the file paths regarding each data label.
     */
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); // changed to camelCase format

    /**
     * Constructs a new FileOutputStrategy with the given base directory.
     *
     * @param baseDirectory The base directory where the output files will be stored.
     */
    public FileOutputStrategy(String baseDirectory) { // uppercase naming
        this.baseDirectory = baseDirectory; // changed to camelCase format
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory)); // camelCase format
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        
        // Set the FilePath variable
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString()); // changed variables to camelCase

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) { // changed FilePath to camelCase
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
