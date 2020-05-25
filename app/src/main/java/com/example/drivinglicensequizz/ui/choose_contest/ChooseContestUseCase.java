package com.example.drivinglicensequizz.ui.choose_contest;

import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.data.model.TypeOfContest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ChooseContestUseCase {
    public List<List<Question>> setQuestionForEachContestA1A2(List<Question> questions) {
        List<List<Question>> questionsPerContest = new ArrayList<>();
        for (int k = 0; k<=13; k++) {
            List<Question>ques = new ArrayList<>();
            ques.add(questions.get(k%12));
            for (int i=0; i<=6; i++) {
                int temp = k*i;
                if (temp + 13 < 72) {
                    ques.add(questions.get(temp+i+13));
                } else {
                    ques.add(questions.get(temp+13-30-i));
                }
            }

            if (k%5 == 0) {
                ques.add(questions.get(72));
                ques.add(questions.get(75));
            } else if (k%5 == 1) {
                ques.add(questions.get(73));
                ques.add(questions.get(76));
            } else if (k%5 == 2) {
                ques.add(questions.get(74));
                ques.add(questions.get(77));
            } else if (k%5 == 3) {
                ques.add(questions.get(74));
                ques.add(questions.get(78));
            } else {
                ques.add(questions.get(74));
                ques.add(questions.get(79));
            }

            if (k%6 == 0) {
                for (int i = 80; i <= 84; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%6 == 1) {
                for (int i = 85; i <= 89; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%6 == 3) {
                for (int i = 95; i <= 99; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%6 == 4) {
                for (int i = 100; i <= 104; i++) {
                    ques.add(questions.get(i));
                }
            } else {
                for (int i = 105; i <= 109; i++) {
                    ques.add(questions.get(i));
                }
            }

            // co 5 cau mang 6
            if (k%7 == 0) {
                for (int i = 115; i <= 119; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%7 == 1) {
                for (int i = 120; i <= 124; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%7 == 2) {
                for (int i = 125; i <= 129; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%7 == 3) {
                for (int i = 130; i <= 134; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%7 == 4) {
                for (int i = 135; i <= 139; i++) {
                    ques.add(questions.get(i));
                }
            } else if (k%7 == 5) {
                for (int i = 140; i <= 144; i++) {
                    ques.add(questions.get(i));
                }
            } else {
                for (int i = 145; i <= 149; i++) {
                    ques.add(questions.get(i));
                }
            }
            questionsPerContest.add(ques);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            int[] array1 = shuffleArray(IntStream.rangeClosed(0, 12).toArray()); // Lay 1 cau
            int[] array2 = shuffleArray(IntStream.rangeClosed(13, 71).toArray()); // Lay 7 cau
            int[] array3 = shuffleArray(IntStream.rangeClosed(72, 74).toArray()); // Lay 1 cau
            int[] array4 = shuffleArray(IntStream.rangeClosed(75, 79).toArray()); // Lay 1 cau
            int[] array5 = shuffleArray(IntStream.rangeClosed(80, 114).toArray()); // Lay 5 cau
            int[] array6 = shuffleArray(IntStream.rangeClosed(115, 150).toArray()); // Lay 5 cau
            List<Question>ques = new ArrayList<>();
            ques.add(questions.get(array1[0]));
            for (int i = 0; i <= 6; i++) {
                ques.add(questions.get(array2[i]));
            }
            ques.add(questions.get(array3[0]));
            ques.add(questions.get(array4[0]));
            for (int i = 0; i <= 4; i++) {
                ques.add(questions.get(array5[i]));
            }
            for (int i = 0; i <= 4; i++) {
                ques.add(questions.get(array6[i]));
            }
            questionsPerContest.add(ques);
        }
        return  questionsPerContest;
    }

    public List<List<Question>> setQuestionForEachContestB1B2(List<Question> questions) {
        List<List<Question>> questionsPerContest = new ArrayList<>();
        for (int k = 0; k<=13; k++) {
            List<Question> ques = new ArrayList<>();
            ques.add(questions.get(k));
            for (int i = 0;i <= 6;i++) {
                int temp = k*i;
                ques.add(questions.get(temp+21+i));
            }
            ques.add(questions.get(k+131));
            ques.add(questions.get(k+145));
            ques.add(questions.get(k+175));
            if (k%2==0) {
                ques.add(questions.get(k+200));
            } else {
                ques.add(questions.get(k+235));
            }
            for (int i=0;i<=8;i++) {
                int temp = k*i;
                if (temp+255<355) {
                    ques.add(questions.get(temp+255+i));
                } else {
                    ques.add(questions.get(temp+255-10-i));
                }
            }
            for (int i=0;i<=8;i++) {
                int temp = k*i;
                if (temp+356+8<450) {
                    ques.add(questions.get(temp+356+i));
                } else {
                    ques.add(questions.get(temp+356-20-i));
                }
            }
            questionsPerContest.add(ques);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            int[] array1 = shuffleArray(IntStream.rangeClosed(0, 20).toArray()); // Lay 1 cau
            int[] array2 = shuffleArray(IntStream.rangeClosed(21, 130).toArray()); // Lay 7 cau
            int[] array3 = shuffleArray(IntStream.rangeClosed(131, 144).toArray()); // Lay 1 cau
            int[] array4 = shuffleArray(IntStream.rangeClosed(145, 174).toArray()); // Lay 1 cau
            int[] array5 = shuffleArray(IntStream.rangeClosed(175, 199).toArray()); // Lay 1 cau
            int[] array6 = shuffleArray(IntStream.rangeClosed(200, 254).toArray()); // Lay 1 cau
            int[] array7 = shuffleArray(IntStream.rangeClosed(255, 354).toArray()); // Lay 9 cau
            int[] array8 = shuffleArray(IntStream.rangeClosed(355, 449).toArray()); // Lay 9 cau
            List<Question> ques = new ArrayList<>();
            ques.add(questions.get(array1[0]));
            for (int i=0;i<=6;i++) {
                ques.add(questions.get(array2[i]));
            }
            ques.add(questions.get(array3[0]));
            ques.add(questions.get(array4[0]));
            ques.add(questions.get(array5[0]));
            ques.add(questions.get(array6[0]));
            for (int i=0;i<=8;i++) {
                ques.add(questions.get(array7[i]));
            }
            for (int i=0;i<=8;i++) {
                ques.add(questions.get(array8[i]));
            }
            questionsPerContest.add(ques);
        }
        return  questionsPerContest;
    }

    public List<List<Question>> setQuestionForEachContest(int typeOfContext, List<Question> questions) {
        if (typeOfContext == TypeOfContest.a1a2) {
            return setQuestionForEachContestA1A2(questions);
        } else {
            return setQuestionForEachContestB1B2(questions);
        }
    }

    private int[] shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

}
