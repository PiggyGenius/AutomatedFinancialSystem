import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class LogoutListener implements ActionListener {
    private ConnectionPage connection_page;
    private JPanel default_panel;

    public LogoutListener(ConnectionPage connection_page, JPanel default_panel){
        this.connection_page = connection_page;
        this.default_panel = default_panel;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Container window = this.default_panel.getParent();
        window.removeAll();
        window.add(this.connection_page);
        window.validate();
        window.repaint();
    }
}
