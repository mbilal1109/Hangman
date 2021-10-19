import java.io.*;
import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = loadWords();
        Random random = new Random();
        String secret_word = words[random.nextInt(words.length)];

        String temp = symbol(secret_word);
        char[] charword = stringToChar(temp);

        String used_letters = " ";
        System.out.println("Welcome to Hangman!");
        System.out.println(
                "I am thinking of a word that is " + secret_word.length() + " letters long." + "\n" + "-------------");
        int count = 5;
        while (!isEqual(charword, secret_word)) {
            System.out.println("You have " + count + " guesses left.");
            System.out.print("Please guess a letter: ");
            String str = sc.nextLine();
            if (used_letters.contains(str)) {
                System.out.println(
                        "Oops! You've already guessed that letter " + printArray(charword) + "\n" + "-------------");
                continue;
            }
            boolean flag = false;
            for (int i = 0; i < secret_word.length(); i++) {
                boolean result = (secret_word.charAt(i) + "").equals(str);
                if (result == true) {
                    charword = toChar(i, charword, str);
                    flag = true;
                }
            }
            used_letters += str;
            if (flag) {
                System.out.println("Good guess: " + printArray(charword) + "\n" + "-------------");

                continue;
            } else {
                System.out.println(
                        "Oops! That letter is not in my word " + printArray(charword) + "\n" + "-------------");

            }
            count--;
            if (count == 0) {
                break;
            }
        }
        if (isEqual(charword, secret_word)) {
            System.out.println("Congratulations, you won!");
        } else {
            char[] secretArr = stringToChar(secret_word);
            System.out.println("Sorry, you ran out of guesses. The word was " + printArray(secretArr));
        }

    }

    public static char[] toChar(int index, char[] temp, String c) {
        char s = c.charAt(0);
        temp[index] = s;
        return temp;
    }

    public static boolean isEqual(char[] array, String str) {
        String str1 = "";
        for (int i = 0; i < array.length; i++) {
            str1 = str1 + "" + array[i];
        }
        return str.equals(str1);
    }

    public static String symbol(String word) {
        String temp = "";
        for (int i = 0; i < word.length(); i++) {
            temp = temp + " _ ";
        }
        return temp;
    }

    public static String[] loadWords() {
        /*
         * Returns a String array of valid words. Also prints out the total number of
         * words (Strings) in the array.
         */
        ArrayList<String> wordList = new ArrayList<String>();
        File f = new File("words.txt");
        String[] wordsArr = new String[wordList.size()];
        try {
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                String word = in.next();
                wordList.add(word);
            }
            in.close();
            System.out.println("Loading words from the file......");
            System.out.println(wordList.size() + " words loaded.");
            wordsArr = (String[]) wordList.toArray(wordsArr);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        return wordsArr;
    }

    public static String printArray(char[] secretArr) {
        String temp = "";
        for (int i = 0; i < secretArr.length; i++) {
            temp += "" + secretArr[i];
        }
        return temp;
    }

    public static char[] stringToChar(String secretWord) {
        /**
         * takes a string which is a secretWord Returns a char array of secretWord You
         * can use printArray method to test the output
         */
        char[] secretArr = new char[secretWord.length()];
        for (int i = 0; i < secretArr.length; i++) {
            secretArr[i] = secretWord.charAt(i);
        }
        return secretArr;
    }
    
}
