import javax.swing.*;
import java.lang.Thread;

public class MoneyThread extends Thread {
    private JLabel invested_money;
    private JLabel financial_value;
    private int client_id;

    public MoneyThread(JLabel financial_value,JLabel invested_money,int client_id){
        this.financial_value=financial_value;
        this.invested_money=invested_money;
        this.client_id=client_id;
    }

    @Override
    public void run(){
        for(;;){
            this.financial_value.setText(String.format("%.2f",DataAccess.getSellingValue(client_id)));
            this.invested_money.setText(String.format("%.2f",DataAccess.getInvestMoney(client_id)));
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e){
            }
        }
    }
}
