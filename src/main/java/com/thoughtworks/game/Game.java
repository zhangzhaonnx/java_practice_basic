package com.thoughtworks.game;

import com.thoughtworks.answer.Answer;
import com.thoughtworks.answer.AnswerGenerator;
import com.thoughtworks.answer.AnswerJudge;
import com.thoughtworks.answer.AnswerValidator;
import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.result.GuessResult;
import com.thoughtworks.result.WrongInputGuessResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private static final Path ANSWER_FILE_PATH = Paths.get("answer.txt");
  private static final int CHANCE_LIMIT = 6;

  private final AnswerValidator answerValidator = new AnswerValidator();
  private final AnswerJudge answerJudge = new AnswerJudge();
  private final Answer answer;

  private int remainChance = CHANCE_LIMIT;
  private List<GuessRecord> records = new ArrayList<>();

  public Game() {
    AnswerGenerator answerGenerator = new AnswerGenerator(ANSWER_FILE_PATH);
    this.answer = answerGenerator.generate();
  }

  public GuessResult guess(Answer guess) {
    GuessResult result;
    try {
      answerValidator.validate(guess);
      result = answerJudge.judge(answer, guess);
      remainChance--;
    } catch (InvalidAnswerException e) {
      result = new WrongInputGuessResult();
    }

    records.add(new GuessRecord(guess, result));
    return result;
  }

  public Answer getAnswer() {
    return answer;
  }

  public boolean isEnd() {
    GuessRecord lastRecord = records.get(records.size() - 1);
    return lastRecord.getResult().isCorrect() || remainChance <= 0;
  }

  public List<GuessRecord> getRecords() {
    return records;
  }
}
