package ru.crazymoonkin.otus.collection;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class DIYarrayListTest {
    @Test
    void shouldAddAllElementsFromOtherCollection() {
        DIYarrayList<Integer> testList = new DIYarrayList<>();
        Integer[] expectedData = IntStream.range(1, 100)
                .boxed()
                .toArray(Integer[]::new);

        Collections.addAll(testList, expectedData);
        assertThat(testList).containsExactly(expectedData);
    }

    @Test
    void shouldCorrectCopyAllElementsFromOtherCollection() {
        DIYarrayList<Integer> testList = spy(new DIYarrayList<>());
        assertThatThrownBy(() -> Collections.copy(testList, List.of(1))).isInstanceOf(IndexOutOfBoundsException.class);

        List<Integer> expectedData = IntStream.range(1, 100)
                .peek(i -> testList.add(0))
                .boxed()
                .collect(Collectors.toList());

        Collections.copy(testList, expectedData);
        assertThat(testList).containsExactlyElementsOf(expectedData);
        verify(testList, times(expectedData.size())).set(anyInt(), anyInt());
        verify(testList, times(1)).listIterator(); // Это д.б. в ДЗ
    }

    @SuppressWarnings("all")
    @Test
    void shouldCorrectSortElements() {
        DIYarrayList<Integer> testList = new DIYarrayList<>();
        List<Integer> expectedData = IntStream.range(1, 100)
                .peek(i -> testList.add(100 - i))
                .boxed()
                .collect(Collectors.toList());

        Collections.sort(testList, Comparator.naturalOrder());
        assertThat(testList).containsExactlyElementsOf(expectedData);
    }
}