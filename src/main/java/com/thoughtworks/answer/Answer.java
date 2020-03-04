package com.thoughtworks.answer;

public class Answer {

  public static final int LENGTH = 4;

  private String number;

  public Answer(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return number;
  }
}
