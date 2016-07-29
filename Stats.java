/*
 * This is basically 
 * a class to hold the
 * values like points, goals
 * stats for generating
 * a league table.
 * Just basic getters, setters.
 * Nothing fancy
 */
class Stats{
	private String teamName;
	private int points;
	private int goalDiff;
	private int goalsFor;
	private int goalsAgainst;
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getGoalDiff() {
		return goalDiff;
	}
	public void setGoalDiff(int goalDiff) {
		this.goalDiff = goalDiff;
	}
	public int getGoalsFor() {
		return goalsFor;
	}
	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}

