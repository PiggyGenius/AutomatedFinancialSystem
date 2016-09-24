import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.math.*;

public class PortfolioRightScreen extends JPanel {
    private static JScrollPane scroll_bar;
    private static int index;

    public PortfolioRightScreen(String email){
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
        String[] column_names = {" Type  "," Company "," Quantity "," Price "," Use Price "};
        JLabel[] column = new JLabel[5];


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.weighty = 1;


        JLabel title = new JLabel(" Options ", JLabel.CENTER);
        title.setFont(title_font);
        title.setForeground(Color.WHITE);
        title.setBorder(border);
        grid_bag.setConstraints(title,constraints);
        container.add(title);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;

        for(int i=0;i<5;i++){
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

        String[][] warrants = DataAccess.getPortfolioWarrantsOfClient(client_id);
        String[][] trackers = DataAccess.getPortfolioTrackersOfClient(client_id);
        if(warrants[0][0].equals("0") && trackers[0][0].equals("0"))
            this.add(container);
        else if(warrants[0][0].equals("-1") || trackers[0][0].equals("-1")){
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
            if(!(warrants[0][0].equals("0"))){
                for(i=0;i<warrants.length;i++){
                    constraints.gridy+=1;
                        for(int j=0;j<warrants[i].length;j++){
                            constraints.gridx=j;
                            if(j<3)
                                data = new JLabel(" "+warrants[i][j]+" ", JLabel.CENTER);
                            else {
                                value = Double.parseDouble(warrants[i][j]);
                                data = new JLabel(" "+String.format("%.2f",value)+" ", JLabel.CENTER);
                            }
                            data.setFont(label_font);
                            data.setForeground(Color.WHITE);
                            data.setBorder(border);
                            grid_bag.setConstraints(data,constraints);
                            container.add(data);
                    }
                }
            }
            if(!(trackers[0][0].equals("0"))){
                for(i=0;i<trackers.length;i++){
                    constraints.gridy+=1;
                        for(int j=0;j<trackers[i].length;j++){
                            constraints.gridx=j;
                            if(j<3)
                                data = new JLabel(" "+trackers[i][j]+" ", JLabel.CENTER);
                            else {
                                value = Float.parseFloat(trackers[i][j]);
                                data = new JLabel(" "+String.format("%.2f",value)+" ", JLabel.CENTER);
                            }
                            data.setFont(label_font);
                            data.setForeground(Color.WHITE);
                            data.setBorder(border);
                            grid_bag.setConstraints(data,constraints);
                            container.add(data);
                    }
                }
            }
            for(i=0;i<20;i++){
                constraints.gridy+=1;
                for(int j=0;j<5;j++){
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
            constraints.gridheight = 10;//constraints.gridy;
            grid_bag.setConstraints(scroll_bar,constraints);
            this.add(scroll_bar, BorderLayout.CENTER);
        }
    }
    public static void setScrollPane(int width,int height){
        if(index>=20)
            scroll_bar.setPreferredSize(new Dimension(width,height));
    }
}
