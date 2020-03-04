package com.thoughtworks;

import com.thoughtworks.generator.AnswerGenerator;

public class GussNumberGame {

  private static final String RESULT_TEMPLATE = "%sA%sB";
  private AnswerValidator answerValidator;
  private String answer;

  public GussNumberGame(AnswerGenerator answerGenerator, AnswerValidator answerValidator) {
    this.answer = answerGenerator.generate();
    this.answerValidator = answerValidator;
  }

  public String guess(String numbers) {
    answerValidator.validate(numbers);

    int positionAndNumberCorrectCount = 0;
    int onlyNumberCorrectCount = 0;

    for (char num : numbers.toCharArray()) {
      boolean isCorrectNumber = answer.contains(String.valueOf(num));
      boolean isCorrectPosition = answer.indexOf(num) == numbers.indexOf(num);
      if (isCorrectNumber && isCorrectPosition) {
        positionAndNumberCorrectCount++;
      }
      if (isCorrectNumber && !isCorrectPosition) {
        onlyNumberCorrectCount++;
      }
    }

    return String.format(RESULT_TEMPLATE, positionAndNumberCorrectCount, onlyNumberCorrectCount);
  }

  public String getAnswer() {
    return answer;
  }
}
