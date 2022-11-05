package com.pulselive.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pulselive.demo.model.LeagueTableEntry;
import com.pulselive.demo.model.Match;

public class LeagueTableTest {
	@Test
	public void TestLeagueTable_Entries() {
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match("Manchester United","Liverpool F.C",5,2));
		matches.add(new Match("Liverpool F.C","Arsenal",2,4));
		matches.add(new Match("Arsenal","Manchester United",2,2));
		
		List<LeagueTableEntry> leagueTableentries = new LeagueTable(matches).getTableEntries();
		assertEquals("Manchester United", leagueTableentries.get(0).getTeamName());
		assertEquals(2, leagueTableentries.get(0).getPlayed());
		assertEquals(1, leagueTableentries.get(0).getWon());
		assertEquals(1, leagueTableentries.get(0).getDrawn());
		assertEquals(0, leagueTableentries.get(0).getLost());
		assertEquals(7, leagueTableentries.get(0).getGoalsFor());
		assertEquals(4, leagueTableentries.get(0).getGoalsAgainst());
		assertEquals(3, leagueTableentries.get(0).getGoalDifference());
		assertEquals(4, leagueTableentries.get(0).getPoints());
		
		assertEquals("Arsenal", leagueTableentries.get(1).getTeamName());
		assertEquals(2, leagueTableentries.get(1).getPlayed());
		assertEquals(1, leagueTableentries.get(1).getWon());
		assertEquals(1, leagueTableentries.get(1).getDrawn());
		assertEquals(0, leagueTableentries.get(1).getLost());
		assertEquals(6, leagueTableentries.get(1).getGoalsFor());
		assertEquals(4, leagueTableentries.get(1).getGoalsAgainst());
		assertEquals(2, leagueTableentries.get(1).getGoalDifference());
		assertEquals(4, leagueTableentries.get(1).getPoints());
		
		assertEquals("Liverpool F.C", leagueTableentries.get(2).getTeamName());
		assertEquals(2, leagueTableentries.get(2).getPlayed());
		assertEquals(0, leagueTableentries.get(2).getWon());
		assertEquals(0, leagueTableentries.get(2).getDrawn());
		assertEquals(2, leagueTableentries.get(2).getLost());
		assertEquals(4, leagueTableentries.get(2).getGoalsFor());
		assertEquals(9, leagueTableentries.get(2).getGoalsAgainst());
		assertEquals(-5, leagueTableentries.get(2).getGoalDifference());
		assertEquals(0, leagueTableentries.get(2).getPoints());
		
	}
	@Test
	public void TestLeagueTable_compareEntriesByPoints() {
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match("Tottenham Hotspur","Leeds United",2,3));
		matches.add(new Match("Everton F.C","Tottenham Hotspur",1,3));
		matches.add(new Match("Chelsea","Tottenham Hotspur",2,2));
		matches.add(new Match("Everton F.C","Leeds United",2,1));
		matches.add(new Match("Chelsea","Everton F.C",3,2));
		matches.add(new Match("Leeds United","Chelsea",4,2));
		
		List<LeagueTableEntry> leagueTableEntries = new LeagueTable(matches).getTableEntries();
		assertEquals("Leeds United", leagueTableEntries.get(0).getTeamName());
		assertEquals("Tottenham Hotspur", leagueTableEntries.get(1).getTeamName());
		assertEquals("Chelsea", leagueTableEntries.get(2).getTeamName());
		assertEquals("Everton F.C", leagueTableEntries.get(3).getTeamName());
		
	}
	

}
