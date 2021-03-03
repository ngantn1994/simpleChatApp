package SocketClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientSocket {
    private View view;

    private String input;
    private String result;

    private DataOutputStream sendToServer;
    private BufferedReader fromServer;
    private Socket clientSocket;


    public ClientSocket() {
        view = new View();
        view.setVisible(true);
        try {
            clientSocket = new Socket("127.0.0.1", 9999);
            sendToServer = new DataOutputStream(clientSocket.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    input = view.getTextFieldValue();
                    try {
                        if (input.equalsIgnoreCase("quit")) {
                            clientSocket.close();
                        }
                        sendToServer.writeBytes(input + '\n');
                        view.resetTextField();
                        result = fromServer.readLine();
                        view.updateTextArea(result);
                    } catch (IOException e1) {
                        System.out.println("Exception Client: " + e1.getMessage());
                        e1.printStackTrace();
                    }
                }
            };
            view.setButtonActionListener(listener);

        } catch (IOException e) {
            System.out.println("Exception Client: " + e.getMessage());
        }
    }
}  