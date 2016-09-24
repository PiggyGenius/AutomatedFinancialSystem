import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class HeaderHistoryScreen extends JPanel{
    public HeaderHistoryScreen(String email, RefreshListener refresh_listener){
        Color window_color = new Color(33,36,38);
        Font text_font = new Font(Font.SANS_SERIF,Font.BOLD,20);

        JLabel mail_label = new JLabel(email, JLabel.LEFT);

        mail_label.setFont(text_font);
        mail_label.setForeground(Color.WHITE);
        mail_label.setVerticalAlignment(JLabel.NORTH);
        mail_label.setHorizontalAlignment(JLabel.LEFT);


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

        JPanel refresh_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refresh_panel.setBackground(window_color);
        JButton refresh_button = new JButton("Refresh");
        refresh_button.setBackground(window_color);
        refresh_button.setForeground(Color.WHITE);
        refresh_button.setVerticalAlignment(JButton.BOTTOM);
        refresh_button.addActionListener(refresh_listener);
        refresh_panel.add(refresh_button);
        this.add(refresh_panel);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    }
}
