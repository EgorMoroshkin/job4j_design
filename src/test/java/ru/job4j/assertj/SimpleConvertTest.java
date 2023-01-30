package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "4", "1");
        assertThat(list).hasSize(5)
                .isNotNull()
                .doesNotContain("One")
                .containsAnyOf("99", "98", "3")
                .endsWith("3", "4", "1");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Monday", "Friday", "Wednesday", "Thursday");
        assertThat(set).hasSize(4)
                .containsSequence("Monday", "Friday")
                .isNullOrEmpty();
        assertThat(set).first().isEqualTo("Monday");
        assertThat(set).element(3).isEqualTo("Thursday");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "3", "4", "5", "6");
        assertThat(map).hasSize(6)
                .containsKeys("1", "3", "6")
                .containsValues(0, 1, 2, 3, 4, 5)
                .doesNotContainKey("9")
                .doesNotContainValue(6)
                .containsEntry("3", 2);
    }
}