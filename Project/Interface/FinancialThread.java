import javax.swing.*;
import java.lang.Thread;

public class FinancialThread extends Thread {
    private JLabel financial_value;
    private int client_id;

    public FinancialThread(JLabel financial_value,int client_id){
        this.financial_value=financial_value;
        this.client_id=client_id;
    }

    @Override
    public void run(){
        String value;
        double nombre;
        for(;;){
            nombre = DataAccess.getSellingValue(client_id);
            if(nombre != -1){
                value = String.format("%.2f",nombre);
                value = "  "+value+"  ";
                this.financial_value.setText(value);
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException e){
                }
            }
        }
    }
}
