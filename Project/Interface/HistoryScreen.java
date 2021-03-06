import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class HistoryScreen extends JTabbedPane {
    private String[][][] client_list;
    private String[][][][] group_list;
    private JLabel[][] stocks;
    private JLabel error_label;
    private JPanel client_stocks, client_warrants, client_trackers, group_stocks, group_warrants, group_trackers;
    private GridBagLayout grid_bag;
    private GridBagConstraints constraints;
    private static JScrollPane[] scroll_bar;
    private static JTabbedPane[] groups;
    private static JTabbedPane group;
    private static int[] index;
    private static String email;

    public HistoryScreen(Container window,String mail){
        Color window_color = new Color(33,36,38);
        window.setBackground(window_color);
        this.setBackground(window_color);
        this.email = mail;
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        Border border = BorderFactory.createLineBorder(Color.BLACK,2);


        JTabbedPane client = new JTabbedPane();


        this.grid_bag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        this.constraints.fill=GridBagConstraints.BOTH;
        this.constraints.anchor=GridBagConstraints.CENTER;
        this.constraints.weightx=1.0;
        JLabel title;

        String[] client_stocks_title = {"Company","Operation","Operation cost","Stock quote","Quantity","Time of transaction"};
        this.client_stocks = new JPanel();
        this.client_stocks.setLayout(this.grid_bag);
        this.client_stocks.setBackground(window_color);
        this.constraints.gridx=0;
        this.constraints.gridy=0;
        for(int i=0;i<6;i++){
            this.constraints.gridx=i;
            title = new JLabel(client_stocks_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            this.grid_bag.setConstraints(title,this.constraints);
            this.client_stocks.add(title);
        }



        String[] client_warrants_title = {"Option ISIN","Company","Operation","Operation cost","Option price","Stock quote","Quantity","Time of transaction"};
        this.client_warrants = new JPanel();
        this.client_warrants.setLayout(this.grid_bag);
        this.client_warrants.setBackground(window_color);
        this.constraints.gridx=0;
        this.constraints.gridy=0;
        for(int i=0;i<8;i++){
            this.constraints.gridx=i;
            title = new JLabel(client_warrants_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            this.grid_bag.setConstraints(title,this.constraints);
            this.client_warrants.add(title);
        }


        
        String[] client_trackers_title = {"Option ISIN","Company","Operation","Operation cost","Option price","Stock quote","Quantity","Time of transaction"};
        this.client_trackers = new JPanel();
        this.client_trackers.setLayout(this.grid_bag);
        this.client_trackers.setBackground(window_color);
        this.constraints.gridx=0;
        this.constraints.gridy=0;
        for(int i=0;i<8;i++){
            this.constraints.gridx=i;
            title = new JLabel(client_trackers_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            this.grid_bag.setConstraints(title,this.constraints);
            this.client_trackers.add(title);
        }


/*
        client.addTab("Stocks",null,this.client_stocks,"See stocks transactions");
        client.setMnemonicAt(0, KeyEvent.VK_1);
        client.addTab("Warrants",null,this.client_warrants,"See warrants transactions");
        client.setMnemonicAt(1, KeyEvent.VK_2);
        client.addTab("Trackers",null,this.client_trackers,"See trackers transactions");
        client.setMnemonicAt(2, KeyEvent.VK_3);
*/


        group = new JTabbedPane();

        this.group_stocks = new JPanel();
        this.group_stocks.setLayout(this.grid_bag);
        this.group_stocks.setBackground(window_color);

        this.group_warrants = new JPanel();
        this.group_warrants.setLayout(this.grid_bag);
        this.group_warrants.setBackground(window_color);

        this.group_trackers = new JPanel();
        this.group_trackers.setLayout(this.grid_bag);
        this.group_trackers.setBackground(window_color);


/*
        group.addTab("Stocks",null,this.group_stocks,"See stocks transactions");
        group.setMnemonicAt(0, KeyEvent.VK_1);
        group.addTab("Warrants",null,this.group_warrants,"See warrants transactions");
        group.setMnemonicAt(1, KeyEvent.VK_2);
        group.addTab("Trackers",null,this.group_trackers,"See trackers transactions");
        group.setMnemonicAt(2, KeyEvent.VK_3);
*/
//
        scroll_bar = new JScrollPane[6];
        index = new int[6];
        scroll_bar[0] = new JScrollPane(this.client_stocks);
        scroll_bar[1] = new JScrollPane(this.client_warrants);
        scroll_bar[2] = new JScrollPane(this.client_trackers);
        /*scroll_bar[3] = new JScrollPane(this.group_stocks);
        scroll_bar[4] = new JScrollPane(this.group_warrants);
        scroll_bar[5] = new JScrollPane(this.group_trackers);


        groups.addTab("Stocks",null,scroll_bar[3],"See stocks transactions");
        groups.setMnemonicAt(0, KeyEvent.VK_1);
        groups.addTab("Warrants",null,scroll_bar[4],"See warrants transactions");
        groups.setMnemonicAt(1, KeyEvent.VK_2);
        groups.addTab("Trackers",null,scroll_bar[5],"See trackers transactions");
        groups.setMnemonicAt(2, KeyEvent.VK_3);*/



        client.addTab("Stocks",null,scroll_bar[0],"See stocks transactions");
        client.setMnemonicAt(0, KeyEvent.VK_1);
        client.addTab("Warrants",null,scroll_bar[1],"See warrants transactions");
        client.setMnemonicAt(1, KeyEvent.VK_2);
        client.addTab("Trackers",null,scroll_bar[2],"See trackers transactions");
        client.setMnemonicAt(2, KeyEvent.VK_3);


//


        this.addTab("Individual",null,client,"See client transactions");
        this.setMnemonicAt(0, KeyEvent.VK_1);
        this.addTab("Group",null,group,"See group transactions");
        this.setMnemonicAt(1, KeyEvent.VK_2);
    }

    public void setClientList(String[][][] list){
        if(list[0][0][0].equals("-1"))
            return ;
        this.client_list=list;
        int begin=0;
        JLabel data_label;
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        for(int x=0;x<this.client_list.length;x++){
            if(this.client_list[x][0][0].equals("0"))
                begin=1;
            for(int y=begin;y<this.client_list[x].length;y++){
                this.constraints.gridy=y+1;
                for(int z=0;z<this.client_list[x][y].length;z++){
                    this.constraints.gridx=z;
                    if(x==0){
                        data_label = new JLabel(this.client_list[x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.client_stocks.add(data_label);
                    }
                    else if(x==1){
                        data_label = new JLabel(this.client_list[x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.client_warrants.add(data_label);
                    }
                    else if(x==2){
                        data_label = new JLabel(this.client_list[x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.client_trackers.add(data_label);
                    }
                }
                begin=0;
            }
            index[x]=this.constraints.gridy;
        }
    }

    public void setGroupList(String[][][][] list){
        Color window_color = new Color(33,36,38);
        group.setBackground(window_color);
        if(list[0][0][0][0].equals("0") || list[0][0][0][0].equals("-1"))
            return ;

        int[] group_ids = DataAccess.getGroupIds(DataAccess.getIdOfClient(email));
        this.groups = new JTabbedPane[list.length];
        for(int k=0;k<list.length;k++)
            this.groups[k] = new JTabbedPane();

        this.group_list=list;
        int begin=0;
        JLabel data_label, title;
        Border border = BorderFactory.createLineBorder(Color.BLACK,2);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        String[] client_stocks_title = {"Company","Operation","Operation cost","Stock quote","Quantity","Time of transaction"};
        String[] client_warrants_title = {"Option ISIN","Company","Operation","Operation cost","Option price","Stock quote","Quantity","Time of transaction"};
        String[] client_trackers_title = {"Option ISIN","Company","Operation","Operation cost","Option price","Stock quote","Quantity","Time of transaction"};
        for(int g=0;g<list.length;g++){
        group_stocks = new JPanel();
        group_stocks.setLayout(this.grid_bag);
        group_stocks.setBackground(window_color);
        constraints.gridx=0;
        constraints.gridy=0;
        for(int i=0;i<6;i++){
            constraints.gridx=i;
            title = new JLabel(client_stocks_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            grid_bag.setConstraints(title,constraints);
            group_stocks.add(title);
        }
        group_warrants = new JPanel();
        group_warrants.setLayout(grid_bag);
        group_warrants.setBackground(window_color);
        constraints.gridx=0;
        constraints.gridy=0;
        for(int i=0;i<8;i++){
            constraints.gridx=i;
            title = new JLabel(client_warrants_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            grid_bag.setConstraints(title,constraints);
            group_warrants.add(title);
        }
        group_trackers = new JPanel();
        group_trackers.setLayout(grid_bag);
        group_trackers.setBackground(window_color);
        constraints.gridx=0;
        constraints.gridy=0;
        for(int i=0;i<8;i++){
            constraints.gridx=i;
            title = new JLabel(client_trackers_title[i], JLabel.CENTER);
            title.setBackground(window_color);
            title.setForeground(Color.WHITE);
            title.setFont(label_font);
            title.setBorder(border);
            grid_bag.setConstraints(title,constraints);
            group_trackers.add(title);
        }
        for(int x=0;x<this.group_list[g].length;x++){
            if(this.group_list[g][x][0][0].equals("0")){
                begin=1;
            }
            for(int y=begin;y<this.group_list[g][x].length;y++){
                this.constraints.gridy=y+1;
                for(int z=0;z<this.group_list[g][x][y].length;z++){
                    this.constraints.gridx=z;
                    if(x==0){
                        data_label = new JLabel(this.group_list[g][x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.group_stocks.add(data_label);
                    }
                    else if(x==1){
                        data_label = new JLabel(this.group_list[g][x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.group_warrants.add(data_label);
                    }
                    else if(x==2){
                        data_label = new JLabel(this.group_list[g][x][y][z], JLabel.CENTER);
                        data_label.setForeground(Color.WHITE);
                        data_label.setFont(label_font);
                        this.grid_bag.setConstraints(data_label,this.constraints);
                        this.group_trackers.add(data_label);
                    }
                }
            }
            begin=0;
            index[x+3]=this.constraints.gridy;
        }

        scroll_bar[3] = new JScrollPane(group_stocks);
        scroll_bar[4] = new JScrollPane(group_warrants);
        scroll_bar[5] = new JScrollPane(group_trackers);

        groups[g].addTab("Stocks",null,scroll_bar[3],"See stocks transactions");
        groups[g].setMnemonicAt(0,KeyEvent.VK_1);
        groups[g].addTab("Warrants",null,scroll_bar[4],"See warrants transactions");
        groups[g].setMnemonicAt(0,KeyEvent.VK_2);
        groups[g].addTab("Trackers",null,scroll_bar[5],"See trackers transactions");
        groups[g].setMnemonicAt(0,KeyEvent.VK_3);



        groups[g].addTab("Stocks",null,scroll_bar[3],"See stocks transactions");
        groups[g].setMnemonicAt(0, KeyEvent.VK_1);
        groups[g].addTab("Warrants",null,scroll_bar[4],"See warrants transactions");
        groups[g].setMnemonicAt(1, KeyEvent.VK_2);
        groups[g].addTab("Trackers",null,scroll_bar[5],"See trackers transactions");
        groups[g].setMnemonicAt(2, KeyEvent.VK_3);

        group_stocks = new JPanel();
        group_stocks.setLayout(grid_bag);
        group_stocks.setBackground(window_color);

        group_warrants = new JPanel();
        group_warrants.setLayout(grid_bag);
        group_warrants.setBackground(window_color);

        group_trackers = new JPanel();
        group_trackers.setLayout(grid_bag);
        group_trackers.setBackground(window_color);


        scroll_bar[3] = new JScrollPane(group_stocks);
        scroll_bar[4] = new JScrollPane(group_warrants);
        scroll_bar[5] = new JScrollPane(group_trackers);

        group.addTab(DataAccess.getNameOfGroup(group_ids[g]),null,groups[g],DataAccess.getNameOfGroup(group_ids[g]));
        constraints.gridx=0;
        constraints.gridy=0;
    }
    }

    public void setErrorMessage(String message){
        this.error_label.setText(message);
        this.client_stocks.add(this.error_label);
        this.client_warrants.add(this.error_label);
        this.client_trackers.add(this.error_label);
        this.group_stocks.add(this.error_label);
        this.group_warrants.add(this.error_label);
        this.group_trackers.add(this.error_label);
    }

    public static void setScrollPanes(int width,int height){
        for(int i=0;i<6;i++){
            if(index[i]>=20)
                scroll_bar[i].setPreferredSize(new Dimension(width,height));
        }
    }
}
