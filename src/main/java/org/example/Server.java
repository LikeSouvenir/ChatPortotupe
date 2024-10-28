package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    private boolean isServerWorking, checkLog;
    private JTextArea  jTextArea;

    private ArrayList<String> chatText = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    Server() {
        JFrame frame = new JFrame("Server");
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button log = new Button("Check Logs");
        JPanel mainJPanel = new JPanel( new GridLayout(2,1));

        mainJPanel.add(addActivatedButton());
        mainJPanel.add(checkLogs());
        frame.add(mainJPanel);

        frame.add(log, BorderLayout.SOUTH);

        log.addActionListener(_ -> {
            checkLog = !checkLog;
            jTextArea.setVisible(checkLog);
        });

        frame.setVisible(true);

        // добавляем историю сообщений
        getTxtFile();
    }

    void connectClient(Client client) {
        try {
            client.setConnectServer(this);
            clients.add(client);
            client.chat.append(getAllMessages().toString());
        }
        catch (Exception e) {
            System.out.println("Не удалось подключиться");
        }
    }

    void addMessage(String message) {
        chatText.add(message);
        goToTxtFile(getLastMessage());
        for (Client client: clients) {
            client.chat.append(getLastMessage());
            jTextArea.append(getLastMessage());
        }
    }

    private ArrayList<String> getAllMessages() {
        return chatText;
    }

    private String getLastMessage() {
        return chatText.getLast();
    }

    private JPanel addActivatedButton() {
        JPanel jPanel = new JPanel( new GridLayout(1,2));

        Button start = new Button("Start");
        Button stop = new Button("Stop");

        start.addActionListener(_ -> {
            if (!isServerWorking) {
                isServerWorking = true;
                jTextArea.append("Server is running\n");
            }
        });
        stop.addActionListener(_ -> {
            if (isServerWorking) {
                isServerWorking = false;
                jTextArea.append("Server stopped\n");
            }
        });

        jPanel.add(start);
        jPanel.add(stop);
        return jPanel;
    }

    private JTextArea checkLogs() {
        jTextArea = new JTextArea("logs\n",1,5);
        jTextArea.setVisible(false);
        jTextArea.setEnabled(false);
        return jTextArea;
    }

    public boolean getStatusServer() {
        System.out.println("Server running = " + isServerWorking);
        return isServerWorking;
    }

    private void getTxtFile() {
        try {
            BufferedReader reader = new BufferedReader( new FileReader("chatStory.txt"));
            String line = reader.readLine();
            while (line != null) {
                chatText.add(line + "\n");
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private void goToTxtFile(String message) {
        try {
            FileWriter writer = new FileWriter("chatStory.txt",true);
            writer.write(message);
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}
