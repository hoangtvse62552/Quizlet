package quizlet.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import quizlet.model.QuestionObject;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

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
    private JButton           btnPre;
    private JButton           btnReset;
    private JButton           btnClose;
    private JLabel            lbNum;
    private int               count            = 0;
    private JButton           btnFocusKeyboard;
    private JTextArea         lbGuide;
    private JPanel            panel;

    public MainPage(List<QuestionObject> qnaList)
    {
        this.qnaList = qnaList;

        question = new JTextArea("Question");
        question.setBounds(50, 50, 900, 220);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);

        // tạo viền
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        // khoảng cách đối tượng với các phần tử bên ngoài
        question.setMargin(new Insets(5, 5, 5, 5));
        // padding (khoảng cách các phần tử bên trong
        Border padding = new EmptyBorder(5, 5, 5, 5);
        // kết hợp 2 border
        Border compoundBorder = BorderFactory.createCompoundBorder(border, padding);
        question.setBorder(compoundBorder);

        Font font = new Font("Arial", Font.PLAIN, 16);
        question.setFont(font);

        lbNum = new JLabel("Number");
        lbNum.setBounds(30, 30, 100, 20);
        lbNum.setVisible(true);

        JLabel lbAnswer = new JLabel("Đáp án");
        lbAnswer.setBounds(350, 290, 100, 25);

        txtAnswer = new JTextField();
        txtAnswer.setBounds(450, 290, 150, 25);
        txtAnswer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (txtAnswer.getText().toUpperCase().equals(answer))
                {
                    lbError.setText("Đúng òi em iu");
                }
                else
                {
                    lbError.setText("Sai òi em iu, đáp án là: " + answer);
                }
                lbError.setVisible(true);
            }
        });

        btnSubmit = new JButton("Kiểm tra");
        btnSubmit.setBounds(450, 330, 100, 25);
        btnSubmit.addActionListener(this);

        btnNext = new JButton("Câu tiếp");
        btnNext.setBounds(560, 330, 100, 25);
        btnNext.addActionListener(this);

        btnPre = new JButton("Câu trước");
        btnPre.setBounds(340, 330, 100, 25);
        btnPre.addActionListener(this);
        btnPre.setEnabled(false);

        btnReset = new JButton("Làm lại");
        btnReset.setBounds(670, 330, 100, 25);
        btnReset.addActionListener(this);

        btnClose = new JButton("Đóng");
        btnClose.setBounds(670, 360, 100, 25);
        btnClose.addActionListener(this);

        lbError = new JLabel("Error");
        lbError.setBounds(450, 270, 300, 25);
        lbError.setVisible(false);
        lbError.setForeground(Color.RED); 
        
        btnFocusKeyboard = new JButton("Dùng bàn phím");
        btnFocusKeyboard.setBounds(340, 360, 200, 25);
        btnFocusKeyboard.addActionListener(this);

        lbGuide = new JTextArea("Dùng phím mũi tên <- và -> để di chuyển câu hỏi. Nhấn các phím chữ cái (A,B,C,D,...) để trả lời câu hỏi");
        lbGuide.setBounds(100, 385, 800, 30);
        lbGuide.setVisible(true);

        add(lbNum);
        add(question);
        add(lbAnswer);
        add(txtAnswer);
        add(btnSubmit);
        add(lbError);
        add(btnNext);
        add(btnPre);
        add(btnClose);
        add(btnReset);
        add(btnFocusKeyboard);
        add(lbGuide);

        panel = new JPanel();
        panel.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                char keychar = e.getKeyChar();
                if (keyCode == KeyEvent.VK_LEFT)
                {
                    onClickPre();
                }
                else if (keyCode == KeyEvent.VK_RIGHT)
                {
                    onClickNext();
                }
                if (keychar >= 'a' && keychar <= 'z')
                {
                    String keyCharAnswer = keychar + "";
                    if (keyCharAnswer.toUpperCase().equals(answer))
                    {
                        lbError.setText("Đúng òi em iu");
                    }
                    else
                    {
                        lbError.setText("Sai òi em iu, đáp án là: " + answer);
                    }
                    lbError.setVisible(true);
                }
            }
        });
        panel.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                btnFocusKeyboard.setEnabled(false);
                lbGuide.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                btnFocusKeyboard.setEnabled(true);
                lbGuide.setVisible(false);
            }
        });
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        add(panel, BorderLayout.CENTER);

        setSize(400, 300);
        setBounds(800, 300, 1000, 450);
        setLayout(null);
        setTitle("From Bin with Love <3");
        mixListQuestion();
    }

    private void mixListQuestion()
    {
        Collections.shuffle(qnaList);
        count = 0;
        lbNum.setText((count + 1) + "/" + qnaList.size());
        question.setText(qnaList.get(count).getQuestion());
        answer = qnaList.get(count).getAnswer();
        lbError.setVisible(false);
    }

//    private void randomQuestion()
//    {
//        if (qnaRemovedList.size() > 0)
//        {
//            lbNum.setText(count++ + "/" + qnaList.size());
//            Random random = new Random();
//            int num = random.nextInt(qnaRemovedList.size());
//            question.setText(qnaRemovedList.get(num).getQuestion());
//            answer = qnaRemovedList.get(num).getAnswer();
//            lbError.setVisible(false);
//            qnaRemovedList.remove(num);
//        }
//        else
//        {
//            question.setText("Hết câu hỏi");
//            btnNext.setVisible(false);
//            btnSubmit.setVisible(false);
//        }
//
//    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == btnSubmit)
        {
            if (txtAnswer.getText().toUpperCase().equals(answer))
            {
                lbError.setText("Đúng òi em iu");
            }
            else
            {
                lbError.setText("Sai òi em iu, đáp án là: " + answer);
            }
            lbError.setVisible(true);
        }
        else if (e.getSource() == btnNext)
        {
            onClickNext();
        }
        else if (e.getSource() == btnPre)
        {
            onClickPre();
        }
        else if (e.getSource() == btnReset)
        {
            mixListQuestion();
        }
        else if (e.getSource() == btnClose)
        {
            System.exit(0);
        }
        else if (e.getSource() == btnFocusKeyboard)
        {
            panel.setFocusable(true);
            panel.requestFocusInWindow();
        }
    }

    private void onClickNext()
    {
        btnPre.setEnabled(true);
        count++;
        if (count < qnaList.size())
        {
            txtAnswer.setText("");
            lbNum.setText((count + 1) + "/" + qnaList.size());
            question.setText(qnaList.get(count).getQuestion());
            answer = qnaList.get(count).getAnswer();
            lbError.setVisible(false);
        }
        else
        {
            count--;
            question.setText("Hết rồi em iu");
            btnNext.setEnabled(false);
            btnSubmit.setEnabled(false);
        }
    }

    private void onClickPre()
    {
        count--;
        if (count == 0)
        {
            btnPre.setEnabled(false);
        }
        if (count >= 0)
        {
            btnNext.setEnabled(true);
            btnSubmit.setEnabled(true);
            txtAnswer.setText("");
            lbNum.setText((count + 1) + "/" + qnaList.size());
            question.setText(qnaList.get(count).getQuestion());
            answer = qnaList.get(count).getAnswer();
            lbError.setVisible(false);
        }
        else
        {
            count++;
        }
    }
}
