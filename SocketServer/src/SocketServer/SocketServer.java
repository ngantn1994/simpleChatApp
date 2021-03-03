package SocketServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private ServerSocket serverSocket;

    private View serverView;

    private int sttClient = 1;

    public SocketServer() {
        serverView = new View();
        serverView.setVisible(true);

        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                new ThreadSocket(serverSocket.accept(), serverView, sttClient).start();
                serverView.updateTextArea("Connection started from client " + sttClient++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ThreadSocket extends Thread {
    private Socket socket;
    private View serverView;
    private int sttClient;

    ThreadSocket(Socket socket, View serverView, int sttClient) {
        this.socket = socket;
        this.serverView = serverView;
        this.sttClient = sttClient;
    }

    // Fuck with that try/catch, without them I can't compile
    // Wonder if it's JDK1.7's mandatory semantic or IntelliJ's fault
    public void run() {
        DataOutputStream sendToClient = null;
        try {
            sendToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader fromClient = null;
        try {
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String clientMessage = null;
            try {
                clientMessage = fromClient.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (clientMessage == null || fromClient == null) {
                serverView.updateTextArea("Disconnected from client " + sttClient);
                break;
            }
            serverView.updateTextArea(clientMessage, sttClient);
            String result = clientMessage.toUpperCase();
            try {
                sendToClient.writeBytes(result + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}