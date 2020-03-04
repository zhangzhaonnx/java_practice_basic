package com.thoughtworks.generator;

import com.thoughtworks.exception.AnswerGenerateException;
import java.util.ArrayList;
import java.util.List;

public class MultiAnswerGenerator implements AnswerGenerator {

  private List<AnswerGenerator> answerGenerators = new ArrayList<>();

  public void addAnswerGenerator(AnswerGenerator answerGenerator) {
    answerGenerators.add(answerGenerator);
  }

  @Override
  public String generate() {
    for (AnswerGenerator generator : answerGenerators) {
      try {
        return generator.generate();
      } catch (Exception e) {
        // ignore
      }
    }

    throw new AnswerGenerateException();
  }
}
