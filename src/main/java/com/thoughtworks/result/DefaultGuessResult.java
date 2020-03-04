package com.thoughtworks.result;

import com.thoughtworks.answer.Answer;

public class DefaultGuessResult implements GuessResult {

  private static final String RESULT_TEMPLATE = "%sA%sB";

  private int correctCount;
  private int onlyNumberCorrectCount;

  public DefaultGuessResult(int correctCount, int onlyNumberCorrectCount) {
    this.correctCount = correctCount;
    this.onlyNumberCorrectCount = onlyNumberCorrectCount;
  }

  @Override
  public boolean isCorrect() {
    return correctCount == Answer.LENGTH;
  }

  @Override
  public String display() {
    return String.format(RESULT_TEMPLATE, correctCount, onlyNumberCorrectCount);
  }
}
