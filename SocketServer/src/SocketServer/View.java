package SocketServer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class View extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Font BIGGER_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    private JTextArea textArea;

    public View() {
        setResizable(false);
        setTitle("Server");
        setBounds(0, 0, 400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        textArea = new JTextArea("Server Opened.");
        textArea.setFont(BIGGER_FONT);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(1, 1, 350, 350);
        scrollPane.setPreferredSize(new Dimension(350, 350));
        getContentPane().add(scrollPane);
    }

    public void updateTextArea(String s, int stt) {
        textArea.setText(textArea.getText() + "\nFROM CLIENT " + stt + ": " + s);
    }

    public void updateTextArea(String s) {
        textArea.setText(textArea.getText() + "\n" + s);
    }
}
