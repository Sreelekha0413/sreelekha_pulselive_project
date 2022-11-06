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
	
	public List<LeagueTableEntry> getLeagueTableEntries(List<Match> matches){
		List<LeagueTableEntry> leagueTable = caluclateLeagueTable(matches);
		return getSortedLeagueTable(leagueTable);
		
		
	}
	private List<LeagueTableEntry> getSortedLeagueTable(List<LeagueTableEntry> leagueTable) {
		// TODO Auto-generated method stub
		 Collections.sort(leagueTable,Comparator.comparing(LeagueTableEntry::getPoints,Comparator.reverseOrder())
				.thenComparing(LeagueTableEntry::getGoalDifference,Comparator.reverseOrder())
				.thenComparing(LeagueTableEntry::getGoalsFor,Comparator.reverseOrder())
				);
		 return leagueTable;
	}
	private List<LeagueTableEntry> caluclateLeagueTable(List<Match> matches){
		Map<String,LeagueTableEntry> table = new HashMap<>();
		matches.forEach(match-> {
			getTeamNames(table,match);
		});
		System.out.println("all teams" + table.values());
		
		System.out.println("----------------------------");
		
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
		
		caluclateGamePoints(table);
		List<LeagueTableEntry> leagueTable = new ArrayList<LeagueTableEntry>(table.values());
		return leagueTable;
		
	}
	private void caluclateGamePoints(Map<String, LeagueTableEntry> table) {
		// TODO Auto-generated method stub
		table.forEach((teamName,record) -> {
			record.setPoints((record.getWon() * 3) + (record.getDrawn()));
		});
	}
	private void caluclateMatchResults(Map<String, LeagueTableEntry> table, Match match) {
		// TODO Auto-generated method stub
		LeagueTableEntry homeTeamEntry = table.get(match.getHomeTeam());
		LeagueTableEntry awayTeamEntry = table.get(match.getAwayTeam());
		
		if(match.getHomeScore() > match.getAwayScore()) {
			homeTeamEntry.setWon(homeTeamEntry.getWon() + 1);
			awayTeamEntry.setLost(awayTeamEntry.getLost() + 1);
			
		}else if(match.getHomeScore() < match.getAwayScore()) {
			awayTeamEntry.setWon(awayTeamEntry.getWon() + 1);
			homeTeamEntry.setLost(homeTeamEntry.getLost() + 1);
			
		}else {
			homeTeamEntry.setDrawn(homeTeamEntry.getDrawn() + 1);
			awayTeamEntry.setDrawn(awayTeamEntry.getDrawn() + 1);
			
		}
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
		if(match.getHomeTeam().isEmpty() || match.getAwayTeam().isEmpty() || match.getHomeTeam().equalsIgnoreCase(match.getAwayTeam())) 
		{
			throw new RuntimeException("HomeTeam or AwayTeam cannot be null and cannot be same");
		}
		table.putIfAbsent(match.getHomeTeam(), new LeagueTableEntry(match.getHomeTeam(), 0, 0, 0, 0, 0, 0, 0, 0));
		table.putIfAbsent(match.getAwayTeam(), new LeagueTableEntry(match.getAwayTeam(), 0, 0, 0, 0, 0, 0, 0, 0));
		
	}
	

}
