package com.assessment.countingBoard;

import java.util.List;

public interface CountingBoard {
    Match startMatch(String homeClass, String awayClass);
    void updateMatchScore(String homeClass, String awayClass, Integer homeScore, Integer awayScore);
    void finishMatch(String homeClass, String awayClass);
    List<Match> getMatchSummary();
}
