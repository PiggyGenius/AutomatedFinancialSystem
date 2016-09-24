import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class PortfolioCenterScreen extends JPanel {
    private static JScrollPane scroll_bar;
    private static JLabel title_label;
    private static JPanel transaction_panel;
    private static JPanel data_panel;
    private static int client_id;

    public PortfolioCenterScreen(String email){
        Color window_color = new Color(33,36,38);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,35);
        Font specific_font = new Font(Font.SANS_SERIF,Font.PLAIN,50);
        Font useless_font = new Font(Font.SANS_SERIF,Font.PLAIN,35);
        Font title_font = new Font(Font.SANS_SERIF,Font.PLAIN,20);
        Border title_border = BorderFactory.createLineBorder(Color.BLACK,2);
        Border transaction_border = BorderFactory.createLineBorder(Color.BLACK,3);

        this.setBackground(window_color);
        this.setLayout(new BorderLayout());


        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setBackground(window_color);



        JPanel financial_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        financial_panel.setBackground(window_color);
        JPanel value_panel = new JPanel();
        value_panel.setLayout(new BoxLayout(value_panel,BoxLayout.Y_AXIS));
        value_panel.setBackground(window_color);


        client_id = DataAccess.getIdOfClient(email);
        String value_string;
        JLabel financial_wealth = new JLabel(" Financial value ");
        value_string = String.format("%.2f",DataAccess.getSellingValue(client_id));
    value_string = "  "+value_string+"  ";
    JLabel financial_value = new JLabel(value_string);
    FinancialThread financial_updater = new FinancialThread(financial_value,client_id);
    financial_updater.start();
    financial_wealth.setForeground(Color.WHITE);
    financial_wealth.setFont(label_font);
    financial_value.setFont(label_font);
    financial_value.setForeground(Color.WHITE);
    financial_panel.add(financial_wealth);
    value_panel.add(new JLabel(" "));
    value_panel.add(financial_value);
    value_panel.add(new JLabel(" "));
    value_panel.setBorder(title_border);
    JPanel value_container = new JPanel();
    value_container.setBackground(window_color);
    value_container.add(value_panel);

    JLabel useless_label = new JLabel(" ");
    useless_label.setFont(useless_font);

    container.add(financial_panel);
    container.add(useless_label);
    //container.add(value_panel);
    container.add(value_container);
    //this.add(container, BorderLayout.CENTER);

        JPanel center_panel = new JPanel();
        center_panel.setLayout(new BoxLayout(center_panel,BoxLayout.Y_AXIS));
        center_panel.setBackground(window_color);
        center_panel.add(container);

        transaction_panel = new JPanel();
        transaction_panel.setBackground(window_color);
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);
        title_label = new JLabel(" Transactions ");
        title_label.setBackground(window_color);
        title_label.setForeground(Color.WHITE);
        title_label.setFont(title_font);
        transaction_panel.setBorder(transaction_border);
        transaction_panel.add(title_label, BorderLayout.SOUTH);

        data_panel = new JPanel();
        //JLabel data;
        data_panel.setLayout(new BoxLayout(data_panel,BoxLayout.Y_AXIS));
        data_panel.setBackground(window_color);
        /*for(int i=0;i<10;i++){
            data = new JLabel("                    "+i+"           ");
            data.setBackground(window_color);
            data.setForeground(Color.WHITE);
            data.setFont(title_font);
            data_panel.add(data);
        }*/

        scroll_bar = new JScrollPane(data_panel);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_bar.setBackground(window_color);
        scroll_bar.setForeground(Color.WHITE);


        JLabel useless_label_2 = new JLabel(" ");
        useless_label_2.setFont(label_font);
        JLabel useless_label_3 = new JLabel(" ");
        useless_label_3.setFont(label_font);
        JLabel useless_label_4 = new JLabel(" ");
        useless_label_4.setFont(specific_font);

        
        center_panel.add(useless_label_2);
        center_panel.add(useless_label_3);
        center_panel.add(useless_label_4);
        center_panel.add(transaction_panel);
        center_panel.add(scroll_bar);
        this.add(center_panel);
    }
    public static void setScrollPane(int width,int height){
        scroll_bar.setPreferredSize(new Dimension(width,92));
        scroll_bar.setMinimumSize(new Dimension(width,92));
        transaction_panel.setPreferredSize(new Dimension(width,transaction_panel.getHeight()));
        String[][][] list = DataAccess.getPortfolioTransactionsOfClient(client_id);
        ClientTransactionThread client_thread = new ClientTransactionThread(list,data_panel,client_id);
        client_thread.start();
    }
}
