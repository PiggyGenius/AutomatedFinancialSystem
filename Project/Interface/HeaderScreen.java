import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class HeaderScreen extends JPanel{
    public HeaderScreen(String email){
        Color window_color = new Color(33,36,38);
        Font text_font = new Font(Font.SANS_SERIF,Font.BOLD,20);

        JLabel mail_label = new JLabel(email, JLabel.LEFT);

        mail_label.setFont(text_font);
        mail_label.setForeground(Color.WHITE);
        mail_label.setVerticalAlignment(JLabel.NORTH);
        mail_label.setHorizontalAlignment(JLabel.LEFT);


        JButton logout_button = new JButton("Logout");
        logout_button.setBackground(window_color);
        logout_button.setForeground(Color.WHITE);
        logout_button.addActionListener(new LogoutListener(ConnectionListener.getConnectionPage(),this));

        //logout_button.setFont(text_font);
        logout_button.setVerticalAlignment(JButton.NORTH);
        logout_button.setHorizontalAlignment(JButton.RIGHT);


        JPanel button_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        button_panel.setBackground(window_color);
        button_panel.add(logout_button);

        this.setBackground(window_color);
        this.setForeground(Color.WHITE);

        this.setLayout(new GridLayout(2,3));

        JPanel left_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left_panel.setBackground(window_color);
        left_panel.setForeground(Color.WHITE);
        JButton return_button = new JButton("Return");
        return_button.setBackground(window_color);
        return_button.setForeground(Color.WHITE);
        return_button.addActionListener(new ReturnController(this));
        left_panel.add(return_button);
        left_panel.add(mail_label);
        this.add(left_panel);
        this.add(new JLabel(""));
        this.add(button_panel);

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    }
}
