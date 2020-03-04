package com.thoughtworks.answer;

import com.thoughtworks.result.DefaultGuessResult;
import com.thoughtworks.result.GuessResult;

public class DefaultAnswerJudge implements AnswerJudge {

  public GuessResult judge(String answer, String guess) {
    int correctCount = 0;
    int onlyNumberCorrectCount = 0;

    for (char num : guess.toCharArray()) {
      boolean isCorrectNumber = answer.contains(String.valueOf(num));
      boolean isCorrectPosition = answer.indexOf(num) == guess.indexOf(num);
      if (isCorrectNumber && isCorrectPosition) {
        correctCount++;
      }
      if (isCorrectNumber && !isCorrectPosition) {
        onlyNumberCorrectCount++;
      }
    }

    return new DefaultGuessResult(correctCount, onlyNumberCorrectCount);
  }
}
