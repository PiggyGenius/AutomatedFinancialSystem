import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.math.*;

public class PortfolioLeftScreen extends JPanel {
    private static JScrollPane scroll_bar;
    private static int index;

    public PortfolioLeftScreen(String email){
        int client_id = DataAccess.getIdOfClient(email);
        Color window_color = new Color(33,36,38);
        this.setBackground(window_color);
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,17);
        Font title_font = new Font(Font.SANS_SERIF,Font.PLAIN,23);
        JPanel container = new JPanel();
        container.setBackground(window_color);
        GridBagLayout grid_bag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        container.setLayout(grid_bag);
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);
        Border column_border = BorderFactory.createLineBorder(Color.BLACK,2);
        MatteBorder stock_border = new MatteBorder(0,0,0,2,Color.BLACK);
        container.setBorder(border);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        String[] column_names = {" Company "," Quantity "," Price "," Quote "};
        JLabel[] column = new JLabel[4];


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.weighty = 1;

        JLabel title = new JLabel(" Stocks ", JLabel.CENTER);
        title.setFont(title_font);
        title.setForeground(Color.WHITE);
        title.setBorder(border);
        grid_bag.setConstraints(title,constraints);
        container.add(title);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;

        for(int i=0;i<4;i++){
            constraints.gridx = i;
            column[i] = new JLabel(column_names[i], JLabel.CENTER);
            column[i].setFont(label_font);
            column[i].setForeground(Color.WHITE);
            column[i].setBorder(column_border);
            grid_bag.setConstraints(column[i],constraints);
            container.add(column[i]);
        }


        constraints.gridx = 0;
        constraints.gridy = 1;

        String[][] stocks = DataAccess.getPortfolioStocksOfClient(client_id);
        if(stocks[0][0].equals("0") || stocks[0][0].equals("-1"))
            this.add(container);
        else if(stocks[0][0].equals("-1")){
            constraints.gridy = 3;
            constraints.gridwidth=4;
            JLabel error = new JLabel("Connection to database lost", JLabel.CENTER);
            error.setFont(label_font);
            error.setForeground(Color.WHITE);
            grid_bag.setConstraints(error,constraints);
            container.add(error);
            this.add(container);
        }
        else {
            JLabel data;
            double value;
            BigDecimal bd, res;
            int i;
            for(i=0;i<stocks.length;i++){
                constraints.gridy+=1;
                    for(int j=0;j<stocks[i].length;j++){
                        constraints.gridx=j;
                        if(j<2)
                            data = new JLabel(" "+stocks[i][j]+" ", JLabel.CENTER);
                        else {
                            value = Double.parseDouble(stocks[i][j]);
                            data = new JLabel(" "+String.format("%.2f",value)+" ", JLabel.CENTER);
                        }
                        data.setFont(label_font);
                        data.setForeground(Color.WHITE);
                        data.setBorder(border);
                        //data.setBorder(stock_border);
                        grid_bag.setConstraints(data,constraints);
                        container.add(data);
                }
            }
            for(i=0;i<20;i++){
                constraints.gridy+=1;
                for(int j=0;j<4;j++){
                    constraints.gridx=j;
                    data = new JLabel(" ");
                    data.setFont(label_font);
                    data.setBorder(border);
                    grid_bag.setConstraints(data,constraints);
                    container.add(data);
                }
            }
            index=constraints.gridy;
            scroll_bar = new JScrollPane(container);
            scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll_bar.setBackground(window_color);
            scroll_bar.setForeground(Color.WHITE);
            constraints.gridx = 5;
            constraints.gridy = 0;
            constraints.weightx=1.0;
            constraints.weighty=1.0;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridheight = 10;
            grid_bag.setConstraints(scroll_bar,constraints);
            this.add(scroll_bar, BorderLayout.CENTER);
        }
    }
    public static void setScrollPane(int width,int height){
        if(index>=20)
            scroll_bar.setPreferredSize(new Dimension(width,height));
    }
}
