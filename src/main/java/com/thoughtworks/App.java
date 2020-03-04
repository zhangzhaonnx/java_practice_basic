package com.thoughtworks;

import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.result.GuessResult;
import com.thoughtworks.result.WrongInputGuessResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  public static final int CHANCE_LIMIT = 6;

  public static void main(String[] args) {
    Game game = GameBuilder.buildDefaultGame();

    Scanner scanner = new Scanner(System.in);
    List<Record> records = new ArrayList<>();

    boolean right = false;
    int count = 0;
    while (count < CHANCE_LIMIT) {
      String input = scanner.nextLine();

      GuessResult result = game.guess(input);
      records.add(new Record(input, result.display()));
      printRecords(records);

      if (result.isCorrect()) {
        right = true;
        break;
      }
      count++;
    }

    if (right) {
      System.out.println("Congratulations, you win!");
    } else {
      System.out.println(
          String.format("Unfortunately, you have no chance, the answer is %s!", game.getAnswer()));
    }
  }

  private static void printRecords(List<Record> records) {
    for (Record record : records) {
      System.out.println(String.format("%s %s", record.getInput(), record.getOutput()));
    }
  }
}
