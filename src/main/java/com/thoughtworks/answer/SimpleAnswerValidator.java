package com.thoughtworks.answer;

import com.thoughtworks.exception.InvalidAnswerException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimpleAnswerValidator implements AnswerValidator {

  @Override
  public void validate(String answer) {
    boolean valid = answer.length() == Answer.LENGTH;

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
