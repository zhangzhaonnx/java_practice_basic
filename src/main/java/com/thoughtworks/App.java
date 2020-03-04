package com.thoughtworks;

import com.thoughtworks.exception.InvalidAnswerException;
import com.thoughtworks.generator.AnswerGenerator;
import com.thoughtworks.generator.FileAnswerGenerator;
import com.thoughtworks.generator.MultiAnswerGenerator;
import com.thoughtworks.generator.RandomAnswerGenerator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  public static final int CHANCE_LIMIT = 6;
  public static final String WRIGHT_OUTPUT = "4A0B";
  public static final Path ANSWER_FILE_PATH = Paths.get("answer.txt");

  public static void main(String[] args) {
    AnswerValidator answerValidator = new SimpleAnswerValidator();
    AnswerGenerator fileAnswerGenerator = new FileAnswerGenerator(ANSWER_FILE_PATH, answerValidator);
    AnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
    MultiAnswerGenerator answerGenerator = new MultiAnswerGenerator();
    answerGenerator.addAnswerGenerator(fileAnswerGenerator);
    answerGenerator.addAnswerGenerator(randomAnswerGenerator);

    GussNumberGame game = new GussNumberGame(answerGenerator,
        answerValidator);

    Scanner scanner = new Scanner(System.in);
    List<Record> records = new ArrayList<>();

    boolean right = false;
    int count = 0;
    while (count < CHANCE_LIMIT) {
      String input = scanner.nextLine();
      try {
        String output = game.guess(input);
        records.add(new Record(input, output));
        printRecords(records);

        if (output.equals(WRIGHT_OUTPUT)) {
          right = true;
          break;
        }
        count++;
      } catch (InvalidAnswerException e) {
        System.out.println("Wrong input");
      }
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
