package io.github.ufuk.java15;

import io.github.ufuk.java15.examples.Animal;
import io.github.ufuk.java15.examples.Cat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Further readings:
 * - https://www.baeldung.com/java-15-new
 */
class Java15Tests {

    @Test
    void sealed_classes_can_be_inherited_by_permitted_classes() {
        Animal cat = new Cat();

        String permittedClasses = Arrays.stream(Animal.class.getPermittedSubclasses())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
        System.out.println("Only those classes [" + permittedClasses
                + "] are permitted to inherit from [Animal] interface");
    }

}
