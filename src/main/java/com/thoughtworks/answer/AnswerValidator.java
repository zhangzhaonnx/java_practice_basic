package com.thoughtworks.answer;

import com.thoughtworks.exception.InvalidAnswerException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnswerValidator {

  public void validate(Answer answer) {
    String number = answer.getNumber();
    boolean valid = number.length() == Answer.LENGTH;

    for (char num : number.toCharArray()) {
      if (!Character.isDigit(num)) {
        valid = false;
        break;
      }
    }

    Set<String> digits = new HashSet<>(Arrays.asList(number.split("")));
    if (digits.size() < number.length()) {
      valid = false;
    }

    if (!valid) {
      throw new InvalidAnswerException();
    }
  }
}
