package com.pulselive.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.pulselive.demo.model.LeagueTableEntry;
import com.pulselive.demo.model.Match;

public class LeagueTableService {

	/**
	 * Method to get the sorted list of League table
	 * 
	 * @param List<Match>
	 * @retun sorted list of leagueTable entries
	 */
	public List<LeagueTableEntry> getLeagueTableEntries(List<Match> matches) {
		List<LeagueTableEntry> leagueTable = caluclateLeagueTable(matches);
		return getSortedLeagueTable(leagueTable);

	}

	/**
	 * Method sort the League table entries by total points (descending) Then by
	 * goal difference (descending) Then by goals scored (descending) Then by team
	 * name (in alphabetical order)
	 * 
	 * @param leagueTable
	 * @retun sorted list of leagueTable
	 */
	private List<LeagueTableEntry> getSortedLeagueTable(List<LeagueTableEntry> leagueTable) {
		// TODO Auto-generated method stub
		Collections.sort(leagueTable,
				Comparator.comparing(LeagueTableEntry::getPoints, Comparator.reverseOrder())
						.thenComparing(LeagueTableEntry::getGoalDifference, Comparator.reverseOrder())
						.thenComparing(LeagueTableEntry::getGoalsFor, Comparator.reverseOrder()));
		return leagueTable;
	}

	/**
	 * Method to calculate League table based on list of matches performed and teams
	 * calculate number of matches played by each team calculate number of matches
	 * won by each team calculate number of matches lost by each team calculate
	 * number of matches draw calculate number of goals by each team calculate
	 * number of goals against by team calculate goals difference of a team
	 * calculate game points by each team
	 * 
	 * @param List<Match>
	 * @retun sorted list of leagueTable
	 */
	private List<LeagueTableEntry> caluclateLeagueTable(List<Match> matches) {
		Map<String, LeagueTableEntry> table = new HashMap<>();
		matches.forEach(match -> {
			getTeamNames(table, match);
		});
		
		matches.forEach(match -> {
			caluclateNoOfMatchPlayed(table, match);
		});

		matches.forEach(match -> {
			caluclateGoalsOfTeam(table, match);
		});

		caluclateGoalDifference(table);
		matches.forEach(match -> {
			caluclateMatchResults(table, match);
		});

		caluclateGamePoints(table);
		List<LeagueTableEntry> leagueTable = new ArrayList<LeagueTableEntry>(table.values());
		return leagueTable;

	}

	/**
	 * Method to calculate total game points for each team
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void caluclateGamePoints(Map<String, LeagueTableEntry> table) {
		table.forEach((teamName, record) -> {
			record.setPoints((record.getWon() * 3) + (record.getDrawn()));
		});
	}

	/**
	 * Method to calculate game results like won, lost, draw for each team
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void caluclateMatchResults(Map<String, LeagueTableEntry> table, Match match) {
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());

		if (match.getHomeScore() > match.getAwayScore()) {
			homeTeamEntry.setWon(homeTeamEntry.getWon() + 1);
			awayTeamEntry.setLost(awayTeamEntry.getLost() + 1);

		} else if (match.getHomeScore() < match.getAwayScore()) {
			awayTeamEntry.setWon(awayTeamEntry.getWon() + 1);
			homeTeamEntry.setLost(homeTeamEntry.getLost() + 1);

		} else {
			homeTeamEntry.setDrawn(homeTeamEntry.getDrawn() + 1);
			awayTeamEntry.setDrawn(awayTeamEntry.getDrawn() + 1);

		}

	}

	/**
	 * Method to calculate goal difference
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void caluclateGoalDifference(Map<String, LeagueTableEntry> table) {
		table.forEach((teamName, record) -> {
			record.setGoalDifference(record.getGoalsFor() - record.getGoalsAgainst());
		});

	}

	/**
	 * Method to calculate number goals and goals against each team
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void caluclateGoalsOfTeam(Map<String, LeagueTableEntry> table, Match match) {
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		homeTeamEntry.setGoalsFor(homeTeamEntry.getGoalsFor() + match.getHomeScore());
		homeTeamEntry.setGoalsAgainst(homeTeamEntry.getGoalsAgainst() + match.getAwayScore());

		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		awayTeamEntry.setGoalsFor(awayTeamEntry.getGoalsFor() + match.getAwayScore());
		awayTeamEntry.setGoalsAgainst(awayTeamEntry.getGoalsAgainst() + match.getHomeScore());
	}

	/**
	 * Method to calculate number of matches played by each team
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void caluclateNoOfMatchPlayed(Map<String, LeagueTableEntry> table, Match match) {
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		homeTeamEntry.setPlayed(homeTeamEntry.getPlayed() + 1);

		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		awayTeamEntry.setPlayed(awayTeamEntry.getPlayed() + 1);

	}

	/**
	 * Method to put team names and create basic LeagueTable entry for each team.
	 * 
	 * @param HashMap<String, LeagueTableEntry>
	 */
	private void getTeamNames(Map<String, LeagueTableEntry> table, Match match) {
		if (match.getHomeTeam().isEmpty() || match.getAwayTeam().isEmpty()
				|| match.getHomeTeam().equalsIgnoreCase(match.getAwayTeam())) {
			throw new RuntimeException("HomeTeam or AwayTeam cannot be null and cannot be same");
		}
		table.putIfAbsent(match.getHomeTeam(), new LeagueTableEntry(match.getHomeTeam(), 0, 0, 0, 0, 0, 0, 0, 0));
		table.putIfAbsent(match.getAwayTeam(), new LeagueTableEntry(match.getAwayTeam(), 0, 0, 0, 0, 0, 0, 0, 0));

	}

}
