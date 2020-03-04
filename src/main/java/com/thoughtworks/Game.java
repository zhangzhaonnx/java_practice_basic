package com.thoughtworks;

import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.generate.AnswerGenerator;
import com.thoughtworks.result.DefaultGuessResult;
import com.thoughtworks.result.GuessResult;
import com.thoughtworks.result.WrongInputGuessResult;
import com.thoughtworks.validate.AnswerValidator;

public class Game {

  private AnswerValidator answerValidator;
  private String answer;

  public Game(AnswerGenerator answerGenerator, AnswerValidator answerValidator) {
    this.answer = answerGenerator.generate();
    this.answerValidator = answerValidator;
  }

  public GuessResult guess(String numbers) {
    try {
      answerValidator.validate(numbers);
    } catch (InvalidAnswerException e) {
      return new WrongInputGuessResult();
    }

    int correctCount = 0;
    int onlyNumberCorrectCount = 0;

    for (char num : numbers.toCharArray()) {
      boolean isCorrectNumber = answer.contains(String.valueOf(num));
      boolean isCorrectPosition = answer.indexOf(num) == numbers.indexOf(num);
      if (isCorrectNumber && isCorrectPosition) {
        correctCount++;
      }
      if (isCorrectNumber && !isCorrectPosition) {
        onlyNumberCorrectCount++;
      }
    }

    return new DefaultGuessResult(correctCount, onlyNumberCorrectCount);
  }

  public String getAnswer() {
    return answer;
  }
}
