package com.thoughtworks.answer;

import com.thoughtworks.result.GuessResult;

public interface AnswerJudge {

  GuessResult judge(String answer, String guess);
}
