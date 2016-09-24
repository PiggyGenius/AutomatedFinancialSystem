import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class RefreshListener implements ActionListener {
    private HistoryScreen view;
    private String client_email;

    public RefreshListener(HistoryScreen view,String client_email){
        this.view = view;
        this.client_email = client_email;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        int client_id = DataAccess.getIdOfClient(this.client_email);
        if(client_id>0){
            this.view.setClientList(DataAccess.getTransactionsOfClient(client_id));
            this.view.setScrollPanes(this.view.getWidth(),this.view.getHeight());
        }
        else {
            this.view.setErrorMessage("Your connection expired, please logout and reconnect.");
        }
    }
    public String[][][] refresh_client(){
        int client_id = DataAccess.getIdOfClient(this.client_email);
        if(client_id>0){
            return DataAccess.getTransactionsOfClient(client_id);
        }
        else {
            String[][][] error = new String[1][1][1];
            error[0][0][0]="-1";
            this.view.setErrorMessage("Your connection expired, please logout and reconnect.");
            return error;
        }
    }
    public String[][][][] refresh_group(){/*
        int client_id = DataAccess.getIdOfClient(this.client_email);
        if(client_id>0){
            return DataAccess.getTransactionsOfClient(client_id);
        }
        else {
            String[][][] error = new String[1][1][1];
            error[0][0][0]="-1";
            this.view.setErrorMessage("Your connection expired, please logout and reconnect.");
            return error;
        }*/
        int client_id = DataAccess.getIdOfClient(this.client_email);
        if(client_id>0){
            return DataAccess.getTransactionsOfGroups(client_id);
        }
        else {
            String[][][][] error = new String[1][1][1][1];
            error[0][0][0][0]="-1";
            this.view.setErrorMessage("Your connection expired, please logout and reconnect.");
        return error;
        }
    }
}
