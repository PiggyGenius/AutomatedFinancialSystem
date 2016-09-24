import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ConnectionPage extends JPanel {
    private JTextField email;
    private JPasswordField password;
    private JButton sign_in;

    public ConnectionPage(int width){
        //Color bckg_color = new Color(50,46,46);
        //this.setBackground(bckg_color);
        Font text_font = new Font(Font.SANS_SERIF,Font.BOLD,25);
        JLabel error_message = new JLabel("");
        error_message.setForeground(Color.RED);

        this.email = new HintTextField("Email",width);
        this.email.setFont(text_font);
        //this.email.setBackground(bckg_color);
        //this.email.setForeground(Color.WHITE);

        this.password = new HintPasswordField("Password",width);
        this.password.setFont(text_font);
        //this.password.setBackground(bckg_color);
        //this.password.setForeground(Color.WHITE);

        this.sign_in = new JButton("Sign in");
        this.sign_in.setFont(text_font);
        this.sign_in.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.sign_in.getMinimumSize().height));

        //this.sign_in.setBackground(bckg_color);
        //this.sign_in.setForeground(Color.WHITE);

        ConnectionListener controller = new ConnectionListener(this.email,this.password,error_message,this);
        this.sign_in.addActionListener(controller);
        this.email.addActionListener(controller);
        this.password.addActionListener(controller);


        JPanel components = new JPanel();
        BoxLayout layout = new BoxLayout(components, BoxLayout.Y_AXIS);
        components.setLayout(layout);

        components.add(this.email);
        components.add(this.password);
        components.add(this.sign_in);
        components.add(error_message);

        this.add(components);
    }
} 
