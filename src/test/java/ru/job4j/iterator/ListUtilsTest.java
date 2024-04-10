package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void  whenRemoveIfElementMoreTwo() {
        ListUtils.removeIf(input, (e) -> e > 2);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void  whenNotRemoveIfElementMoreThree() {
        ListUtils.removeIf(input, (e) -> e > 3);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfElementEqualsOne() {
        ListUtils.replaceIf(input, (e) -> e == 1, 100);
        assertThat(input).hasSize(2).containsSequence(100, 3);
    }

    @Test
    void whenNotReplaceIfElementEqualsFive() {
        ListUtils.replaceIf(input, (e) -> e == 5, 100);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceAll() {
        input.addAll(Arrays.asList(2, 4, 5));
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).hasSize(3).containsSequence(2, 4, 5);
    }

}