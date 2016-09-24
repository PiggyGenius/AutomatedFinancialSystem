import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class GroupScreen extends JTabbedPane {
    private String email;

    public GroupScreen(String email){
        this.email=email;
        Color window_color = new Color(33,36,38);
        this.setBackground(window_color);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,35);
        Border border = BorderFactory.createLineBorder(Color.BLACK,2);



        JTabbedPane portfolio = new JTabbedPane();
        JTabbedPane group_tab = new JTabbedPane();
        this.addTab("Portfolio",null,portfolio,"Visualize group portfolios");
        this.setMnemonicAt(0,KeyEvent.VK_1);
        this.addTab("Group Manager",null,group_tab,"Manage investing groups");
        this.setMnemonicAt(1,KeyEvent.VK_2);

        int[] group_ids = DataAccess.getGroupIds(DataAccess.getIdOfClient(email));
        JTabbedPane[] groups = new JTabbedPane[group_ids.length];
        JPanel container,small_container;
        JLabel data;
        String[] sentence = {"Selling Value","Cash Flow","Invest Money","Money Won Overall","Net Money Won Overall"};
        double[][] values = DataAccess.getGroupPortfolio(group_ids);
        for(int i=0;i<values.length;i++){
            groups[i] = new JTabbedPane();
            groups[i].setBackground(window_color);
            container = new JPanel(new GridLayout(2,2));
            container.setBackground(window_color);
            for(int j=0;j<values[i].length-1;j++){
                data = new JLabel(sentence[j]+" : "+String.format("%.2f",values[i][j]));
                data.setBackground(window_color);
                data.setForeground(Color.WHITE);
                data.setFont(label_font);
                small_container = new JPanel();
                small_container.setBackground(window_color);
                small_container.add(data);
                container.add(small_container);
            }
            portfolio.addTab(DataAccess.getNameOfGroup(group_ids[i]),null,container,DataAccess.getNameOfGroup(group_ids[i]));
        }

/*
        JPanel[] containers = new JPanel[4];
        JLabel data = new JLabel("");
        containers[0] = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        data = new JLabel("Ceci est a droite");
        containers[0].add(data);
        containers[0].setBackground(window_color);

        containers[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        data = new JLabel("Ceci est a gauche");
        containers[1].add(data);
        containers[1].setBackground(window_color);

        containers[2] = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        data = new JLabel("Ceci est a droite");
        containers[2].add(data);
        containers[2].setBackground(window_color);

        containers[3] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        data = new JLabel("Ceci est a gauche");
        containers[3].add(data);
        containers[3].setBackground(window_color);

        JPanel main_panel = new JPanel();
        main_panel.setBackground(window_color);
        main_panel.setLayout(new GridLayout(4,3));


        main_panel.add(new JPanel());
        main_panel.add(new JPanel());
        main_panel.add(new JPanel());


        main_panel.add(containers[0]);
        main_panel.add(new JPanel());
        main_panel.add(containers[1]);

        main_panel.add(new JPanel());
        main_panel.add(new JPanel());
        main_panel.add(new JPanel());

        main_panel.add(containers[2]);
        main_panel.add(new JPanel());
        main_panel.add(containers[3]);


        portfolio.add(main_panel);
        */
    }
}
