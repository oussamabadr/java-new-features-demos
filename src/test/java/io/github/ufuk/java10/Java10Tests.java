package io.github.ufuk.java10;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Further readings:
 * - https://www.baeldung.com/java-10-overview
 * - https://www.baeldung.com/java-10-local-variable-type-inference
 * - https://www.baeldung.com/java-10-performance-improvements
 */
class Java10Tests {

    @Test
    void local_variable_type_inference() throws FileNotFoundException {
        // before
        String oldLocalText = "Hello var";
        Map<String, String> anOldLocalMap = Map.of("key", "value");
        Set<String> anOldSet = Set.of("Item_1", "Item_2", "Item_3");
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("c:\\data\\output-file.txt"));

        // after
        var newLocalText = "Hello var";
        var aNewLocalMap = Map.of("key", "value");
        var aSet = Set.of("Item_1", "Item_2", "Item_3");
        var aOutputStream = new BufferedOutputStream(new FileOutputStream("c:\\data\\output-file.txt"));
    }

    @Test
    void create_immutable_lists_with_new_copy_of_utility() { // for sets and maps; use "Set.copyOf(...)" or "Map.copyOf(...)"
        List<String> aModifiableList = new ArrayList<>();
        aModifiableList.add("1");
        aModifiableList.add("2");
        aModifiableList.add("3");

        List<String> anImmutableList = List.copyOf(aModifiableList);

        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    anImmutableList.add("4"); // throws exception
                }
        );
    }

    @Test
    void create_immutable_lists_with_new_collector() { // for sets and maps; use "Collectors.toUnmodifiableSet()" or "Collectors.toUnmodifiableMap(...)"
        List<String> aModifiableList = new ArrayList<>();
        aModifiableList.add("1");
        aModifiableList.add("2");
        aModifiableList.add("3");

        List<String> anImmutableList = aModifiableList.stream()
                .filter(StringUtils::isNumeric)
                .collect(Collectors.toUnmodifiableList());

        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    anImmutableList.add("4"); // throws exception
                }
        );
    }

    @Test
    void optional_or_else_throw_no_such_element_exception_easily() {
        Optional<String> anOptionalString = Optional.of("text");

        assertThrows(
                NoSuchElementException.class,
                () -> {
                    Long convertedValue = anOptionalString.filter(StringUtils::isNumeric)
                            .map(Long::parseLong)
                            .orElseThrow(); // throws exception
                }
        );
    }

}
