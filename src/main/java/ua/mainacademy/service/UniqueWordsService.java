package ua.mainacademy.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
public class UniqueWordsService {

    public Map<String, Long> getUniqueSortedWords(@NonNull String text) {
        Map<String, Long> collect = getUniqueWords(text).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> sorted = collect.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .sorted(Comparator.comparing(
                        (Function<Map.Entry<String, Long>, Long>) Map.Entry::getValue).reversed())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        return sorted;
    }

    private List<String> getUniqueWords(String text) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9а-яА-Я]+)");
        Matcher matcher = pattern.matcher(text);
        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }
}
