package quizlet.model;

public class QuestionObject
{
    private String question;
    private String answer;

    public QuestionObject()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuestionObject(String question, String answer)
    {
        super();
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    @Override
    public String toString()
    {
        
        return super.toString();
    }

}
