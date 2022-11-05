package com.pulselive.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pulselive.demo.model.LeagueTableEntry;
import com.pulselive.demo.model.Match;

public class LeagueTableService {
	
	public List<LeagueTableEntry> getLeagueTableEntries(List<Match> matches){
		
		return null;
		
	}
	private List<LeagueTableEntry> caluclateLeagueTable(List<Match> matches){
		Map<String,LeagueTableEntry> table = new HashMap<>();
		matches.forEach(match-> {
			getTeamNames(table,match);
		});
		
		matches.forEach(match -> {
			caluclateNoOfMatchPlayed(table,match);
		});
		
		matches.forEach(match -> {
			caluclateGoalsOfTeam(table,match);
		});
		
		caluclateGoalDifference(table);
		matches.forEach(match -> {
			caluclateMatchResults(table,match);
		});
		
		return null;
		
	}
	private void caluclateMatchResults(Map<String, LeagueTableEntry> table, Match match) {
		// TODO Auto-generated method stub
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		
	}
	private void caluclateGoalDifference(Map<String, LeagueTableEntry> table) {
		// TODO Auto-generated method stub
		table.forEach((teamName,record) -> {
			record.setGoalDifference(record.getGoalsFor()-record.getGoalsAgainst());
		});
		
	}
	private void caluclateGoalsOfTeam(Map<String, LeagueTableEntry> table, Match match) {
		// TODO Auto-generated method stub
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		homeTeamEntry.setGoalsFor(homeTeamEntry.getGoalsFor() + match.getHomeScore());
		homeTeamEntry.setGoalsAgainst(homeTeamEntry.getGoalsAgainst() + match.getAwayScore());
		
		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		awayTeamEntry.setGoalsFor(awayTeamEntry.getGoalsFor() + match.getAwayScore());
		awayTeamEntry.setGoalsAgainst(awayTeamEntry.getGoalsAgainst() + match.getHomeScore());
	}
	private void caluclateNoOfMatchPlayed(Map<String, LeagueTableEntry> table, Match match) {
		// TODO Auto-generated method stub
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		homeTeamEntry.setPlayed(homeTeamEntry.getPlayed() + 1);
		
		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		awayTeamEntry.setPlayed(awayTeamEntry.getPlayed() + 1);
		
	}
	private void getTeamNames(Map<String, LeagueTableEntry> table, Match match) {
		// TODO Auto-generated method stub
		table.putIfAbsent(match.getHomeTeam(), new LeagueTableEntry(match.getHomeTeam(), 0, 0, 0, 0, 0, 0, 0, 0));
		table.putIfAbsent(match.getHomeTeam(), new LeagueTableEntry(match.getAwayTeam(), 0, 0, 0, 0, 0, 0, 0, 0));
		
	}
	

}