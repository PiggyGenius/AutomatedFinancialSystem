import java.lang.ProcessBuilder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class LaunchController implements ActionListener {
    private int launch;
    private Process process;
    private Color window_color;
    private JButton button;

    public LaunchController(JButton button){
        this.button = button;
        this.launch = 1;
        this.window_color = new Color(33,36,38);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        this.launch=(this.launch+1)%2;
        if(launch==0){
            try {
                this.process = new ProcessBuilder("CountToInfinite.exe"/*,"useless_1","useless_2"*/).start();
                this.button.setBackground(Color.GREEN);
            } catch(IOException ex){
                System.out.println("Cannot launch AFIS."+ex.getMessage());
            }
        }
        else {
            this.process.destroy();
            this.button.setBackground(window_color);
        }
    }
}
