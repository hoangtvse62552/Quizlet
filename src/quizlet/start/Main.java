package quizlet.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            String question = "";
            String answer = "";

            while ((line = br.readLine()) != null)
            {
                if (checkAnswer(line))
                {

                    answer = line.trim();
                    QuestionObject dto = new QuestionObject(question, answer);
                    qnaList.add(dto);
                    question = "";
                }
                else
                {
                    question += line.trim() + " \n";
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return qnaList;
    }

    private static boolean checkNumQuest(String line)
    {
        try
        {
            String[] items = line.split(" ");
            Integer.parseInt(items[0].replace('.', ' '));
        }
        catch (Exception e)
        {
            return false;
        }
        return true;

    }

    private static boolean checkAnswer(String line)
    {
        try
        {
            String[] items = line.trim().split(" ");
            if (items.length > 1)
                return false;
            else
                return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}