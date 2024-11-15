package com.assessment.countingBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountingBoardTest {
    private CountingBoard countingBoard;

    @BeforeEach
    void setUp() {
        countingBoard = new CountingBoardDefault();
    }

    @Test
    void testStartMatch() {
        Match match = countingBoard.startMatch("ClassA", "ClassB");
        assertNotNull(match);
        assertEquals("ClassA 0 - 0 ClassB", match.toString());
    }

    @Test
    void testDuplicateMatchThrowsException() {
        countingBoard.startMatch("ClassA", "ClassB");
        assertThrows(IllegalArgumentException.class, () -> countingBoard.startMatch("ClassA", "ClassB"));
    }

    @Test
    void testUpdateMatchScore() {
        Match match = countingBoard.startMatch("ClassA", "ClassB");
        countingBoard.updateMatchScore("ClassA", "ClassB", 5, 3);
        assertEquals("ClassA 5 - 3 ClassB", match.toString());
    }

    @Test
    void testUpdateMatchThatDoesNotExist() {
        CountingBoardDefault countingBoard = new CountingBoardDefault();

        // Attempt to update a match that hasn't been started yet
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            countingBoard.updateMatchScore("ClassA", "ClassB", 1, 0);  // No such match exists
        });

        assertEquals("Match between ClassA and ClassB not found.", exception.getMessage());
    }

    @Test
    void testUpdateFinishedMatch() {
        CountingBoardDefault countingBoard = new CountingBoardDefault();

        // Start a match
        countingBoard.startMatch("ClassA", "ClassB");
        countingBoard.updateMatchScore("ClassA", "ClassB", 1, 0);  // Update the score

        // Finish the match
        countingBoard.finishMatch("ClassA", "ClassB");

        // Attempt to update the match after it has been finished
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            countingBoard.updateMatchScore("ClassA", "ClassB", 2, 1);  // Match has been finished, cannot update
        });

        assertEquals("Cannot update the score for a finished match.", exception.getMessage());
    }

    @Test
    void testFinishMatch() {
        Match match = countingBoard.startMatch("ClassA", "ClassB");
        countingBoard.finishMatch("ClassA", "ClassB");
        List<Match> summary = countingBoard.getMatchSummary();
        assertFalse(summary.contains(match));
    }

    @Test
    void testFinishNonExistentMatchThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> countingBoard.finishMatch("ClassA", "ClassB"));

    }

    @Test
    void testFinishAlreadyFinishedMatchThrowsException() {
        countingBoard.startMatch("ClassA", "ClassB");
        countingBoard.finishMatch("ClassA", "ClassB");

        assertThrows(IllegalStateException.class, () -> countingBoard.finishMatch("ClassA", "ClassB"));
    }

    @Test
    void testGetMatchSummary() {
        countingBoard.startMatch("ClassA", "ClassB");
        countingBoard.updateMatchScore("ClassA", "ClassB", 0, 5);

        countingBoard.startMatch("ClassC", "ClassD");
        countingBoard.updateMatchScore("ClassC", "ClassD", 10, 2);

        countingBoard.startMatch("ClassE", "ClassF");
        countingBoard.updateMatchScore("ClassE", "ClassF", 2, 2);

        countingBoard.startMatch("ClassG", "ClassH");
        countingBoard.updateMatchScore("ClassG", "ClassH", 6, 6);

        countingBoard.startMatch("ClassI", "ClassJ");
        countingBoard.updateMatchScore("ClassI", "ClassJ", 3, 1);

        List<Match> summary = countingBoard.getMatchSummary();

        assertEquals("ClassG 6 - 6 ClassH", summary.get(0).toString());
        assertEquals("ClassC 10 - 2 ClassD", summary.get(1).toString());
        assertEquals("ClassA 0 - 5 ClassB", summary.get(2).toString());
        assertEquals("ClassI 3 - 1 ClassJ", summary.get(3).toString());
        assertEquals("ClassE 2 - 2 ClassF", summary.get(4).toString());
    }

    @Test
    void testGetMatchSummaryEmpty() {
        List<Match> summary = countingBoard.getMatchSummary();

        assertEquals(summary.size(), 0);
    }
}
