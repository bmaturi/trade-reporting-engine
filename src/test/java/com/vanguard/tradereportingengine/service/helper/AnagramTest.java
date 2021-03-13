package com.vanguard.tradereportingengine.service.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnagramTest {

    @Test
    void isAnagramTrue() {
        assertEquals(Boolean.TRUE, Anagram.isAnagram("abc", "cba"));
    }

    @Test
    void isAnagramFalse() {
        assertEquals(Boolean.FALSE, Anagram.isAnagram("abf", "cba"));
    }

    @Test
    void isAnagramSpecialChar() {
        assertEquals(Boolean.TRUE, Anagram.isAnagram("BANK_EMU", "EMU_BANK"));
    }

}
