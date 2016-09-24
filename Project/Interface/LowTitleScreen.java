import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class LowTitleScreen extends JPanel{
    public LowTitleScreen(int client_id){
        Color window_color = new Color(33,36,38);
        Font text_font = new Font(Font.SANS_SERIF,Font.PLAIN,20);
        this.setBackground(window_color);
        this.setForeground(Color.WHITE);
        this.setLayout(new GridLayout(2,3));
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,25);

        JPanel financial_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        financial_panel.setBackground(window_color);
        JLabel financial_wealth = new JLabel("Financial wealth : ");
        JLabel financial_value = new JLabel(Double.toString(DataAccess.getSellingValue(client_id)));
        financial_panel.add(financial_wealth);
        financial_panel.add(financial_value);

        JPanel invested_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        invested_panel.setBackground(window_color);
        JLabel invested_money = new JLabel("Invested money : ");
        JLabel invested_value = new JLabel(Double.toString(DataAccess.getInvestMoney(client_id)));
        invested_panel.add(invested_money);
        invested_panel.add(invested_value);

        financial_wealth.setForeground(Color.WHITE);
        financial_value.setForeground(Color.WHITE);
        invested_money.setForeground(Color.WHITE);
        invested_value.setForeground(Color.WHITE);

        financial_wealth.setFont(label_font);
        financial_value.setFont(label_font);
        invested_money.setFont(label_font);
        invested_value.setFont(label_font);

        MoneyThread money_thread = new MoneyThread(financial_value,invested_value,client_id);
        money_thread.start();


        JLabel useless_one = new JLabel("");
        useless_one.setFont(label_font);

        JLabel useless_two = new JLabel("");
        useless_two.setFont(label_font);

        JLabel useless_three = new JLabel("");
        useless_three.setFont(label_font);

        this.add(useless_one);
        this.add(useless_two);
        this.add(useless_three);

        this.add(financial_panel);
        this.add(new JLabel(""));
        this.add(invested_panel);
    }
}
