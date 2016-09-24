import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class HeaderGroupScreen extends JPanel{
    public HeaderGroupScreen(){
        Color window_color = new Color(33,36,38);
        Font text_font = new Font(Font.SANS_SERIF,Font.BOLD,20);


        this.setBackground(window_color);
        this.setForeground(Color.WHITE);

        this.setLayout(new GridLayout(2,3));

        JPanel left_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left_panel.setBackground(window_color);
        left_panel.setForeground(Color.WHITE);
        JButton return_button = new JButton("Return");
        return_button.addActionListener(new ReturnController(this));
        return_button.setBackground(window_color);
        return_button.setForeground(Color.WHITE);
        left_panel.add(return_button);

        this.add(left_panel);
        this.add(new JLabel(""));

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    }
}
