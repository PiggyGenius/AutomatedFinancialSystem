import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PilotingController extends MouseAdapter {
    private DataAccess data_model;
    private static JPanel[] panels;
    private TitleScreen title_screen;
    private String email;

    public PilotingController(DataAccess data_model,TitleScreen title_screen, String email){
        this.data_model = data_model;
        this.title_screen = title_screen;
        this.email = email;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        JPanel clicked_panel = (JPanel) e.getSource();
        Container window = this.title_screen.getParent();
        window.removeAll();
        if(clicked_panel.equals(this.panels[0])){
            PortfolioCenterScreen portfolio_CenterScreen = new PortfolioCenterScreen(this.email);
            PortfolioLeftScreen portfolio_LeftScreen = new PortfolioLeftScreen(this.email);
            PortfolioRightScreen portfolio_RightScreen = new PortfolioRightScreen(this.email);
            PortfolioTopScreen portfolio_TopScreen = new PortfolioTopScreen(this.email);
            PortfolioBottomScreen portfolio_BottomScreen = new PortfolioBottomScreen();
            window.add(portfolio_CenterScreen, BorderLayout.CENTER);
            window.add(portfolio_LeftScreen, BorderLayout.WEST);
            window.add(portfolio_RightScreen,BorderLayout.EAST);
            window.add(portfolio_TopScreen, BorderLayout.NORTH);
            //window.add(portfolio_BottomScreen, BorderLayout.SOUTH);
            window.validate();
            window.repaint();
            PortfolioLeftScreen.setScrollPane(portfolio_LeftScreen.getWidth(),portfolio_LeftScreen.getHeight());
            PortfolioRightScreen.setScrollPane(portfolio_RightScreen.getWidth(),portfolio_RightScreen.getHeight());
//            PortfolioCenterScreen.setScrollPane(window.getWidth()-portfolio_LeftScreen.getWidth()-portfolio_RightScreen.getWidth(),portfolio_RightScreen.getHeight());
            PortfolioCenterScreen.setScrollPane(portfolio_CenterScreen.getWidth(),portfolio_RightScreen.getHeight());

        }
        else if(clicked_panel.equals(this.panels[1])){
            window.add(new HeaderGroupScreen(), BorderLayout.NORTH);
            GroupScreen group_screen = new GroupScreen(this.email);
            window.add(group_screen);
        }
        else if(clicked_panel.equals(this.panels[2])){
            ProfileScreen profile_screen = new ProfileScreen(this.email);
            window.add(profile_screen, BorderLayout.CENTER);
        }
        else {
            HistoryScreen history_screen = new HistoryScreen(window,this.email);
            RefreshListener refresh_listener = new RefreshListener(history_screen,this.email);
            window.add(new HeaderHistoryScreen(this.email,refresh_listener), BorderLayout.NORTH);
            history_screen.setClientList(refresh_listener.refresh_client());
            history_screen.setGroupList(refresh_listener.refresh_group());
            window.add(history_screen, BorderLayout.CENTER);
            window.validate();
            window.repaint();
            history_screen.setScrollPanes(history_screen.getWidth(),history_screen.getHeight());
        }
        window.validate();
        window.repaint();
    }

    public void setPanels(JPanel[] panels){
        this.panels = new JPanel[panels.length];
        for(int i=0;i<panels.length;i++){
            this.panels[i]=panels[i];
        }
    }
}
