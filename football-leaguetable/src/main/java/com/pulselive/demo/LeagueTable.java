package com.pulselive.demo;

import java.util.List;

import com.pulselive.demo.model.LeagueTableEntry;
import com.pulselive.demo.model.Match;
import com.pulselive.demo.service.LeagueTableService;

/**
 * The League class will accept the matches and will populate sorted leagueTable
 */
public class LeagueTable {

	private final List<Match> matches;

	/* Initialize league table with matches */

	public LeagueTable(final List<Match> matches) {
		this.matches = matches;

	}

	/**
	 * Method to get the ordered list of LeagueTable entries
	 * 
	 * @return the leagueTable entries
	 * 
	 */

	public List<LeagueTableEntry> getTableEntries() {
		LeagueTableService leagueTableService = new LeagueTableService();
		List<LeagueTableEntry> leagueTable = leagueTableService.getLeagueTableEntries(getMatches());
		return leagueTable;

	}

	public List<Match> getMatches() {
		return this.matches;
	}
}