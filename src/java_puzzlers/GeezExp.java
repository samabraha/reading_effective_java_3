package java_puzzlers;

import java.util.List;
import java.util.Scanner;

public class GeezExp {
    private static final int END = Integer.MAX_VALUE ;
    private static final int START = END - 100;

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Word: ");
        String input = "";
        while (!input.equalsIgnoreCase("quit")) {
            input = scanner.nextLine();
            String word = getRoot(input);
            System.out.println(input + ": " + word);
        }

    }

    private static String getRoot(String word) {
        char firstLetter = word.toCharArray()[0];
        word = switch (firstLetter) {
            case 'እ','ት', 'ይ', 'የ', 'አ' -> word.substring(1);
            default -> word;
        };

        System.out.printf("%s: %s", word, word.toCharArray());
        return word;
    }

    private static PartOfSpeech getPartOfSpeech(String word) {
        PartOfSpeech partOfSpeech;
            if (isPronoun(word)) {
                return PartOfSpeech.PRONOUN;
            }

            if (isNoun(word)) {
                return PartOfSpeech.NOUN;
            }
            if (isAdjective(word)) {
                return PartOfSpeech.ADJECTIVE;
            }
            if (isVerb(word)) {
                return PartOfSpeech.VERB;
            }

        return PartOfSpeech.INDETERMINATE;
    }

    private static boolean isVerb(String word) {
        return false;
    }

    private static boolean isPronoun(String word) {
        var pronouns = List.of("ኣነ", "ንሕና", "ንስኺ", "ንስኻ", "ንስኻትክን", "ንስኻትኩም", "ንሳ", "ንሱ", "ንሳተን", "ንሳቶም");
        return pronouns.contains(word);
    }

    private static boolean isAdjective(String word) {
        return false;
    }

    private static boolean isNoun(String word) {
        return false;
    }
}

enum PartOfSpeech { VERB, NOUN, ADJECTIVE, PRONOUN, INDETERMINATE }
enum Gender { MALE, FEMALE, NEUTRAL }

