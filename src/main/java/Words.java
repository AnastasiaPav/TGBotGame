import java.io.*;
import java.util.*;

public class Words {
    //private Map<Date, String> usedWords = new HashMap<>();
    private Set<Word> words = new HashSet<>();
    private Word word;
    private String line;
    private String previousWord = "a";
    private final String FILE_PATH = "C:\\Users\\Павлова Анастасия\\Desktop\\Bot\\src\\main\\java\\easy-text.txt";
    FileInputStream fIn = new FileInputStream(FILE_PATH);
    BufferedReader reader = new BufferedReader(new InputStreamReader(fIn));

    public Words() throws FileNotFoundException {
    }

    public String gameLogic(String msg) throws IOException {
        fIn.getChannel().position(0);
        reader = new BufferedReader(new InputStreamReader(fIn));

        System.out.println("Starting logic");
        word = new Word(msg, new Date());

        System.out.println(words.size());

        //word.setLastLetter(Character.toString(msg.charAt(0)));
        //word

        if (!previousWord.equals("a")) {
            if (word.getWord().charAt(0) != previousWord.charAt(previousWord.length() - 1)) {
                return "Wrong symbol. Enter a word that starts with '" + previousWord.charAt(previousWord.length() - 1) + "' symbol";
            }
        }

        boolean isCorrect = true;

        //ищем слово в файле
        System.out.println(word.getWord());
        while ((line = reader.readLine()) != null) {
            if (word.getWord().equals(line)) {
                System.out.println(previousWord.charAt(previousWord.length() - 1));
                for (Word currentWord : words) {
                    if (currentWord.getWord().equals(word.getWord())) {
                        System.out.println("Word " + word.getWord() + " has already been used before");
                        isCorrect = false;
                        break;
                    }
                }

                if (!isCorrect)//если уже юзали, то все, останавливаем
                    break;

                isCorrect = true;
                words.add(new Word(line, new Date()));
                System.out.println("Word is correct");
                System.out.println(word.getWord());
                break;
            }

            System.out.println("Word " + line.toUpperCase() + " does not match");//toUpperCase преобразует в верхний регистр
        }

        if (!isCorrect) {
            return "This word has already been used at " + word.getSentTime().toString() + ". " +
                    "Try choosing another word that starts with '" + previousWord.charAt(previousWord.length() - 1) + "' symbol";
        }

        fIn.getChannel().position(0);
        reader = new BufferedReader(new InputStreamReader(fIn));

        while ((line = reader.readLine()) != null) {
            if (word.getWord().endsWith(String.valueOf(line.charAt(0)))) {
                System.out.println("Bot is checking word " + line.toUpperCase());
                for (Word word1 : words) {//смотрим есть ли уже в нашем сэте якобы новое слово
                    System.out.println("Looking if word " + line.toUpperCase() + " has been used before");
                    if (word1.getWord().equals(line)) {
                        isCorrect = false;
                        System.out.println("Word has been used before");
                        break;
                    } else {
                        isCorrect = true;
                    }
                }

                if (!isCorrect)
                    continue;

                words.add(new Word(line, new Date()));
                System.out.println("Bot has found match in words");
                previousWord = line;
                System.out.println("Bot's answer is " + line.toUpperCase());
                System.out.println();

                for (Word all : words) {
                    System.out.println(all.getWord() + " : " + all.getSentTime());
                }
                return line;
            }
        }

        for (Word all : words) {
            System.out.println(all.getWord() + " : " + all.getSentTime());
        }
        previousWord = "a";
        words.clear();
        return "You win!";
    }
}
