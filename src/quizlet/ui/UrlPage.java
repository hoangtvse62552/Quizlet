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

    public UrlPage()
    {

        txtAnswer = new JTextField();
        txtAnswer.setBounds(20, 50, 150, 20);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(50, 100, 100, 20);
        btnSubmit.addActionListener(this);

        add(txtAnswer);
        add(btnSubmit);
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
                MainPage page = new MainPage(qnaList);
                page.setVisible(true);
                this.setVisible(false);
            }
        }

    }
}
