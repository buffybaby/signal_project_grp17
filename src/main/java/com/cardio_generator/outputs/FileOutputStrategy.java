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
 *
 * <p><strong>Overview:</strong></p>
 * This class defines the methods to output the patient data to a given file.
 * It utilizes the base directory to store the output files and a ConcurrentHashMap to manage the given
 * file paths based on the data labels.
 *
 * <p><strong>Usage:</strong></p>
 * This class is intended to be used by classes that require a file-based output for the patient data, such as
 * data generators or data processors.
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
     * @param baseDirectory the base directory where the output files will be stored.
     */
    public FileOutputStrategy(String baseDirectory) { // uppercase naming
        this.baseDirectory = baseDirectory; // changed to camelCase format
    }

    /**
     * Outputs patient data based on the provided parameters to a file.
     *
     * @param patientId is the ID of the patient whose data is being outputted.
     * @param timestamp shows the timestamp when the data was recorded.
     * @param label a label describing the category or the given type of data
     * @param data the actual data which is to be outputted
     * @throws IOException incase an I/O error occurs while creation of directories or writing to the file
     */
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
