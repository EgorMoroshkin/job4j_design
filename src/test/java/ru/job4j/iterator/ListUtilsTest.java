package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfEvenNumbersPredicat() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(input).containsSequence(1, 3, 5);
    }

    @Test
    void removeIfNumberMoreTwoPredicat() {
        ListUtils.removeIf(input, x -> x > 2);
        assertThat(input).hasSize(1);
    }

    @Test
    void replaceIfLessThreeReplaceNinePredicat() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, x -> x < 3, 9);
        assertThat(input).containsSequence(9, 9, 3, 4, 5, 6);
    }

    @Test
    void replaceIfMoreFourReplaceOnePredicat() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, x -> x > 4, 1);
        assertThat(input).containsSequence(1, 2, 3, 4, 1, 1);
    }

    @Test
    void removeAllElementFromListSizeOne() {
        List<Integer> list = List.of(3);
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void removeAllElementFromListSizeAsList() {
        List<Integer> list = List.of(3, 1);
        ListUtils.removeAll(input, list);
        assertThat(input).isEmpty();
    }

    @Test
    void removeAllElementFromListMoreList() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> list = List.of(2, 5, 6);
        ListUtils.removeAll(input, list);
        assertThat(input).containsSequence(1, 3, 4);
    }
}