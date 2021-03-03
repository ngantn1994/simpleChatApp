package SocketServer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {
	
//	private int serverValue = Integer.parseInt( "FF0000", 16);
//	private Color serverColor = new Color(serverValue);
//	
//	private int clientValue = Integer.parseInt( "FFFF00", 16);
	
	private List<Color> clientColor; 
	
    private static final long serialVersionUID = 1L;
    private static final Font BIGGER_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    private JTextPane textPane;
    private StyledDocument doc;

    public View() {
        setResizable(false);
        setTitle("Server");
        setBounds(0, 0, 400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        clientColor = new ArrayList<Color>();
        clientColor.add(Color.BLUE);
        clientColor.add(Color.GREEN);
        clientColor.add(Color.DARK_GRAY);
        clientColor.add(Color.ORANGE);
        clientColor.add(Color.MAGENTA);
        clientColor.add(Color.BLACK);
        clientColor.add(Color.YELLOW);
        clientColor.add(Color.CYAN);
        clientColor.add(Color.PINK);
        clientColor.add(Color.LIGHT_GRAY);

        textPane = new JTextPane();
        textPane.setBorder(null);
        textPane.setMargin(new Insets(5, 5, 5, 5));
        doc = textPane.getStyledDocument();
        
        
//        textArea = new JTextArea("Server Opened.");
//        textArea.setFont(BIGGER_FONT);
//        textArea.setEditable(false);
//
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(1, 1, 350, 350);
        scrollPane.setPreferredSize(new Dimension(350, 350));
        getContentPane().add(scrollPane);
        
        appendToPane("Server Opened.", Color.RED);
        
    }

    public void updateTextArea(String s, int stt) {
    	Color color = clientColor.get(stt%10);
    	appendToPane("\nFROM CLIENT " + stt + ": " + s, color);
//        textArea.setText(textArea.getText() + "\nFROM CLIENT " + stt + ": " + s);
    }

    public void updateTextArea(String s) {
    	appendToPane("\n" + s, Color.RED);
//        textArea.setText(textArea.getText() + "\n" + s);
    }
    
    private void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        int len = textPane.getDocument().getLength();
        textPane.setCaretPosition(len);
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }
}
