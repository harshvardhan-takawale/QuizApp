package com.example.harshvardhan.quizclubapp;

/**
 * Created by lenovo on 7/13/2017.
 */

public class QuestionForm {
    private String question;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String answer;

    public QuestionForm() {

    }

    public QuestionForm(String question, String optA, String optB, String optC, String optD, String answer) {
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptA() {
        return optA;
    }

    public String getOptB() {
        return optB;
    }

    public String getOptC() {
        return optC;
    }

    public String getOptD() {
        return optD;
    }

    public String getAnswer() {
        return answer;
    }
}
