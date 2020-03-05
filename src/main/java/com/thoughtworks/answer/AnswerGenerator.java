package com.thoughtworks.answer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class AnswerGenerator {

  private static final int ANSWER_NUMBER_LIMIT = 10;
  private static final String ANSWER_DELIMITER = "";

  private final AnswerValidator answerValidator = new AnswerValidator();
  private final Path filePath;

  public AnswerGenerator(Path filePath) {
    this.filePath = filePath;
  }

  public Answer generate() {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(filePath.toString())) {
      Objects.requireNonNull(is);
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String number = reader.readLine();

      Answer answer = new Answer(number);
      answerValidator.validate(answer);
      return answer;
    } catch (Exception e) {
      return randomGenerate();
    }
  }

  private Answer randomGenerate() {
    Random random = new Random();
    Set<String> digits = new HashSet<>();
    while (digits.size() < Answer.LENGTH) {
      int digit = random.nextInt(ANSWER_NUMBER_LIMIT);
      digits.add(String.valueOf(digit));
    }
    String number = String.join(ANSWER_DELIMITER, digits);

    return new Answer(number);
  }
}
