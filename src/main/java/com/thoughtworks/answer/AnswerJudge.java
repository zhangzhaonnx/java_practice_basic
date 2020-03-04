package com.thoughtworks.answer;

import com.thoughtworks.result.GuessResult;

public interface AnswerJudge {

  GuessResult judge(Answer answer, Answer guess);
}
