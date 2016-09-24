//package controller;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import view.*;



public class MenuListener implements ActionListener{
	private String who;
	
	public MenuListener(String w){
		who = w;
	}
	
	@Override public void actionPerformed(ActionEvent arg0) {
		if(who.equals("close"))
			close();
		else if(who.equals("minimize"))
			minimize();
	}
	
	private void close(){
		Start.get().dispose();
		System.exit(0);
	}
	
	private void minimize(){
		Start.get().setState(Frame.ICONIFIED);
	}
}
