package com.thoughtworks;

import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.generate.AnswerGenerator;
import com.thoughtworks.result.DefaultGuessResult;
import com.thoughtworks.result.GuessResult;
import com.thoughtworks.result.WrongInputGuessResult;
import com.thoughtworks.validate.AnswerValidator;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private static final int CHANCE_LIMIT = 6;

  private AnswerValidator answerValidator;
  private String answer;
  private int remainChance = CHANCE_LIMIT;
  private List<GuessRecord> records = new ArrayList<>();

  public Game(AnswerGenerator answerGenerator, AnswerValidator answerValidator) {
    this.answer = answerGenerator.generate();
    this.answerValidator = answerValidator;
  }

  public GuessResult guess(String numbers) {
    GuessResult result;
    try {
      answerValidator.validate(numbers);
      result = judge(numbers);
      remainChance--;
    } catch (InvalidAnswerException e) {
      result = new WrongInputGuessResult();
    }

    records.add(new GuessRecord(numbers, result));
    return result;
  }

  private GuessResult judge(String numbers) {
    int correctCount = 0;
    int onlyNumberCorrectCount = 0;

    for (char num : numbers.toCharArray()) {
      boolean isCorrectNumber = answer.contains(String.valueOf(num));
      boolean isCorrectPosition = answer.indexOf(num) == numbers.indexOf(num);
      if (isCorrectNumber && isCorrectPosition) {
        correctCount++;
      }
      if (isCorrectNumber && !isCorrectPosition) {
        onlyNumberCorrectCount++;
      }
    }

    return new DefaultGuessResult(correctCount, onlyNumberCorrectCount);
  }

  public String getAnswer() {
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
