

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LeagueTable {

	/*
	 * This HasMap stores all the opponent teams 
	 * for a particular team  (key)
	 * The values of the Opponent teams are
	 * stored as HashSet of String to avoid 
	 * duplicate. 
	 * 
	 * Deals with case that two teams can play max 2 games:
	 * 1 home and 1 away.
	 * NO team can play with itself
	 */
	static HashMap<String, HashSet<String>> fixtureMap = new HashMap<String, HashSet<String>>();
	
	/*
	 * Used to Store the stats
	 * for each team against 
	 * its teamName(key)
	 */
	static HashMap<String, Stats> pointsMap = new HashMap<String, Stats>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		/*
		 * Identifier to 
		 * see if there is
		 * an error in input
		 */
		boolean flag=true;
	
		for(int i=0;i<n;i++){
			String teamName = sc.next();
			//Initialization of both HashMaps
			fixtureMap.put(teamName, new HashSet<String>());
			pointsMap.put(teamName, new Stats());
			
			// Need to give TeamName to later generate list from the pointsMap HashMap
			pointsMap.get(teamName).setTeamName(teamName);
		}
		
		// Total number of games for which data is available
		int numOfGames = sc.nextInt();

		/*
		 * Loop to process all the game data
		 */
		for(int i=0;i<numOfGames;i++){
			
			String hTeam=sc.next();  // Home Team Name
			String aTeam=sc.next();  // Away Team Name
			
			int hTeamGoals = sc.nextInt();  // Fetching Goals from score Line
			int aTeamGoals = sc.nextInt();
			
			
			if(flag){ // flag tells if input is invalid. no more processing required if its true.
			
				String h1 = new String(hTeam); // Need to do this for comparing Strings. (IMPORTANt)h1.equals(a1)
				String a1 = new String(aTeam);
			
				if(fixtureMap.get(hTeam).contains(aTeam) || h1.equals(a1)){
					flag=false;
				}
				
				// Populating the fixureMap
				fixtureMap.get(hTeam).add(aTeam);
				
				// Update the pointsMap based on input data
				if(hTeamGoals>aTeamGoals){
					pointsMap.get(hTeam).setPoints(pointsMap.get(hTeam).getPoints()+2);
					pointsMap.get(hTeam).setGoalDiff(pointsMap.get(hTeam).getGoalDiff()+ (hTeamGoals-aTeamGoals) ); 
				}
				
				else if(hTeamGoals<aTeamGoals){
					pointsMap.get(aTeam).setPoints(pointsMap.get(aTeam).getPoints()+2);
					pointsMap.get(aTeam).setGoalDiff(pointsMap.get(aTeam).getGoalDiff()+ (hTeamGoals-aTeamGoals) ); 
				}
			
				else{
					pointsMap.get(hTeam).setPoints(pointsMap.get(hTeam).getPoints()+1);
					pointsMap.get(aTeam).setPoints(pointsMap.get(aTeam).getPoints()+1);
				}
			
				// Not needed for the present useCase but can be used later
				// Calculates GoalsAgainst
				pointsMap.get(hTeam).setGoalsAgainst(pointsMap.get(hTeam).getGoalsFor()+hTeamGoals);
				pointsMap.get(aTeam).setGoalsAgainst(pointsMap.get(aTeam).getGoalsFor()+aTeamGoals);
		
			}
		}
		
		if(!flag){
			System.out.println("Invalid Input");
		}
		
		else{
			List<Stats> pointTable = SortPointsMap(); // nethod returns a list
			Iterator<Stats> itr = pointTable.iterator();
			while(itr.hasNext()){
				System.out.println(itr.next().getTeamName());
			}
		}
		sc.close();
	}
	/*
	 * Code to sort the HashMap 
	 * by getting the Value Set 
	 * into a list and then
	 * coding up a Comparator for the 
	 * same. 
	 */
	private static List<Stats> SortPointsMap(){
		List<Stats> teams = new ArrayList<Stats>(pointsMap.values());
		Collections.sort(teams, new Comparator<Stats>() {

	        public int compare(Stats o1, Stats o2) {
	        	if(o1.getPoints() != o2.getPoints())
	        		return o2.getPoints() - o1.getPoints();
	        	else if(o2.getGoalDiff() != o1.getGoalDiff()){
	        		return o2.getGoalDiff() - o1.getGoalDiff();
	        	}
	        	else if(o1.getGoalsFor() != o2.getGoalsFor()){
	        		return o2.getGoalsFor() - o1.getGoalsFor();
	        	}
	        	
	        	else
	        		return o1.getTeamName().toLowerCase().compareTo(o2.getTeamName().toLowerCase());
	        }
	    });
		return teams;
	}
	
}

