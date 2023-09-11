package quizlet.start;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import quizlet.model.QuestionObject;
import quizlet.ui.UrlPage;

public class Main
{
    public static void main(String[] args) throws Exception
    {
//        String filePath = "D:/QnA.txt";
//        List<QuestionObject> qnaList = readQnAFromFile(filePath);
//
//        System.out.println(qnaList.size());
//        for (QuestionObject qna : qnaList)
//        {
//            System.out.println(qna.getQuestion());
//            System.out.println(qna.getAnswer());
//        }

        UrlPage page = new UrlPage();
        page.setVisible(true);

    }

    public static List<QuestionObject> readQnAFromFile(String filePath)
    {
        List<QuestionObject> qnaList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))
        {
            String line;
            String question = "";
            String answer = "";

            while ((line = br.readLine()) != null)
            {
                if (checkAnswer(line))
                {
                    line = line.trim();
                    line = line.toUpperCase();
                    answer = line;
                    QuestionObject dto = new QuestionObject(question, answer);
                    qnaList.add(dto);
                    question = "";
                }
                else
                {
                    line = line.trim();
                    line = line.replaceAll("\t", "  ");
                    question += line + " \n";
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return qnaList;
    }

    private static boolean checkAnswer(String line)
    {
        try
        {
            line = line.replaceAll("\t", " ");
            line = line.trim();
            if (line.length() > 0)
            {
                String[] items = line.split(" ");

                if (items.length == 1)
                {
//                    System.out.println("[" + line + "] : " + items[0] + " : " + items[0].length());
                    return true;
                }
                else
                    return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }

}