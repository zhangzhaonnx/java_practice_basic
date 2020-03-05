package com.thoughtworks.answer;

import com.thoughtworks.result.NormalGuessResult;
import com.thoughtworks.result.GuessResult;

public class AnswerJudge {

  public GuessResult judge(Answer answer, Answer guess) {
    String answerNumber = answer.getNumber();
    String guessNumber = guess.getNumber();

    int correctCount = 0;
    int onlyNumberCorrectCount = 0;
    for (char digit : guessNumber.toCharArray()) {
      boolean isCorrectNumber = answerNumber.contains(String.valueOf(digit));
      boolean isCorrectPosition = answerNumber.indexOf(digit) == guessNumber.indexOf(digit);
      if (isCorrectNumber && isCorrectPosition) {
        correctCount++;
      }
      if (isCorrectNumber && !isCorrectPosition) {
        onlyNumberCorrectCount++;
      }
    }

    return new NormalGuessResult(correctCount, onlyNumberCorrectCount);
  }
}
