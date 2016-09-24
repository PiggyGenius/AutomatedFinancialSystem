import java.awt.event.*;

public class WindowCloser extends WindowAdapter {
    public WindowCloser(){
        ;
    }
    public void windowClosing(WindowEvent we){
        System.exit(0);
    }
}
