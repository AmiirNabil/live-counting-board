package com.assessment.countingBoard;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public final class GeneralMatch implements Serializable, Comparable<Match>, Match {
    private static long counter = 0;
    private static final long serialVersionUID = 1;
    private final String homeClass;
    private final String awayClass;
    private final LocalDateTime localDateTime;
    private final long sequence;
    private Integer homeScore;
    private Integer awayScore;
    private boolean finished;

    public GeneralMatch(String homeClass, String awayClass) {
        this.homeClass = homeClass;
        this.awayClass = awayClass;
        this.homeScore = 0;
        this.awayScore = 0;
        this.localDateTime = LocalDateTime.now();
        this.sequence = counter++;
        this.finished = false;
    }

    public void updateScore(Integer homeScore, Integer awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public Integer getTotalScore() {
        return homeScore + awayScore;
    }

    @Override
    public long getSequence() {
        return sequence;
    }

    public void finish() {
        this.finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
    public String getHomeClass() {
        return this.homeClass;
    }

    @Override
    public String getAwayClass() {
        return this.awayClass;
    }

    @Override
    public String toString() {
        return homeClass + " " + homeScore + " - " + awayScore + " " + awayClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralMatch match)) return false;
        return Objects.equals(homeClass, match.homeClass) &&
                Objects.equals(awayClass, match.awayClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeClass, awayClass);
    }

    @Override
    public int compareTo(Match other) {
        int scoreComparison = Integer.compare(other.getTotalScore(), this.getTotalScore());
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        return Long.compare(other.getSequence(), this.getSequence());
    }
}
