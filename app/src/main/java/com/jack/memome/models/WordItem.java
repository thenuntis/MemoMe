package com.jack.memome.models;

/**
 * Created on 18.10.2016.
 */
public class WordItem {

    private long wordId;
    private String word;
    private String translatedWord;
    private String commentWord;

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getCommentWord() {
        return commentWord;
    }

    public void setCommentWord(String commentWord) {
        this.commentWord = commentWord;
    }
}
