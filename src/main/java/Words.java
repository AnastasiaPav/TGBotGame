import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Words {

    private Set<String> usedWords = new HashSet<>();
    private String line;
    private String previousWord = "test0";
    private final String FILE_PATH ="C:\\Users\\Павлова Анастасия\\Desktop\\Bot\\src\\main\\java\\easy-text.txt";

    public String gameLogic(String word) throws IOException {

        if(!previousWord.equals("test0")) {
            if (word.charAt(0) != previousWord.charAt(previousWord.length() - 1)) {
                return "bad symbol";
            }
        }
        BufferedReader reader = null;
        FileInputStream fIn = new FileInputStream(FILE_PATH);

        reader = new BufferedReader(new InputStreamReader(fIn));


        boolean isCorrect = false;
        while ((line = reader.readLine()) != null) {

            if (word.equals(line) && !usedWords.contains(word)) {
                isCorrect = true;
                usedWords.add(word);
                System.out.println(word);
                break;
            }

        }


        if (!isCorrect) {
            return "Wrong word";
        }

        fIn.getChannel().position(0);
        reader = new BufferedReader(new InputStreamReader(fIn));
        while ((line = reader.readLine()) != null) {
            if (word.endsWith(String.valueOf(line.charAt(0))) && !usedWords.contains(line)) {
                previousWord = line;
                return line;
            }
        }
        return "You win";
    }


}
