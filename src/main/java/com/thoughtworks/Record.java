package com.thoughtworks;

public class Record {

  private final String input;
  private final String output;

  public Record(String input, String output) {
    this.input = input;
    this.output = output;
  }

  public String getInput() {
    return input;
  }

  public String getOutput() {
    return output;
  }
}
