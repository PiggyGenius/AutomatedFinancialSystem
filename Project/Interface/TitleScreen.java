import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class TitleScreen extends JPanel{
    public TitleScreen(String email,ConnectionPage connection_page){
        Color window_color = new Color(33,36,38);
        Font text_font = new Font(Font.SANS_SERIF,Font.PLAIN,20);

        JLabel mail_label = new JLabel(email, JLabel.LEFT);

        mail_label.setFont(text_font);
        mail_label.setForeground(Color.WHITE);
        mail_label.setVerticalAlignment(JLabel.NORTH);
        mail_label.setHorizontalAlignment(JLabel.LEFT);


        JButton logout_button = new JButton("Logout");
        logout_button.setBackground(window_color);
        logout_button.setForeground(Color.WHITE);
        logout_button.addActionListener(new LogoutListener(connection_page,this));

        logout_button.setFont(text_font);
        logout_button.setVerticalAlignment(JButton.NORTH);
        logout_button.setHorizontalAlignment(JButton.RIGHT);


        JPanel button_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        button_panel.setBackground(window_color);
        button_panel.add(logout_button);

        this.setBackground(window_color);
        this.setForeground(Color.WHITE);

        this.setLayout(new GridLayout(3,3));

        this.add(mail_label);
        this.add(new JLabel(""));
        this.add(button_panel);


        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,25);

        //JLabel startup_label = new JLabel("Start/Stop AFIS");
        //startup_label.setFont(label_font);
        //startup_label.setForeground(Color.WHITE);
        //this.add(startup_label);

        JPanel title_panel = new JPanel(new GridLayout(1,2));
        JPanel cash_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel launch_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        title_panel.setBackground(window_color);
        cash_panel.setBackground(window_color);
        launch_panel.setBackground(window_color);
        JLabel cash_label = new JLabel("Cash flow : ");
        int client_id = DataAccess.getIdOfClient(email);
        JLabel cash_value = new JLabel(Double.toString(DataAccess.getCashFlow(client_id)));
        CashThread cash_updater = new CashThread(cash_value,client_id);
        cash_updater.start();
        cash_label.setForeground(Color.WHITE);
        cash_label.setFont(label_font);
        cash_value.setFont(label_font);
        cash_value.setForeground(Color.WHITE);
        cash_panel.add(cash_label);
        cash_panel.add(cash_value);

        JButton launch_button = new JButton("Start/Stop AFIS");
        launch_button.addActionListener(new LaunchController(launch_button));
        launch_button.setFont(label_font);
        launch_button.setBackground(window_color);
        launch_button.setForeground(Color.WHITE);
        launch_panel.add(launch_button);

        title_panel.add(cash_panel);
        title_panel.add(launch_panel);
        //this.add(title_panel);
        this.add(cash_panel);
        this.add(launch_panel);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));

        JLabel power = new JLabel("");
        power.setIcon(new ImageIcon("../Pictures/synthesis_image.png"));
        //this.add(new JLabel(""));
        //this.add(power);

        /*JPanel money_panel = new JPanel(new GridLayout(2,1));
        money_panel.setBackground(window_color);
        JLabel financial_wealth = new JLabel("Financial wealth : ");
        JLabel invested_money = new JLabel("Invested money : ");
        financial_wealth.setForeground(Color.WHITE);
        invested_money.setForeground(Color.WHITE);
        financial_wealth.setFont(label_font);
        invested_money.setFont(label_font);
        money_panel.add(financial_wealth);
        money_panel.add(invested_money);
        this.add(money_panel);*/


        JLabel useless_one = new JLabel("");
        useless_one.setFont(label_font);

        JLabel useless_two = new JLabel("");
        useless_two.setFont(label_font);

        JLabel useless_three = new JLabel("");
        useless_three.setFont(label_font);

        //this.add(useless_one);
        //this.add(useless_two);
        //this.add(useless_three);
//        this.add(new JLabel(""));
//        this.add(new JLabel(""));
//        this.add(new JLabel(""));
    }
}
