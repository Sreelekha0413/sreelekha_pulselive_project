package com.pulselive.demo.model;

import java.util.Objects;

public class LeagueTableEntry {
    /**
     * name of this team
     */
    private String teamName;
    /**
     * total games played
     */
    private int played;
    /**
     * total games won
     */
    private int won;
    /**
     * total games drawn
     */
    private int drawn;
    /**
     * total games lost
     */
    private int lost;
    /**
     * total goals scored by this team
     */
    private int goalsFor;
    /**
     * total goals against this team
     */
    private int goalsAgainst;
    /**
     * Average absolute score difference.
     */
    private int goalDifference;
    /**
     * total points (3 for wins, and 1 for draws, 0 for loss)
     */
    private int points;

    public LeagueTableEntry(String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst, int goalDifference, int points) {
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getLost() {
        return lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueTableEntry that = (LeagueTableEntry) o;
        return played == that.played && won == that.won && drawn == that.drawn && lost == that.lost && goalsFor == that.goalsFor && goalsAgainst == that.goalsAgainst && goalDifference == that.goalDifference && points == that.points && Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, played, won, drawn, lost, goalsFor, goalsAgainst, goalDifference, points);
    }

	@Override
	public String toString() {
		return "LeagueTableEntry [teamName=" + teamName + ", played=" + played + ", won=" + won + ", drawn=" + drawn
				+ ", lost=" + lost + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", goalDifference="
				+ goalDifference + ", points=" + points + "]";
	}
    
}

