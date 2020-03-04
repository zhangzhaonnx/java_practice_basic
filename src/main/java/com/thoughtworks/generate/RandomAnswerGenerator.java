package com.thoughtworks.generate;

import com.thoughtworks.answer.Answer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomAnswerGenerator implements AnswerGenerator {

  private static final int ANSWER_NUMBER_LIMIT = 10;
  private static final String ANSWER_DELIMITER = "";
  private Random random;

  public RandomAnswerGenerator() {
    this.random = new Random();
  }

  @Override
  public Answer generate() {
    Set<String> digits = new HashSet<>();
    while (digits.size() < Answer.LENGTH) {
      int digit = random.nextInt(ANSWER_NUMBER_LIMIT);
      digits.add(String.valueOf(digit));
    }
    String number = String.join(ANSWER_DELIMITER, digits);

    return new Answer(number);
  }
}
