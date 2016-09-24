import javax.swing.*;
import java.awt.event.*;

public class HintTextField extends JTextField implements FocusListener {
    private String hint;
    private boolean visible;

    public HintTextField(String hint,int columns){
        super(hint,columns);
        this.hint = hint;
        this.visible = true;
        super.addFocusListener(this);
    }
    @Override
    public void focusGained(FocusEvent e){
        if(this.getText().isEmpty()){
            super.setText("");
            this.visible = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e){
        if(this.getText().isEmpty()){
            super.setText(hint);
            this.visible = true;
        }
    }
    @Override
    public String getText(){
        if(this.visible){
            return "";
        }
        else {
            return super.getText();
        }
    }
}
