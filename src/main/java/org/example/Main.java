package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputText = "Це перше відоме речення. Це друге речення. Це третє речення."; // текст
        System.out.println("Вхідний текст:");
        System.out.println(inputText);

        Text text = new Text(inputText);

        System.out.println("\nРечення в тексті:");
        for (int i = 0; i < text.getSentences().size(); i++) {
            System.out.println("Речення " + (i + 1) + ": " + text.getSentences().get(i));
        }

        // Слова з першого речення
        Set<String> firstWords = text.getFirstSentence().getWordsAsStrings();
        System.out.println("\nСлова з першого речення:");
        System.out.println(firstWords);

        // Всі слова з наступних речень
        Set<String> subsequentWords = text.getSubsequentSentencesWords();
        System.out.println("\nСлова з наступних речень:");
        System.out.println(subsequentWords);

        // Унікальні слова
        StringBuffer uniqueWords = getUniqueWords(firstWords, subsequentWords);
        System.out.println("\nУнікальні слова:");
        System.out.println(uniqueWords.toString().trim());
    }

    // Метод для отримання унікальних слів
    public static StringBuffer getUniqueWords(Set<String> firstWords, Set<String> subsequentWords) {
        StringBuffer uniqueWords = new StringBuffer();
        for (String word : firstWords) {
            if (!subsequentWords.contains(word)) {
                uniqueWords.append("\"").append(word).append("\" ").append("\n");
            }
        }
        return uniqueWords;
    }
}

// Клас для роботи з текстом
class Text {
    private final List<Sentence> sentences;

    public Text(String inputText) {
        // Очищення тексту від зайвих пробілів
        String cleanedText = inputText.replaceAll("\\s+", " ").trim();
        this.sentences = new ArrayList<>();
        String[] sentenceArray = cleanedText.split("\\. "); // Розбиття на речення
        for (String sentenceText : sentenceArray) {
            sentences.add(new Sentence(sentenceText));
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public Sentence getFirstSentence() {
        return sentences.get(0);
    }

    public Set<String> getSubsequentSentencesWords() {
        Set<String> subsequentWords = new HashSet<>();
        for (int i = 1; i < sentences.size(); i++) {
            subsequentWords.addAll(sentences.get(i).getWordsAsStrings());
        }
        return subsequentWords;
    }
}

// Клас для роботи з реченнями
class Sentence {
    private final List<Word> words;

    public Sentence(String sentenceText) {
        this.words = new ArrayList<>();
        String[] wordArray = sentenceText.split(" "); // Розбиття на слова
        for (String wordText : wordArray) {
            words.add(new Word(wordText));
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public Set<String> getWordsAsStrings() {
        Set<String> wordSet = new HashSet<>();
        for (Word word : words) {
            wordSet.add(word.toString());
        }
        return wordSet;
    }

    @Override
    public String toString() {
        StringBuilder sentenceBuilder = new StringBuilder();
        for (Word word : words) {
            sentenceBuilder.append(word).append(" ");
        }
        return sentenceBuilder.toString().trim();
    }
}

// Клас для роботи зі словами
class Word {
    private final List<Letter> letters;

    public Word(String wordText) {
        this.letters = new ArrayList<>();
        for (char c : wordText.toCharArray()) {
            this.letters.add(new Letter(c));
        }
    }

    @Override
    public String toString() {
        StringBuilder wordBuilder = new StringBuilder();
        for (Letter letter : letters) {
            wordBuilder.append(letter);
        }
        return wordBuilder.toString();
    }
}

// Клас для роботи з літерами
class Letter {
    private final char character;

    public Letter(char character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
