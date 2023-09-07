package quizlet.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import quizlet.model.QuestionObject;
import quizlet.start.Main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class UrlPage extends Frame implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField        txtAnswer;
    private JButton           btnSubmit;
    private JButton           btnClose;
    private JButton           btnOpenFile;
    private JFileChooser      fileChooser;

    public UrlPage()
    {

        txtAnswer = new JTextField();
        txtAnswer.setBounds(20, 50, 160, 30);
        txtAnswer.setText("D:\\QnA.txt");

        btnSubmit = new JButton("Mở");
        btnSubmit.setBounds(50, 100, 100, 20);
        btnSubmit.addActionListener(this);

        btnClose = new JButton("Đóng");
        btnClose.setBounds(50, 130, 100, 20);
        btnClose.addActionListener(this);

        btnOpenFile = new JButton("Chọn");
        btnOpenFile.setBounds(200, 50, 70, 30);
        btnOpenFile.addActionListener(this);

        try
        {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        add(txtAnswer);
        add(btnSubmit);
        add(btnClose);
        add(btnOpenFile);
        setSize(400, 300);
        setBounds(800, 300, 300, 200);
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
        else if (e.getSource() == btnClose)
        {
            System.exit(0);
        }
        else if (e.getSource() == btnOpenFile)
        {
            int result = fileChooser.showOpenDialog(this);

            // Kiểm tra xem người dùng đã chọn tệp hay chưa
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                System.out.println("Đường dẫn tệp đã chọn: " + filePath);
                txtAnswer.setText(filePath);
            }
        }

    }
}
