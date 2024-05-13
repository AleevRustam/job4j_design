package ru.job4j.io;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Rustam Aleev");
    }

    @Test
    void whenPairHasBadKeyFormat() {
        String path = "./data/pair_with_bad_key_format.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairHasBadValueFormat() {
        String path = "./data/pair_with_bad_value_format.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairWithTwoSignEqualInValue() {
        String path = "./data/pair_with_two_sign_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Rustam=Aleev");
    }
}