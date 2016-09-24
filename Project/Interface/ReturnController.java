import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ReturnController implements ActionListener {
    private JPanel default_panel;

    public ReturnController(JPanel default_panel){
        this.default_panel = default_panel;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Container window = this.default_panel.getParent();
        window.removeAll();
        JPanel[] piloting_panels = ConnectionListener.getPilotingPanels();
        window.add(piloting_panels[0], BorderLayout.NORTH);
        window.add(piloting_panels[1], BorderLayout.CENTER);
        window.add(piloting_panels[2], BorderLayout.SOUTH);
        window.validate();
        window.repaint();
    }
}
