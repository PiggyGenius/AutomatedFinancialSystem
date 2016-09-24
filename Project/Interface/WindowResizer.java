import java.awt.*;
import java.awt.event.*;

public class WindowResizer extends ComponentAdapter {
    private ConnectionPage connection_page;
    public WindowResizer(ConnectionPage connection_page){
        this.connection_page=connection_page;
    }
    public void componentResized(ComponentEvent e){
    }
}
