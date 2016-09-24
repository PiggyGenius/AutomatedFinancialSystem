import javax.swing.*;
import java.awt.event.*;

public class HintPasswordField extends JPasswordField implements FocusListener {
    private String hint;
    private boolean visible;

    public HintPasswordField(String hint,int columns){
        super(hint,columns);
        this.hint = hint;
        this.visible = true;
        super.addFocusListener(this);
        this.setEchoChar((char) 0);
    }
    @Override
    public void focusGained(FocusEvent e){
        if(this.getPassword().length == 0){
            this.setEchoChar('*');
            super.setText("");
            this.visible = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e){
        if(this.getPassword().length == 0){
            this.setEchoChar((char) 0);
            super.setText(hint);
            this.visible = true;
        }
    }
    @Override
    public char[] getPassword(){
        char[] empty = new char[0];
        if(this.visible){
            return empty;
        }
        else {
            return super.getPassword();
        }
    }
}
