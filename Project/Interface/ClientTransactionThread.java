import javax.swing.*;
import java.lang.Thread;
import java.awt.*;

public class ClientTransactionThread extends Thread {
    private JPanel data_panel;
    private String[][][] list;
    private int client_id;

    public ClientTransactionThread(String[][][] list,JPanel data_panel,int client_id){
        this.list = list;
        this.data_panel = data_panel;
        this.client_id = client_id;
    }

    @Override
    public void run(){
        double value;
        JLabel data;
        Color window_color = new Color(33,36,38);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        String[][][] last_list;
        for(;;){
            last_list = DataAccess.getPortfolioTransactionsOfClient(this.client_id);
            if(this.list[0][0][0]=="0" || last_list[0][0][0]=="0")
                ;
            else if(this.list[0][0][0]=="-1" || last_list[0][0][0]=="-1"){
                data = new JLabel("Connection to database lost.");
                data.setFont(label_font);
                data.setBackground(window_color);
                data.setForeground(Color.WHITE);
                this.data_panel.add(data);
            }
            else {
                for(int x=0;x<last_list.length;x++){
                    for(int y=this.list[x].length;y<last_list[x].length;y++){
                        if(x==0){
                            data = new JLabel(last_list[x][y][0]+" "+last_list[x][y][2]+" "+last_list[x][y][1]+" stock for "+String.format("%.2f",Double.valueOf(last_list[x][y][3]))+"$. Actual Price was : "+String.format("%.2f",Double.valueOf(last_list[x][y][4]))+" per stock.");
                            data.setFont(label_font);
                            data.setBackground(window_color);
                            data.setForeground(Color.WHITE);
                            this.data_panel.add(data);
                        }
                        if(x==1){
                            data = new JLabel(last_list[x][y][0]+" "+last_list[x][y][3]+" "+last_list[x][y][1]+" warrant of "+last_list[x][y][2]+" for "+String.format("%.2f",Double.valueOf(last_list[x][y][4]))+"$ exercise price is : "+String.format("%.2f",Double.valueOf(last_list[x][y][5]))+".");
                            data.setFont(label_font);
                            data.setBackground(window_color);
                            data.setForeground(Color.WHITE);
                            this.data_panel.add(data);
                    }
                        if(x==2){
                            data = new JLabel(last_list[x][y][0]+" "+last_list[x][y][3]+" "+last_list[x][y][1]+" tracker of "+last_list[x][y][2]+" for "+String.format("%.2f",Double.valueOf(last_list[x][y][4]))+"$ exercise price is : "+last_list[x][y][5]+".");
                            data.setFont(label_font);
                            data.setBackground(window_color);
                            data.setForeground(Color.WHITE);
                            this.data_panel.add(data);
                        }
                    }
                }
                this.list=last_list;
            }
            this.data_panel.revalidate();
            this.data_panel.repaint();
            try {
                Thread.sleep(3000);
            } catch(InterruptedException e){
            }
        }
    }
}
