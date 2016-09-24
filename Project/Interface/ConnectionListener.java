import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ConnectionListener implements ActionListener {
    private JTextField email;
    private JTextField password;
    private DataAccess data_model;
    private JLabel error_message;
    private static ConnectionPage connection_page;
    private static TitleScreen title_screen;
    private static PilotingScreen piloting_screen;
    private static LowTitleScreen low_title_screen;

    public ConnectionListener(JTextField email, JTextField password, JLabel error_message, ConnectionPage connection_page){
        this.email = email;
        this.password = password;
        this.data_model = new DataAccess();
        this.error_message = error_message;
        this.connection_page = connection_page;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        this.error_message.setText("");
        String str_email = String.format(this.email.getText());
        String str_password = String.format(this.password.getText());
        int conditioner = DataAccess.clientAuthentification(str_email,str_password);
        if(conditioner == 1){
            Container window = this.connection_page.getParent();
            window.remove(connection_page);
        
            this.title_screen = new TitleScreen(str_email,this.connection_page);
            PilotingController piloting_controller = new PilotingController(this.data_model,title_screen,str_email);
            this.piloting_screen = new PilotingScreen(piloting_controller,str_email,window);
            window.setLayout(new BorderLayout());
            window.add(title_screen, BorderLayout.NORTH);
            window.add(piloting_screen, BorderLayout.CENTER);
            this.low_title_screen = new LowTitleScreen(DataAccess.getIdOfClient(str_email));
            window.add(this.low_title_screen, BorderLayout.SOUTH);
            window.validate();
            window.repaint();
        }
        else if(conditioner == 0){
            this.error_message.setText("Authentification Failure.");
        }
        else if(conditioner == -1){
            this.error_message.setText("Could not reach server.");
        } 
    }
    public static ConnectionPage getConnectionPage(){
        return connection_page;
    }
    public static JPanel[] getPilotingPanels(){
        JPanel[] panels = new JPanel[3];
        panels[0]=title_screen;
        panels[1]=piloting_screen;
        panels[2]=low_title_screen;
        return panels;
    }
}
