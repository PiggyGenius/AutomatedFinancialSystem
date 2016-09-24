import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ProfileScreen extends JPanel {
    public ProfileScreen(String email){
        Color window_color = new Color(33,36,38);
        this.setBackground(window_color);
        Font label_font = new Font(Font.SANS_SERIF,Font.PLAIN,20);


        JPanel survey = new JPanel();
        survey.setLayout(new BoxLayout(survey,BoxLayout.Y_AXIS));
        survey.setBackground(window_color);
        JLabel useless;
        JTextField[] answers = new JTextField[10];
        JLabel[] errors = new JLabel[11];


        for(int i=0;i<11;i++){
            errors[i] = new JLabel(" ");
            errors[i].setForeground(Color.RED);
        }


        JPanel header_panel = new JPanel(new GridLayout(1,3));
        JPanel return_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        return_panel.setBackground(window_color);
        header_panel.setBackground(window_color);
        JButton return_button = new JButton("Return");
        return_button.setBackground(window_color);
        return_button.setForeground(Color.WHITE);
        return_button.addActionListener(new ReturnController(this));
        return_panel.add(return_button);
        header_panel.add(return_panel);

        JPanel error_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel submit_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submit_panel.setBackground(window_color);
        error_panel.setBackground(window_color);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ProfileController(email,answers,errors,this));
        submit.setBackground(window_color);
        submit.setForeground(Color.WHITE);
        submit_panel.add(submit);
        error_panel.add(errors[10]);
        header_panel.add(error_panel);
        header_panel.add(submit_panel);

        survey.add(header_panel);


        JPanel question_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_1.setBackground(window_color);

        JLabel total_invest = new JLabel("1) How much money do you wish to invest in this simulation ?        ");
        total_invest.setFont(label_font);
        total_invest.setForeground(Color.WHITE);
        answers[0] = new JTextField(10);
        answers[0].setFont(label_font);
        answers[0].setBackground(window_color);
        answers[0].setForeground(Color.WHITE);
        question_1.add(total_invest);
        question_1.add(answers[0]);
        question_1.add(errors[0]);
        survey.add(question_1);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_2.setBackground(window_color);

        JLabel invest_rate = new JLabel("2) What rate of this amount of money should be invested at the same time at most ?        ");
        invest_rate.setForeground(Color.WHITE);
        invest_rate.setFont(label_font);
        answers[1] = new JTextField(10);
        answers[1].setFont(label_font);
        answers[1].setBackground(window_color);
        answers[1].setForeground(Color.WHITE);
        question_2.add(invest_rate);
        question_2.add(answers[1]);
        question_2.add(errors[1]);
        survey.add(question_2);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_3.setBackground(window_color);

        JLabel capital = new JLabel("3) What is the sum of the value of everything you own ?        ");
        capital.setForeground(Color.WHITE);
        capital.setFont(label_font);
        answers[2] = new JTextField(10);
        answers[2].setFont(label_font);
        answers[2].setBackground(window_color);
        answers[2].setForeground(Color.WHITE);
        question_3.add(capital);
        question_3.add(answers[2]);
        question_3.add(errors[2]);
        survey.add(question_3);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_4.setBackground(window_color);

        JLabel net_capital = new JLabel("4) What is the sum of the net value of everything you own ?        ");
        net_capital.setForeground(Color.WHITE);
        net_capital.setFont(label_font);
        answers[3] = new JTextField(10);
        answers[3].setFont(label_font);
        answers[3].setBackground(window_color);
        answers[3].setForeground(Color.WHITE);
        question_4.add(net_capital);
        question_4.add(answers[3]);
        question_4.add(errors[3]);
        survey.add(question_4);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_5.setBackground(window_color);

        JLabel debt_value = new JLabel("5) How much debt do you have ?        ");
        debt_value.setForeground(Color.WHITE);
        debt_value.setFont(label_font);
        answers[4] = new JTextField(10);
        answers[4].setFont(label_font);
        answers[4].setBackground(window_color);
        answers[4].setForeground(Color.WHITE);
        question_5.add(debt_value);
        question_5.add(answers[4]);
        question_5.add(errors[4]);
        survey.add(question_5);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_6.setBackground(window_color);

        JLabel income = new JLabel("6) How much income are you sure to earn every month ?        ");
        income.setForeground(Color.WHITE);
        income.setFont(label_font);
        answers[5] = new JTextField(10);
        answers[5].setFont(label_font);
        answers[5].setBackground(window_color);
        answers[5].setForeground(Color.WHITE);
        question_6.add(income);
        question_6.add(answers[5]);
        question_6.add(errors[5]);
        survey.add(question_6);
        useless=new JLabel("      ");
        survey.add(useless);

 
        JPanel question_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_7.setBackground(window_color);

        JLabel time = new JLabel("7) For how many years do you plan to not need the invested money ?        ");
        time.setForeground(Color.WHITE);
        time.setFont(label_font);
        answers[6] = new JTextField(10);
        answers[6].setFont(label_font);
        answers[6].setBackground(window_color);
        answers[6].setForeground(Color.WHITE);
        question_7.add(time);
        question_7.add(answers[6]);
        question_7.add(errors[6]);
        survey.add(question_7);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_8.setBackground(window_color);

        JLabel life = new JLabel("8) Do you plan to live off AFIS ?(yes/no)        ");
        life.setForeground(Color.WHITE);
        life.setFont(label_font);
        answers[7] = new JTextField(10);
        answers[7].setFont(label_font);
        answers[7].setBackground(window_color);
        answers[7].setForeground(Color.WHITE);
        question_8.add(life);
        question_8.add(answers[7]);
        question_8.add(errors[7]);
        survey.add(question_8);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_9.setBackground(window_color);

        JLabel risky = new JLabel("9) 75% chance of doubling the investment, 25% chance of loosing 3/4, invest ?(yes/no)        ");
        risky.setForeground(Color.WHITE);
        risky.setFont(label_font);
        answers[8] = new JTextField(10);
        answers[8].setFont(label_font);
        answers[8].setBackground(window_color);
        answers[8].setForeground(Color.WHITE);
        question_9.add(risky);
        question_9.add(answers[8]);
        question_9.add(errors[8]);
        survey.add(question_9);
        useless=new JLabel("      ");
        survey.add(useless);


        JPanel question_10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        question_10.setBackground(window_color);

        JLabel risk = new JLabel("10) Are you willing to make a very risky decision if the reward is very important ?(yes/no)        ");
        risk.setForeground(Color.WHITE);
        risk.setFont(label_font);
        answers[9] = new JTextField(10);
        answers[9].setFont(label_font);
        answers[9].setBackground(window_color);
        answers[9].setForeground(Color.WHITE);
        question_10.add(risk);
        question_10.add(answers[9]);
        question_10.add(errors[9]);
        survey.add(question_10);


/*
        JPanel submit_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        submit_panel.setBackground(window_color);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ProfileController(email,answers,errors,this));
        submit.setBackground(window_color);
        submit.setForeground(Color.WHITE);
        submit_panel.add(submit);
        submit_panel.add(errors[10]);
        survey.add(submit_panel);
*/


        this.add(survey);
    }
}
