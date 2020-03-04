package com.thoughtworks.generate;

import com.thoughtworks.answer.Answer;
import com.thoughtworks.answer.AnswerValidator;
import com.thoughtworks.exception.AnswerGenerateException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;

public class FileAnswerGenerator implements AnswerGenerator {

  private Path filePath;
  private AnswerValidator answerValidator;

  public FileAnswerGenerator(Path filePath, AnswerValidator answerValidator) {
    this.filePath = filePath;
    this.answerValidator = answerValidator;
  }

  @Override
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
      throw new AnswerGenerateException();
    }
  }
}
