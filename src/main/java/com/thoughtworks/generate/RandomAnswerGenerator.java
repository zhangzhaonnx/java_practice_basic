package com.thoughtworks.generate;

import com.thoughtworks.answer.Answer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomAnswerGenerator implements AnswerGenerator {

  private static final int ANSWER_NUMBER_LIMIT = 10;
  private static final String ANSWER_DELIMITER = "";

  @Override
  public String generate() {
    Random random = new Random();

    Set<String> numbers = new HashSet<>();
    while (numbers.size() < Answer.LENGTH) {
      numbers.add(String.valueOf(random.nextInt(ANSWER_NUMBER_LIMIT)));
    }

    return String.join(ANSWER_DELIMITER, numbers);
  }
}
