package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("host")).isEqualTo("127.0.0.1");
        assertThat(config.value("gateway")).isEqualTo("255.255.255.0");
    }

    @Test
    void whenPairWithEmptyLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("red")).isEqualTo("#FF0000");
        assertThat(config.value("blue")).isEqualTo("#0000FF");
    }

    @Test
    void whenPairErrorInValue() {
        String path = "./data/pair_with_error_in_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .message().isNotEmpty();
    }

    @Test
    void whenPairErrorInKey() {
        String path = "./data/pair_with_error_in_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairErrorInCharEqual() {
        String path = "./data/pair_with_error_in_char_equal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .message().isNotEmpty();
    }

    @Test
    void whenPairErrorThreeWords() {
        String path = "./data/pair_with_error_three_words.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=value");
    }

    @Test
    void whenPairErrorTwoWords() {
        String path = "./data/pair_with_error_two_words.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=");
    }
}