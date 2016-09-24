//package view;
import java.awt.*;
import javax.swing.*;



public class IconButton extends JButton{
	public IconButton(String who){
    	ImageIcon default_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/default_"+who+".png"));
    	ImageIcon hover_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/hover_"+who+".png"));
    	ImageIcon pressed_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/pressed_"+who+".png"));
    	
    	setIcon(default_icon);
    	setRolloverIcon(hover_icon);
    	setPressedIcon(pressed_icon);
    	
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    	setContentAreaFilled(false);
    	setBorder(BorderFactory.createEmptyBorder());
    	setPreferredSize(new Dimension(default_icon.getIconWidth(), default_icon.getIconHeight()));
	}
}
