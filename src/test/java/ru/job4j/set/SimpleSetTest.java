package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddOne() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddThreeElementAnaFindElementOther() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(5)).isTrue();
        assertThat(set.contains(6)).isFalse();
        assertThat(set.add(6)).isTrue();
        assertThat(set.contains(7)).isFalse();
        assertThat(set.add(7)).isTrue();
        assertThat(set.contains(8)).isFalse();
        assertThat(set.add(8)).isTrue();
    }
}