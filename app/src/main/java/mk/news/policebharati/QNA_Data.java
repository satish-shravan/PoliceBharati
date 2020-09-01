package mk.news.policebharati;

public class QNA_Data {
    String question;
    String answer;

    public  QNA_Data()
    {

    }

    public QNA_Data(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
