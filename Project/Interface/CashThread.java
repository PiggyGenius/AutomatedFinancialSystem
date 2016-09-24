import javax.swing.*;
import java.lang.Thread;

public class CashThread extends Thread {
    private JLabel cash_label;
    private int client_id;

    public CashThread(JLabel cash_label,int client_id){
        this.cash_label=cash_label;
        this.client_id=client_id;
    }

    @Override
    public void run(){
        double value;
        for(;;){
            this.cash_label.setText(String.format("%.2f",DataAccess.getCashFlow(client_id)));
            try {
                Thread.sleep(6000);
            } catch(InterruptedException e){
            }
        }
    }
}
