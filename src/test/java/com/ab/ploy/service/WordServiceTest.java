package com.ab.ploy.service;

import com.ab.ploy.model.Word;
import com.ab.ploy.persistence.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WordServiceTest {

    private WordRepository wordRepository = mock(WordRepository.class);

    private WordService unitUndertest;

    @BeforeEach
    void beforeEach() {
        unitUndertest = new WordService(wordRepository);
    }

    @Test
    void createWord_shouldPropagateSpanishAndEnglish() {
        Word word = createWord();
    }

    private Word createWord() {
        return null;
    }

}
