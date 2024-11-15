# CountingBoard

**CountingBoard** is a simple Java-based library designed to track matches between teams (represented by "home" and "away" classes), update scores, and manage match states. The library includes functionality to **start matches**, **update match scores**, **finish matches**, and retrieve a **summary** of ongoing matches. The library also ensures that matches are only updated when they are still active.

## Features

- **Start a match**: Create a new match between two teams (home and away).
- **Update match score**: Update the score for a match while it is ongoing.
- **Finish a match**: Mark a match as finished. Finished matches cannot be updated.
- **Match summary**: Get a summary of all ongoing matches, sorted by score.

## Project Structure

This project uses the **SOLID** principles to ensure the code is maintainable, extendable, and easy to test. The project includes the following core components:

1. **CountingBoard**: Manages the matches, starting them, updating scores, finishing them, and generating the summary of ongoing matches.
2. **Match**: Represents a match between two teams and manages its state (e.g., scores, finished status).
3. **MatchValidator**: Validates the state of a match before any operation (e.g., updating score or finishing the match).
4. **Unit Tests**: Tests for the **CountingBoard** functionality, ensuring that all edge cases are handled correctly.

## Dependencies

The project uses **JUnit 5** for unit testing and **Java** 8 or higher for its runtime. Here are the primary dependencies:

- JUnit 5 for unit tests.
- Java 8 (or higher) for `Lambda expressions`, `Streams API` and `LocalDateTime`.

## Assumptions

- A match is between two teams, represented by their respective classes, with scores assigned to each team.
- Once a match is finished, it cannot be updated.
- Each match is identified uniquely by its **homeClass** and **awayClass**.
- The match summary only shows ongoing matches (i.e., matches that are not yet finished).

## Example Usage

### 1. **Start a Match**

```java
CountingBoard countingBoard = new CountingBoardDefault();

// Start a match between ClassA and ClassB
Match match = countingBoard.startMatch("ClassA", "ClassB");
System.out.println(match); // Output: ClassA 0 - 0 ClassB
```

### 2. **Update Match Score**

```java
// Update the score for the match between ClassA and ClassB
countingBoard.updateMatchScore("ClassA", "ClassB", 5, 3);
```
### 3. **Finish a Match**

```java
// Finish the match
countingBoard.finishMatch("ClassA", "ClassB");
```

### 4. **Get Match Summary**

```java
// Get the summary of ongoing matches
List<Match> matchSummary = countingBoard.getMatchSummary();
matchSummary.forEach(match -> System.out.println(match));

```

## Unit Tests

The project includes a suite of **unit tests** written with **JUnit 5** to validate the functionality of the **CountingBoard**. The tests cover the following scenarios:

- **Starting a match** and verifying that the match is created correctly.
- **Handling duplicate matches** by throwing an exception.
- **Updating match scores** and ensuring the match state is updated correctly.
- **Finishing a match** and ensuring that no further updates are allowed after the match is finished.
- **Retrieving the match summary** and verifying that ongoing matches are listed in the correct order.

### Running Tests

To run the tests, you can use your IDE's built-in support for running JUnit tests or execute the following Maven command:

```bash
mvn test
```

## Folder Structure
```php
src/
 └── com/
     └── assessment/
         └── countingBoard/
             ├── CountingBoard.java                    # Interface for CountingBoard
             ├── CountingBoardDefault.java             # Implementation of CountingBoard
             ├── Match.java                            # Match interface
             ├── MatchImpl.java                        # Concrete implementation of Match
             ├── MatchValidator.java                   # Validator for match operations
             └── MatchValidatorImpl.java               # Concrete implementation of MatchValidator
```


## Assumptions and Design Decisions
### 1. **Match Uniqueness**:
A match is uniquely identified by its `homeClass` and `awayClass`. This was chosen because it keeps the design simple and avoids unnecessary complexity.
### 2. **Finished Match Behavior**:
Once a match is marked as finished, it cannot be updated. This design choice ensures that match integrity is preserved and avoids accidental score changes after the match ends.
### 3. **Match Validation**:
The MatchValidator is used to ensure that operations on matches are performed only when valid (e.g., you can't update a finished match).
### 4. **Future Extensions**:
design supports future extensions where new types of matches or strategies for finishing matches can be added without modifying the core functionality.
### 5. **Simplified Match Model**:
The current match model assumes that matches are simple entities with just two teams (home and away), scores, and a finished status.
### 6. **Match Summary Sorting**:
The match summary is sorted by total score (in descending order) and includes only ongoing matches and that's depending on the entity class compareTo(Match o) method.