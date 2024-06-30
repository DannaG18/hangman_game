package com.hangman_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.text.MessageFormat;

public class Hangman {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){
            Random random = new Random();
            List <String> words = new ArrayList <>();
            words.add("apple");
            words.add("grape");
            words.add("blueberry");
            words.add("strawberry");
            words.add("maracuya");
            words.add("papaya");
            words.add("banana");
            words.add("kiwi");
            words.add("granade");
            words.add("avocado");

            int counterWords = random.nextInt(words.size());
            String selectedWord = words.get(counterWords);

            System.out.println("Welcome to the HangMan game! \n   Are u ready for fun? \n Enter to continue...");
            sc.nextLine();
            clearConsole();

            char[] characterArray = selectedWord.toCharArray();
            char[] guessedLetters = new char[characterArray.length];
            for (int i = 0; i < guessedLetters.length; i++) {
                guessedLetters[i] = '_';
            }

            boolean wordGuessed = false;
            int attempts = 0;
            

            while (!wordGuessed && attempts < 10) {
                System.out.println("Guess the letters of the word! (it's a fruit) \n Enter de letter: ");
                String letter;
                while (true) {
                    System.out.print("- ");
                    letter = sc.nextLine().toLowerCase();
                    
                    if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a single letter");
                    }
                }
                clearConsole();
                boolean letterFound = false;
                for (int i = 0; i < characterArray.length; i++) {
                    if (characterArray[i] == letter.charAt(0)) {
                        guessedLetters[i] = characterArray[i];
                        letterFound = true;
                    }
                }

                if (letterFound) {
                    System.out.println("Good guess!!");
                } else {
                    System.out.println("Wrong guess the letter don't match :( \n");
                    attempts++;
                }

                System.out.println(MessageFormat.format("Current state of the word: {0} \n Enter to continue!", new String(guessedLetters)));
                sc.nextLine();
                clearConsole();

                if (new String (guessedLetters).equals(selectedWord)) {
                    wordGuessed = true;
                    System.out.println("Congrats! U guess the word -> " + selectedWord);
                }
            }

            if (!wordGuessed) {
                System.out.println("Sorry, you ran out of attempts... the word was: " + selectedWord);
            }
        }
    }

    public static void clearConsole() {
        try {
            if(System.getProperty("os.name").contains("Window")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
        }
    }
}
