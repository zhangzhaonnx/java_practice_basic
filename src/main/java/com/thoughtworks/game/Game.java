package com.thoughtworks.game;

import com.thoughtworks.answer.Answer;
import com.thoughtworks.answer.AnswerJudge;
import com.thoughtworks.answer.AnswerValidator;
import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.generate.AnswerGenerator;
import com.thoughtworks.result.GuessResult;
import com.thoughtworks.result.WrongInputGuessResult;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private static final int CHANCE_LIMIT = 6;

  private final AnswerValidator answerValidator;
  private final AnswerJudge answerJudge;
  private final Answer answer;

  private int remainChance = CHANCE_LIMIT;
  private List<GuessRecord> records = new ArrayList<>();

  public Game(AnswerGenerator answerGenerator, AnswerValidator answerValidator,
      AnswerJudge answerJudge) {
    this.answer = answerGenerator.generate();
    this.answerValidator = answerValidator;
    this.answerJudge = answerJudge;
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
