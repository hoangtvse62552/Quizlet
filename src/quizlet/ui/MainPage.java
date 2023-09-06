package quizlet.ui;

import javax.swing.*;

import quizlet.model.QuestionObject;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class MainPage extends Frame implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField        txtAnswer;
    private JButton           btnSubmit;
    private JLabel            lbError;
    private JTextArea         question;
    List<QuestionObject>      qnaList;
    private String            answer           = "";
    private JButton           btnNext;
    private JButton           btnReset;
    List<QuestionObject>      qnaRemovedList;

    private final JTextField  txtError         = new JTextField();

    public MainPage(List<QuestionObject> qnaList)
    {
        this.qnaList = qnaList;
        this.qnaRemovedList = qnaList;

        txtError.setColumns(10);
        question = new JTextArea("Question");
        question.setBounds(50, 50, 500, 250);

        JLabel lbAnswer = new JLabel("Answer");
        lbAnswer.setBounds(50, 300, 100, 20);

        txtAnswer = new JTextField();
        txtAnswer.setBounds(150, 300, 150, 20);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(70, 330, 100, 20);
        btnSubmit.addActionListener(this);

        btnNext = new JButton("Next");
        btnNext.setBounds(200, 330, 100, 20);
        btnNext.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(300, 330, 100, 20);
        btnReset.addActionListener(this);

        lbError = new JLabel("Error");
        lbError.setBounds(50, 360, 300, 20);
        lbError.setVisible(false);

        add(question);
        add(lbAnswer);
        add(txtAnswer);
        add(btnSubmit);
        add(lbError);
        add(btnNext);
        setSize(400, 300);
        setBounds(800, 300, 600, 400);
        setLayout(null);
        
        randomQuestion();
    }

    private void randomQuestion()
    {
        if (qnaRemovedList.size() > 0)
        {
            Random random = new Random();
            int num = random.nextInt(qnaRemovedList.size());
            question.setText(qnaRemovedList.get(num).getQuestion());
            answer = qnaRemovedList.get(num).getAnswer();
            lbError.setVisible(false);
            qnaRemovedList.remove(num);
        }
        else
        {
            question.setText("Hết câu hỏi");
            btnNext.setVisible(false);
            btnSubmit.setVisible(false);
        }

    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == btnSubmit)
        {
            if (txtAnswer.getText().toUpperCase().equals(answer))
            {
                lbError.setText("Đúng òi");
            }
            else
            {
                lbError.setText("Sai òi, đáp án là: " + answer);
            }
            lbError.setVisible(true);
        }
        else if (e.getSource() == btnNext)
        {
            randomQuestion();
        }
        else if (e.getSource() == btnReset)
        {
            qnaRemovedList.addAll(qnaList);
            randomQuestion();
        }
    }
}
