package com.assessment.countingBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingBoardDefault implements CountingBoard {
    private final Map<Match, Match> matches;
    private final MatchValidator matchValidator;

    public CountingBoardDefault() {
        this.matches = new HashMap<>();
        this.matchValidator = new MatchValidatorImpl();
    }

    @Override
    public synchronized Match startMatch(String homeClass, String awayClass) {
        Match match = new GeneralMatch(homeClass, awayClass);

        if (matches.containsKey(match)) {
            throw new IllegalArgumentException("Match between " + homeClass + " and " + awayClass + " already exists.");
        }

        matches.put(match, match);
        return match;
    }

    @Override
    public void updateMatchScore(String homeClass, String awayClass, Integer homeScore, Integer awayScore) {
        // Create a Match object to identify the match by home and away classes
        Match matchKey = new GeneralMatch(homeClass, awayClass);
        Match match = matches.get(matchKey);

        matchValidator.validateMatch(match);

        // Update the score if the match is still active
        match.updateScore(homeScore, awayScore);
    }

    @Override
    public void finishMatch(String homeClass, String awayClass) {
        // Create a Match object to identify the match by home and away classes
        Match matchKey = new GeneralMatch(homeClass, awayClass);

        // Attempt to get the match from the map
        Match match = matches.get(matchKey);

        matchValidator.validateMatch(match);

        // Mark the match as finished
        match.finish();
    }

    @Override
    public List<Match> getMatchSummary() {
        return matches.values().stream()
                .sorted()
                .filter(match -> !match.isFinished())
                .collect(Collectors.toList());
    }
}
