package com.thoughtworks.result;

public class WrongInputGuessResult implements GuessResult {

  @Override
  public String display() {
    return "Wrong input";
  }

  @Override
  public boolean isCorrect() {
    return false;
  }
}
