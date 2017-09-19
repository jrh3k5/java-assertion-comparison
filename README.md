# Java Assertion Comparison

A project demonstrating the differences between popular assertion libraries in Java

This compares four assertion libraries:

* JUnit Assert
* Hamcrest
* Google Truth
* assertj

## Usage Statistics

Acknowledging that GitHub is not the be-all, end-all exhaustive authority on what projects use these assertions, it is, nevertheless, sufficient for the needs of sampling popularity of these various assertion libraries.

### Maven Dependencies

The following statistics exist for declaration of dependencies on each assertion library:

* **JUnit Assert**: unfortunately, given the nature of how these assertions are embedded into the junit.jar file itself, it is not possible to distinguish between POMs that depend on JUnit for merely the ability to execute JUnit tests and those who depend on it for its assertion functionality.
* **Hamcrest**: using the _hamcrest-core_ library as the determination of yields, at time of writing this, [33,380](https://github.com/search?l=Maven+POM&q="hamcrest-core"+filename%3Apom.xml&type=Code&utf8=✓) results for POMs and [5,393](https://github.com/search?l=Gradle&q="hamcrest-core"+filename%3Abuild.gradle&type=Code&utf8=✓) for Gradle, for a total of 38,773. This may be a small estimation, however, as some projects, such as _mockito-all_ bundle Hamcrest classes in with their assemblies, so there may be false negatives in projects that use libraries like _mockito-all_ to bring in their own functionality as well as Hamcrest functionality.
* **Google Truth**: using the _com.google.truth_ group ID (since the artifact ID of "truth" is likely to result in a non-negligible amount of false positives) yields, at time of writing this, [2,535](https://github.com/search?l=Maven+POM&q="com.google.truth"+filename%3Apom.xml&type=Code&utf8=✓) results for POMs and [1,437](https://github.com/search?l=Gradle&q="com.google.truth"+filename%3Abuild.gradle&type=Code&utf8=✓) results for Gradle, for a total of 3,972.
* **assertj**: using the _assertj-core_ library as the determination of yields, at time of writing this, [30,277](https://github.com/search?l=Maven+POM&q="assertj-core"+filename%3Apom.xml&type=Code&utf8=✓) results for POMs and [9,090](https://github.com/search?l=Gradle&q="assertj-core"+filename%3Abuild.gradle&type=Code&utf8=✓) results for Gradle, for a total of 39,367.

### Java Code Dependencies

The following statistics exist for usage of these assertion libraries in Java code, searching by import statements.

* **JUnit Assert**: searching for imports of _org.junit.Assert_ yields [3,176,406](https://github.com/search?utf8=✓&q="org.junit.Assert"+language%3AJava&type=Code) results
* **Hamcrest**: searching for imports of _org.hamcrest.Matchers_ yields [396,818](https://github.com/search?utf8=✓&q="org.hamcrest.Matchers"+language%3AJava&type=Code) results
* **Google Truth**: searching for imports of _com.google.common.truth_ yields [57,340](https://github.com/search?utf8=✓&q="com.google.common.truth"+language%3AJava&type=Code) results
* **assertj**: searching for imports of _org.assertj.core.api_ yields [303,846](https://github.com/search?utf8=✓&q="org.assertj.core.api.Assertions"+language%3AJava&type=Code) results
