package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseEmptyStringThanTrowsIllegalArgumentException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenNameFormatIsBadThanHasMassage() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"key:value"};

        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining("does not contain the symbol '='")
                .hasMessageContaining("key:value");
    }

    @Test
    void whenNameStartsWithSymbolEqualThanHasMassage() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"=Value"};

        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining("=Value");
    }

    @Test
    void whenNameEndsWithSymbolEqualThanHasMassage() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"Key="};

        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining("Key=");
    }

}