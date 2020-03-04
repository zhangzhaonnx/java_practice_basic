package com.thoughtworks;

import com.thoughtworks.result.GuessResult;

public class GuessRecord {

  private final String guess;
  private final GuessResult result;

  public GuessRecord(String guess, GuessResult result) {
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
