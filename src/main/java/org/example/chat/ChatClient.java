package org.example.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatClient extends JFrame {
    private static final int WIGHT = 400;
    private static final int HEIGHT = 300;

    private final JTextArea LOG = new JTextArea();

    private final JPanel PANEL_TOP = new JPanel(new GridLayout(2,3));
    private final JTextField IP_FIELD = new JTextField("ip");
    private final JTextField PORT_FIELD = new JTextField("port");
    private final JTextField NAME_FIELD = new JTextField("name");
    private final JPasswordField PASSWORD_FIELD = new JPasswordField("password");
    private final JButton LOGIN_BUTTON = new JButton("login");

    private final JPanel PANEL_BOTTOM = new JPanel(new GridLayout());
    private final JTextField MESSAGES = new JTextField();
    private final JButton SEND_BUTTON = new JButton("send");

    private JScrollPane scrollPane = new JScrollPane(LOG,22,31);


    private ChatClient() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIGHT,HEIGHT);
        setTitle("ChatClient");

        PANEL_TOP.add(IP_FIELD);
        PANEL_TOP.add(PORT_FIELD);
        PANEL_TOP.add(NAME_FIELD);
        PANEL_TOP.add(PASSWORD_FIELD);
        PANEL_TOP.add(LOGIN_BUTTON);

        PANEL_BOTTOM.add(MESSAGES);
        PANEL_BOTTOM.add(SEND_BUTTON);

        LOG.setEditable(false);
        LOG.setLineWrap(true);
        LOG.setText(ChatLogger.load());

        scrollPane.createVerticalScrollBar();
        add(PANEL_TOP,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(PANEL_BOTTOM,BorderLayout.SOUTH);

        MESSAGES.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SEND_BUTTON.doClick();
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        SEND_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(MESSAGES.getText().length() > 0)) {return;}
                String result = LOG.getText() +
                        String.format("\n%s: %s", NAME_FIELD.getText(), MESSAGES.getText());
                LOG.setText(result);
                ChatLogger.save(result);
                MESSAGES.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
