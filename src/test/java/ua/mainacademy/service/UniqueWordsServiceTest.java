package ua.mainacademy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

class UniqueWordsServiceTest {

    @Test
    void getUniqueSortedWordsTest() {
        String text = "Charlie Jennifer Charlie Bob Charlie Charlie Bob Jennifer Alice Alice";
        Map<String, Long> expected = new LinkedHashMap<>();
            expected.put("Charlie", 4L);
            expected.put("Alice", 2L);
            expected.put("Bob", 2L);
            expected.put("Jennifer", 2L);

        Map<String, Long> sortedWords = new UniqueWordsService().getUniqueSortedWords(text);
        Assertions.assertEquals(sortedWords.keySet(),expected.keySet());
        Assertions.assertEquals(new HashSet<>(sortedWords.values()),
                new HashSet<>(expected.values()));
    }

    @Test
    void nullTextTest() {
        Assertions.assertThrows(NullPointerException.class, ()->{
            new UniqueWordsService().getUniqueSortedWords(null);
        });
    }
}