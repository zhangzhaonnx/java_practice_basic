package com.thoughtworks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimpleAnswerValidator implements AnswerValidator {

  public static final int ANSWER_LENGTH = 4;

  @Override
  public void validate(String answer) {
    boolean valid = answer.length() == ANSWER_LENGTH;

    for (char num : answer.toCharArray()) {
      if (!Character.isDigit(num)) {
        valid = false;
        break;
      }
    }

    Set<String> numbers = new HashSet<>(Arrays.asList(answer.split("")));
    if (numbers.size() < answer.length()) {
      valid = false;
    }

    if (!valid) {
      throw new InvalidAnswerException();
    }
  }
}
