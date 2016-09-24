import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Start {
    private static JFrame window;
    private static Point mouseDownCompCoords;
    public static void main(String[] args){
        window = new JFrame("Automated Investment Financial System");
        window.setSize(1300,600);
        //window.setLayout(new GridLayout(3,3));
        window.setUndecorated(true);
        //window.setBounds(0,0,1300,600);
        //window.getContentPane().setBackground(new Color(33,36,38));
        configListener();

        /*
        
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(window);
        cr.setSnapSize(new Dimension(10,10));
        cr.setMaximumSize(new Dimension(2000,2000));
        cr.setMinimumSize(new Dimension(1300,600));
        
        */
	
    	JMenuBar bar = new JMenuBar();
    	bar.setBackground(new Color(33,36,38));
    	bar.setLayout(new BorderLayout());
    	bar.setBorder(BorderFactory.createMatteBorder(0,0,1,0, new Color(65,65,65)));
	   	
    	JLabel lab_p = new JLabel("AFIS  ");
    	lab_p.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,15));
    	lab_p.setForeground(new Color(240,240,240));
	   	
    	JLabel lab_t = new JLabel("AUTOMATED FINANCIAL INVESTMENT SYSTEM");
    	lab_t.setFont(new Font("Century Gothic",Font.PLAIN,12));
    	lab_t.setForeground(new Color(180,180,200));
	   	
    	JButton close = (JButton)new IconButton("close");
    	JButton minimize = (JButton)new IconButton("minimize");
    	close.addActionListener(new MenuListener("close"));
    	minimize.addActionListener(new MenuListener("minimize"));
	   	
    	JPanel pan_e = new JPanel(new GridBagLayout());
    	pan_e.setBackground(null);
    	pan_e.add(lab_p, new GridBagConstraints());
    	pan_e.add(lab_t);
	   	
    	JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    	pan.setBackground(null);
    	pan.add(minimize);
    	pan.add(close);
	   	
    	bar.add(pan_e, BorderLayout.CENTER);
    	bar.add(pan, BorderLayout.EAST);
    	window.setJMenuBar(bar);

        //window.getContentPane().setBackground(Color.RED);

        ConnectionPage connection_page = new ConnectionPage(window.getWidth()/80);

        //window.addComponentListener(new WindowResizer(connection_page));
        window.addWindowListener(new WindowCloser());
        window.add(new JLabel());
        window.add(connection_page);

        window.setVisible(true);
    }
    static public JFrame get(){
        return window;
    }
    static private void configListener(){
	    window.addMouseListener(new MouseListener(){
		    @Override public void mouseReleased(MouseEvent e){ mouseDownCompCoords = null; }
		    @Override public void mousePressed(MouseEvent e){ mouseDownCompCoords = e.getPoint(); }
		    @Override public void mouseExited(MouseEvent e){}
		    @Override public void mouseEntered(MouseEvent e){}
		    @Override public void mouseClicked(MouseEvent e){}
	    });
	    
	    window.addMouseMotionListener(new MouseMotionListener(){
	    	@Override public void mouseMoved(MouseEvent e){}
	    	@Override public void mouseDragged(MouseEvent e){
		        Point currCoords = e.getLocationOnScreen();
		        window.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
		    }
	    });
    }
}
