import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.lang.*;
import java.util.*;
import java.lang.Exception.*;
import java.text.ParseException;

public class ProfileController implements ActionListener {
    private JTextField[] answers;
    private JLabel[] errors;
    private JPanel default_panel;
    private String email;

    public ProfileController(String email,JTextField[] answers,JLabel[] errors,JPanel default_panel){
        this.answers = answers;
        this.errors = errors;
        this.default_panel=default_panel;
        this.email=email;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String[] string_answer = new String[10];
        float[] answer_values = new float[8];
        float risk_rate=0;
        Number number;
        boolean update;
        boolean[] decision = new boolean[3];
        for(int i=0;i<10;i++){
            string_answer[i] = String.format(this.answers[i].getText());
            if(string_answer[i].equals("")){
                this.errors[i].setText("      Fill it in");
                return ;
            }
            else
                this.errors[i].setText(" ");
            if(i<7){
                try {
                    number = NumberFormat.getNumberInstance(Locale.ENGLISH).parse(string_answer[i]);
                    answer_values[i] = number.floatValue();
                } catch(ParseException pe){
                    this.errors[i].setText("      Incorrect entry");
                }
            }
            else {
                if(string_answer[i].equals("yes"))
                    decision[i%7]=true;
                else if(string_answer[i].equals("no"))
                    decision[i%7]=false;
                else
                    this.errors[i].setText("      Incorrect entry");
            }

        }
        for(int i=0;i<3;i++){
            if(decision[i]==true)
                risk_rate+=0.15;
        }
        update=DataAccess.setClientData(DataAccess.getIdOfClient(this.email),answer_values,decision[0],risk_rate);
        if(update==false){
            this.errors[10].setText("               Could not reach database, log out and log in again.");
            return ;
        }
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
