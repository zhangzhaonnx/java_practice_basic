package com.thoughtworks;

import com.thoughtworks.answer.Answer;
import com.thoughtworks.game.Game;
import com.thoughtworks.game.GuessRecord;
import com.thoughtworks.result.GuessResult;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Game game = new Game();

    GuessResult result;
    Scanner scanner = new Scanner(System.in);
    do {
      String guess = scanner.nextLine();
      result = game.guess(new Answer(guess));
      printRecords(game.getRecords());
    } while (!game.isEnd());

    printEndMessage(game, result);
  }

  private static void printRecords(List<GuessRecord> records) {
    for (GuessRecord record : records) {
      System.out.println(record);
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
}
