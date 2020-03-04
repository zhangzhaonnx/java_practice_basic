package com.thoughtworks.game;

import com.thoughtworks.answer.DefaultAnswerJudge;
import com.thoughtworks.generate.FileAnswerGenerator;
import com.thoughtworks.generate.MultiAnswerGenerator;
import com.thoughtworks.generate.RandomAnswerGenerator;
import com.thoughtworks.answer.AnswerValidator;
import com.thoughtworks.answer.SimpleAnswerValidator;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameBuilder {

  public static final Path ANSWER_FILE_PATH = Paths.get("answer.txt");

  public static Game buildDefaultGame() {
    AnswerValidator answerValidator = new SimpleAnswerValidator();

    MultiAnswerGenerator answerGenerator = new MultiAnswerGenerator();
    answerGenerator.addAnswerGenerator(new FileAnswerGenerator(ANSWER_FILE_PATH, answerValidator));
    answerGenerator.addAnswerGenerator(new RandomAnswerGenerator());

    return new Game(answerGenerator, answerValidator, new DefaultAnswerJudge());
  }
}
