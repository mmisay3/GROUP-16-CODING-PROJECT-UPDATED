package edu.mmisay3.myapplication;

public class QuestionLibrary {

    //Source : https://quizlet.com/146255172/cpr-test-25-questions-flash-cards/
    //Answers for quiz questions
    public static String mQuestions [] = {
            "What is the rate for chest compressions per minute for any age?",
            "The compression-ventilation ratio for one or two rescuers for ADULT CPR is?",
            "Where should the hands be placed on the chest for adult CPR?",
            "What is the preferred hand technique for two rescuer infant CPR?",
            "What is the depth of compression on an adult?",
            "What is the depth of compression on an infant?"

    };

    public static String mChoices[][] = {
            {"50","75","100"},
            {"30 compression to 2 ventilation breaths","90 compression to 2 ventilation breaths","45 compression to 2 ventilation breaths"},
            {"upper half of breast bone","lower half of breast bone","middle of breast bone"},
            {"1 thumb", "2 thumbs", "3 thumbs"},
            {"1 inch","2 inches","3 inches"},
            {"1 cm","2 cm", "4 cm"}
    };

    public static String mCorrectAnswers[] = {"100","30 compression to 2 ventilation breaths","lower half of breast bone","2 thumbs","2 inches","4 cm"};

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice1 = mChoices[a][0];
        return choice1;
    }

    public String getChoice2(int a){
        String choice2 = mChoices[a][1];
        return choice2;
    }

    public String getChoice3(int a){
        String choice3 = mChoices[a][2];
        return choice3;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
