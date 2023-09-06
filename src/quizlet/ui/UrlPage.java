package quizlet.ui;

import javax.swing.*;

import quizlet.model.QuestionObject;
import quizlet.start.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class UrlPage extends Frame implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField        txtAnswer;
    private JButton           btnSubmit;
    private JButton           btnClose;

    public UrlPage()
    {

        txtAnswer = new JTextField();
        txtAnswer.setBounds(20, 50, 160, 20);
        txtAnswer.setText("D:\\QnA.txt");

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(50, 100, 100, 20);
        btnSubmit.addActionListener(this);

        btnClose = new JButton("Close");
        btnClose.setBounds(50, 130, 100, 20);
        btnClose.addActionListener(this);

        add(txtAnswer);
        add(btnSubmit);
        add(btnClose);
        setSize(400, 300);
        setBounds(800, 300, 200, 200);
        setLayout(null);

    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == btnSubmit)
        {
            List<QuestionObject> qnaList = Main.readQnAFromFile(txtAnswer.getText().trim());
            if (qnaList.size() > 0)
            {
                for (QuestionObject questionObject : qnaList)
                {
//                    System.out.println("---------------------------------------------------");
//                    System.out.println(questionObject.getQuestion() + "\n" + questionObject.getAnswer());
                }
                System.out.println(qnaList.size());
                MainPage page = new MainPage(qnaList);
                page.setVisible(true);
                this.setVisible(false);
            }
        }
        else if (e.getSource() == btnClose)
        {
            System.exit(0);
        }

    }
}
