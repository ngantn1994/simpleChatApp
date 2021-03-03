package SocketClient;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField textField;
    private JButton button;

    private JTextArea textArea;

    private static final Font CLIENT_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);

    public View() {
        setResizable(false);
        setBounds(90, 90, 400, 180);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setLayout(new FlowLayout());

        textArea = new JTextArea();
        textArea.setFont(CLIENT_FONT);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        // Must use setBounds to make scroll bar appear @@
        scrollPane.setBounds(1, 1, 350, 60);
        scrollPane.setPreferredSize(new Dimension(350, 60));
        add(scrollPane);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 30));
        getContentPane().add(textField);

        button = new JButton("Click to send");
        button.setPreferredSize(new Dimension(130, 30));
        getContentPane().add(button);
    }

    public String getTextFieldValue() {
        return textField.getText();
    }

    public void resetTextField() {
        textField.setText("");
    }

    public void updateTextArea(String s) {
        textArea.setText(textArea.getText() + "FROM SERVER: " + s + "\n");
    }


    public void setButtonActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}
