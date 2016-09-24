import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class PilotingScreen extends JPanel{
    public PilotingScreen(PilotingController piloting_controller, String email, Container window){
        JPanel center_panel = new JPanel();
        BoxLayout center_layout = new BoxLayout(center_panel, BoxLayout.X_AXIS);
        center_panel.setLayout(center_layout);
        
        Color window_color = new Color(33,36,38);
        window.setBackground(window_color);
        Color bckg_color = new Color(58,61,63);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,25);

        center_panel.setEnabled(true);
        center_panel.setBackground(bckg_color);



        JPanel synthesis_panel = new JPanel();
        synthesis_panel.setBackground(bckg_color);
        synthesis_panel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()/2));
        synthesis_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        synthesis_panel.addMouseListener(piloting_controller);

        JLabel synthesis_label = new JLabel("Portfolio");
        synthesis_label.setFont(label_font);
        synthesis_label.setHorizontalTextPosition(JLabel.CENTER);
        synthesis_label.setVerticalTextPosition(JLabel.TOP);
        synthesis_label.setIcon(new ImageIcon("./Pictures/tmp/synthesis_image.png"));
        synthesis_label.setForeground(Color.WHITE);
        synthesis_panel.add(synthesis_label);


        JPanel group_panel = new JPanel();
        
        group_panel.setBackground(bckg_color);
        group_panel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()/2));
        group_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        group_panel.addMouseListener(piloting_controller);


        JLabel group_label = new JLabel("Groups");
        group_label.setFont(label_font);
        group_label.setHorizontalTextPosition(JLabel.CENTER);
        group_label.setVerticalTextPosition(JLabel.TOP);
        group_label.setIcon(new ImageIcon("./Pictures/group_image.png"));
        group_label.setForeground(Color.WHITE);
        group_panel.add(group_label);


        JPanel profile_panel = new JPanel();
        profile_panel.setBackground(bckg_color);
        profile_panel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()/2));
        profile_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        profile_panel.addMouseListener(piloting_controller);


        JLabel profile_label = new JLabel("Profile");
        profile_label.setFont(label_font);
        profile_label.setHorizontalTextPosition(JLabel.CENTER);
        profile_label.setVerticalTextPosition(JLabel.TOP);
        profile_label.setIcon(new ImageIcon("./Pictures/profile_image.png"));
        profile_label.setForeground(Color.WHITE);
        profile_panel.add(profile_label);


        JPanel history_panel = new JPanel();
        history_panel.setBackground(bckg_color);
        history_panel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()/2));
        history_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        history_panel.addMouseListener(piloting_controller);


        JLabel history_label = new JLabel("History");
        history_label.setFont(label_font);
        history_label.setHorizontalTextPosition(JLabel.CENTER);
        history_label.setVerticalTextPosition(JLabel.TOP);
        history_label.setIcon(new ImageIcon("./Pictures/history_image.png"));
        history_label.setForeground(Color.WHITE);
        history_panel.add(history_label);


        JPanel space_one = new JPanel();
        space_one.setPreferredSize(new Dimension(window.getWidth()/40,1));
        space_one.setBackground(window_color);

        JPanel space_two = new JPanel();
        space_two.setPreferredSize(new Dimension(window.getWidth()/40,1));
        space_two.setBackground(window_color);

        JPanel space_three = new JPanel();
        space_three.setPreferredSize(new Dimension(window.getWidth()/40,1));
        space_three.setBackground(window_color);

        center_panel.add(synthesis_panel);
        center_panel.add(space_one);

        center_panel.add(group_panel);
        center_panel.add(space_two);

        center_panel.add(profile_panel);
        center_panel.add(space_three);

        center_panel.add(history_panel);

        JPanel[] panels = new JPanel[4];
        panels[0]=synthesis_panel;
        panels[1]=group_panel;
        panels[2]=profile_panel;
        panels[3]=history_panel;
        piloting_controller.setPanels(panels);

        BoxLayout main_layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(main_layout);
        this.add(center_panel, BorderLayout.CENTER);
    }
}
