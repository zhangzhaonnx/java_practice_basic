package com.thoughtworks.game;

import com.thoughtworks.answer.Answer;
import com.thoughtworks.result.GuessResult;

public class GuessRecord {

  private final Answer guess;
  private final GuessResult result;

  public GuessRecord(Answer guess, GuessResult result) {
    this.guess = guess;
    this.result = result;
  }

  public GuessResult getResult() {
    return result;
  }

  @Override
  public String toString() {
    return String.format("%s %s", guess, result.display());
  }
}
