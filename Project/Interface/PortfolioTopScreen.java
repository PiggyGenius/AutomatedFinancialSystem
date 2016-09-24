import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PortfolioTopScreen extends JPanel {
    public PortfolioTopScreen(String email){
        Color window_color = new Color(33,36,38);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,20);
        this.setLayout(new GridLayout(2,3));
        this.setBackground(window_color);
        JPanel left_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel right_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        left_panel.setBackground(window_color);
        right_panel.setBackground(window_color);

        JButton return_button = new JButton("Return");
        return_button.addActionListener(new ReturnController(this));
        return_button.setBackground(window_color);
        return_button.setForeground(Color.WHITE);
        return_button.setFont(label_font);
        left_panel.add(return_button);


        int client_id = DataAccess.getIdOfClient(email);
        JLabel cash_label = new JLabel("Cash Flow : ");
        JLabel cash_value = new JLabel(Double.toString(DataAccess.getCashFlow(client_id)));
        CashThread cash_updater = new CashThread(cash_value,client_id);
        cash_updater.start();
        cash_label.setForeground(Color.WHITE);
        cash_label.setFont(label_font);
        cash_value.setFont(label_font);
        cash_value.setForeground(Color.WHITE);
        right_panel.add(cash_label);
        right_panel.add(cash_value);

        this.add(left_panel);
        this.add(new JLabel(""));
        this.add(right_panel);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    }
}
