package com.thoughtworks;

import com.thoughtworks.generate.FileAnswerGenerator;
import com.thoughtworks.generate.MultiAnswerGenerator;
import com.thoughtworks.generate.RandomAnswerGenerator;
import com.thoughtworks.validate.AnswerValidator;
import com.thoughtworks.validate.SimpleAnswerValidator;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameBuilder {

  public static final Path ANSWER_FILE_PATH = Paths.get("answer.txt");

  public static Game buildDefaultGame() {
    AnswerValidator answerValidator = new SimpleAnswerValidator();

    MultiAnswerGenerator answerGenerator = new MultiAnswerGenerator();
    answerGenerator.addAnswerGenerator(new FileAnswerGenerator(ANSWER_FILE_PATH, answerValidator));
    answerGenerator.addAnswerGenerator(new RandomAnswerGenerator());

    return new Game(answerGenerator, answerValidator);
  }
}
