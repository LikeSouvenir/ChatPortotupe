package org.example;

import javax.swing.*;
import java.awt.*;

public class Client {

    Server server = null;
    JTextField login, IP_Address, port, message;
    JPasswordField password;
    JTextArea chat;
    JButton send, connect;
    JPanel connectPanel, messagePanel;

    Client() {

        JFrame frame = new JFrame("Chat Client");
        frame.setSize(400,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(addButtonOnForm(),BorderLayout.NORTH);
        frame.add(addChat(), BorderLayout.CENTER);
        frame.add(addSenderMessage(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void setConnectServer(Server server) {
        this.server = server;
    }

    private JTextArea addChat() {
        chat = new JTextArea(10,1);
        chat.setEnabled(false);
        return chat;
    }

    private JPanel addSenderMessage() {
        messagePanel = new JPanel(new GridLayout(1,2));
        message = new JTextField();
        send = new JButton("Send");

        messagePanel.add(message);
        messagePanel.add(send);

        send.addActionListener(_-> {
            if(server != null && server.getStatusServer()) {
                server.addMessage(login.getText() + " : " + message.getText() + "\n");
            }
        });

        return  messagePanel;
    }

    private JPanel addButtonOnForm() {
        connectPanel = new JPanel(new GridLayout(2,3));
        login = new JTextField("Login");
        password = new JPasswordField("as12asd");
        IP_Address = new JTextField("127.0.0.1");
        port = new JTextField("8189");
        connect = new JButton("Login");

        connectPanel.add(IP_Address);
        connectPanel.add(port);
        connectPanel.add(login);
        connectPanel.add(password);
        connectPanel.add(connect);

        connectPanel.setBackground(Color.GRAY);

        return connectPanel;
    }
}
