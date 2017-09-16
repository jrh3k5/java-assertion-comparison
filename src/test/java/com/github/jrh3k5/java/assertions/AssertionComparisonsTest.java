package com.github.jrh3k5.java.assertions;

/*-
 * #%L
 * Java Assertion Comparison
 * %%
 * Copyright (C) 2017 jrh3k5
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jrh3k5.java.assertions.assertions.CharacterSpecies;
import com.github.jrh3k5.java.assertions.assertions.TolkienCharacter;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.IterableAssert;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.github.jrh3k5.java.assertions.assertions.CharacterSpecies.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.jupiter.api.Assertions.*;

//import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test class demonstrating the various ways to do the same kinds of assertions between popular assertion libraries.
 *
 * @author Joshua Hyde
 */

class AssertionComparisonsTest {
    private static final TolkienCharacter FRODO = new TolkienCharacter("Frodo", HOBBIT);
    private static final TolkienCharacter SAM = new TolkienCharacter("Sam", HOBBIT);
    private static final TolkienCharacter MERRY = new TolkienCharacter("Merry", HOBBIT);
    private static final TolkienCharacter PIPPIN = new TolkienCharacter("Pippin", HOBBIT);
    private static final TolkienCharacter GANDALF = new TolkienCharacter("Gandalf", MAIA);
    private static final TolkienCharacter BOROMIR = new TolkienCharacter("Boromir", HUMAN);
    private static final TolkienCharacter ARAGORN = new TolkienCharacter("Aragorn", HUMAN);
    private static final TolkienCharacter SARUMAN = new TolkienCharacter("Saruman", MAIA);
    private static final TolkienCharacter GOLLUM = new TolkienCharacter("Gollum", HOBBIT);
    private static final TolkienCharacter THEODRED = new TolkienCharacter("Theodred", HUMAN);
    private final List<TolkienCharacter> fellowship = new ArrayList<>();

    /**
     * Sets up the Fellowship for each test
     */
    @BeforeEach
    void setUp() {
        fellowship.addAll(Arrays.asList(FRODO, SAM, MERRY, PIPPIN, GANDALF, BOROMIR, ARAGORN));
        // Uncomment this line to cause Gollum's presence to cause test failures
        //fellowship.add(GOLLUM);
        // Uncomment this line to kill Boromir
        //fellowship.remove(BOROMIR);
        // Uncomment this for an unexpected Theodred to join the Fellowship
        //fellowship.add(THEODRED);
    }

    /**
     * Exercise assertions using traditional JUnit assertions
     */
    @Nested
    @DisplayName("when using Hamcrest assertions")
    class HamcrestAssertions {
        @Test
        @DisplayName("has no servants of the Ring")
        void hasNoServantsOfTheRing() {
            assertThat(fellowship, not(hasItems(SARUMAN, GOLLUM)));
        }

        @Test
        @DisplayName("still has Boromir")
        void stillHasBoromir() {
            assertThat(fellowship, hasItem(BOROMIR));
        }

        @Test
        @DisplayName("has no one named Theodred")
        void hasNoOneNamedTheodred() {
            final List<String> names = fellowship.stream()
                    .map(TolkienCharacter::getName)
                    .collect(Collectors.toList());
            assertThat(names, not(hasItem(THEODRED.getName())));
        }

        @Test
        @DisplayName("executes the same assertions for multiple objects")
        void executesSameAssertions() {
            final Consumer<TolkienCharacter> assertion = (c) -> assertThat(c.getSpecies(), is(HOBBIT));

            assertion.accept(FRODO);
            assertion.accept(SAM);
            assertion.accept(MERRY);
            assertion.accept(PIPPIN);
            // Uncomment this to see the error message
            //assertion.accept(THEODRED);
        }

        @Test
        @DisplayName("test for size of potentially-null Collection")
        // Un-ignore to see the error message in action
        @Disabled
        void testsMaybeNullCollectionSize() {
            assertThat(couldReturnNull(), hasSize(1));
        }

        @Test
        @DisplayName("none of the Fellowship Hobbits should be Gollum")
        void noFellowshipHobbitsAreGollum() {
            final List<TolkienCharacter> hobbits = fellowship.stream()
                    .filter(c -> HOBBIT.equals(c.getSpecies()))
                    .collect(Collectors.toList());
            assertThat(hobbits, hasItems(FRODO, SAM, MERRY, PIPPIN));
            assertThat(hobbits, not(hasItems(GOLLUM)));
        }

        /**
         * A proxy method to allow the use of multiple classes defining an assertThat() method.
         * @param actual The object against which an assertion is to be ran.
         * @param matcher The {@link Matcher} to be ran against the given object.
         * @param <T> The type of object to be validated.
         */
        private <T> void assertThat(T actual, Matcher<? super T> matcher) {
            MatcherAssert.assertThat(actual, matcher);
        }
    }

    /**
     * Exercise assertions using traditional JUnit assertions
     */
    @Nested
    @DisplayName("when using JUnit assertions")
    class JUnitAssertions {
        @Test
        @DisplayName("has no servants of the Ring")
        void hasNoServantsOfTheRing() {
            assertFalse(fellowship.contains(SARUMAN));
            assertFalse(fellowship.contains(GOLLUM));
        }

        @Test
        @DisplayName("still has Boromir")
        void stillHasBoromir() {
            assertTrue(fellowship.contains(BOROMIR));
        }

        @Test
        @DisplayName("has no one named Theodred")
        void hasNoOneNamedTheodred() {
            final List<String> names = fellowship.stream()
                                                 .map(TolkienCharacter::getName)
                                                 .collect(Collectors.toList());
            assertFalse(names.contains(THEODRED.getName()));
        }

        @Test
        @DisplayName("executes the same assertions for multiple objects")
        void executesSameAssertions() {
            final Consumer<TolkienCharacter> assertion = (c) -> assertEquals(CharacterSpecies.HOBBIT, c.getSpecies());

            assertion.accept(FRODO);
            assertion.accept(SAM);
            assertion.accept(MERRY);
            assertion.accept(PIPPIN);
            // Uncomment this to see the error message
            //assertion.accept(THEODRED);
        }

        @Test
        @DisplayName("test for size of potentially-null Collection")
        // Un-ignore to see the error message in action
        @Disabled
        void testsMaybeNullCollectionSize() {
            assertEquals(1, couldReturnNull().size());
        }

        @Test
        @DisplayName("none of the Fellowship Hobbits should be Gollum")
        void noFellowshipHobbitsAreGollum() {
            final List<TolkienCharacter> hobbits = fellowship.stream()
                                                             .filter(c -> HOBBIT.equals(c.getSpecies()))
                                                             .collect(Collectors.toList());
            assertTrue(hobbits.contains(FRODO));
            assertTrue(hobbits.contains(SAM));
            assertTrue(hobbits.contains(MERRY));
            assertTrue(hobbits.contains(PIPPIN));
            assertFalse(hobbits.contains(GOLLUM));
        }
    }

    /**
     * Exercise assertions using traditional Assertj assertions
     */
    @Nested
    @DisplayName("when using assertj assertions")
    class AssertJAssertions {
        @Test
        @DisplayName("has no servants of the Ring")
        void hasNoServantsOfTheRing() {
            assertThat(fellowship).doesNotContain(SARUMAN, GOLLUM);
        }

        @Test
        @DisplayName("still has Boromir")
        void stillHasBoromir() {
            assertThat(fellowship).contains(BOROMIR);
        }

        @Test
        @DisplayName("has no one named Theodred")
        void hasNoOneNamedTheodred() {
            assertThat(fellowship).extracting("name")
                                  .doesNotContain(THEODRED.getName());
        }

        @Test
        @DisplayName("executes the same assertions for multiple objects")
        void executesSameAssertions() {
            final Consumer<TolkienCharacter> assertion = (c) -> assertThat(c.getSpecies()).isEqualTo(CharacterSpecies.HOBBIT);

            assertThat(FRODO).satisfies(assertion);
            assertThat(SAM).satisfies(assertion);
            assertThat(MERRY).satisfies(assertion);
            assertThat(PIPPIN).satisfies(assertion);
            // Uncomment this to see the error message
            //assertThat(THEODRED).satisfies(assertion);
        }

        @Test
        @DisplayName("test for size of potentially-null Collection")
        // Un-ignore to see the error message in action
        @Disabled
        void testsMaybeNullCollectionSize() {
            assertThat(couldReturnNull()).hasSize(1);
        }

        @Test
        @DisplayName("none of the Fellowship Hobbits should be Gollum")
        void noFellowshipHobbitsAreGollum() {
            assertThat(fellowship).filteredOn("species", HOBBIT)
                                  .hasSize(4)
                                  .contains(FRODO, SAM, MERRY, PIPPIN)
                                  .doesNotContain(GOLLUM);
        }

        /**
         * A proxy method to allow for multiple classes declaring an assertThat() method to be used in this test class.
         * @param collection The {@link Collection} against which assertions are to be ran.
         * @param <T> The type of objects in the collection.
         * @return An {@link IterableAssert} used to run assertions on the given collection.
         */
        private <T> IterableAssert<T> assertThat(Collection<T> collection) {
            return org.assertj.core.api.Assertions.assertThat(collection);
        }

        /**
         * A proxy method to allow for multiple classes declaring an assertThat() method to be used in this test class.
         * @param object The object against which the assertions are to occur.
         * @param <T> The type of object against which assertions are to be ran.
         * @return An {@link AbstractObjectAssert} used to run assertions against the given object.
         */
        private <T> AbstractObjectAssert<?, T> assertThat(T object) {
            return org.assertj.core.api.Assertions.assertThat(object);
        }
    }

    /**
     * A method used to simulate a case when testing a method that could return null.
     * @return A potentially {@code null} reference; otherwise, a {@link Collection}.
     */
    private static Collection<TolkienCharacter> couldReturnNull() {
        return null;
    }
}
