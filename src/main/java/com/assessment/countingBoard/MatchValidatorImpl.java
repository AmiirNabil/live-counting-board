package com.assessment.countingBoard;

public class MatchValidatorImpl implements MatchValidator {

    @Override
    public void validateMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("Match not found.");
        }
        if (match.isFinished()) {
            throw new IllegalStateException("Cannot process operation for a finished match.");
        }
    }
}
