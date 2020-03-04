package com.thoughtworks;

import com.thoughtworks.result.GuessResult;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Game game = GameBuilder.buildDefaultGame();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      GuessResult result = game.guess(input);
      printRecords(game.getRecords());

      if (game.isEnd()) {
        printEndMessage(game, result);
        break;
      }
    }
  }

  private static void printEndMessage(Game game, GuessResult result) {
    if (result.isCorrect()) {
      System.out.println("Congratulations, you win!");
    } else {
      System.out.println(
          String.format("Unfortunately, you have no chance, the answer is %s!",
              game.getAnswer()));
    }
  }

  private static void printRecords(List<GuessRecord> records) {
    for (GuessRecord record : records) {
      System.out.println(record);
    }
  }
}
