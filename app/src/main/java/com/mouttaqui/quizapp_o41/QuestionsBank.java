package com.mouttaqui.quizapp_o41;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    private static List<QuestionsList> scienceQuestions() {

    final List<QuestionsList> questionsLists = new ArrayList<>();

    final QuestionsList question1 = new QuestionsList("Plants receive their nutrients mainly from?","Chlorophyll","Soil","Atmosphere","Soil","");
    final QuestionsList question2 = new QuestionsList("How many bones are in the human body?","206","110","250","206","");
    final QuestionsList question3 = new QuestionsList("What is the biggest planet in our solar system?","Mars","Jupiter","Saturn","Jupiter","");
    final QuestionsList question4 = new QuestionsList("What is the hardest natural substance on Earth?","Diamond","Nanospheres","Dyneema","Diamond","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

    return questionsLists;
}
    private static List<QuestionsList> popQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("How many times did Ross Geller get divorced on Friends?","Never","2 times","3 times","3 times","");
        final QuestionsList question2 = new QuestionsList("Who did Forbes name the youngest “self-made billionaire ever” in 2019?","Selena Gomez","Kylie Jenner","Tom Holland","Kylie Jenner","");
        final QuestionsList question3 = new QuestionsList("Who was able to pick up Thor’s hammer in Endgame?","SpiderMan","Iron Man","Captain America","Captain America","");
        final QuestionsList question4 = new QuestionsList("How many kids does Angelina Jolie have?","6","4","2","6","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;
    }

    private static List<QuestionsList> literatureQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Mention the exact number of sonnets written by William Shakespeare?","202","154","360","154","");
        final QuestionsList question2 = new QuestionsList("Who is the author of the classic masterpiece “Anna Karenina”?","Sarah J Maas","Leo Tolstoy","Dean Koontz","Leo Tolstoy","");
        final QuestionsList question3 = new QuestionsList("Which novel of William Peter Blatty written in 1971? It has been adopted in which epic Horror Film?","The Exorcist","Eli","Summer of 84","The Exorcist","");
        final QuestionsList question4 = new QuestionsList("Tweedledum and Tweedledee are two characters of which Children’s book?","The Little Prince","A Traveller in Time","Alice in Wonderland","Alice in Wonderland","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;
    }
    private static List<QuestionsList> geographyQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What is the capital city of Australia?","Canberra","Sydney","Perth","Canberra","");
        final QuestionsList question2 = new QuestionsList("What is the name of the tallest mountain in the world?","Makalu","Mount Everest","K2","Mount Everest","");
        final QuestionsList question3 = new QuestionsList("What American city is the Golden Gate Bridge located in?","San Francisco","New York","Los Angeles","San Francisco","");
        final QuestionsList question4 = new QuestionsList("What country has the most natural lakes?","China","Russia","Canada","Canada","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;
    }
public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "science":
                return scienceQuestions();
            case "geography":
                return geographyQuestions();
            case "literature":
                return literatureQuestions();
            default:
                return popQuestions();
        }
    }
}
