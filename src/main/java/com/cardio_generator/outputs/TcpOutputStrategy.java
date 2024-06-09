package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * The TcpOutputStrategy class implements the OutputStrategy interface for TCP to output.
 *
 * <p><strong>Overview:</strong></p>
 * This establishes a TCP server to listen for client connections and then sends data to the connected clients.
 *
 * <p><strong>Usage:</strong></p>
 * This class is to be used by the main application or by data processing components to establish a TCP server
 * for sending the data to the clients.
 */
public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * TcpOutputStrategy constructor listens for any incomming connection on the specified port.
     *
     * @param port Is the port number where the TCP server will check for any incoming connections.
     */
    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs data over the TCP connection to the given connected client.
     *
     * @param patientId ID of the patient which is related to the data
     * @param timestamp time that the data was retrieved
     * @param label is the label or type of data retrieved
     * @param data the actual data which is going to be transfered
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
