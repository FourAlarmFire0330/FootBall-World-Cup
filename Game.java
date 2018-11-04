
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
public class Game
{
    private ArrayList<Team> teamList;
    private RandomGoalsGenerator randomNumber;
    private Scanner console;
    
    // Default Consturctor
    public Game()
    {
        teamList = new ArrayList<Team>();
        //goldenBootList = new ArrayList<Player>();
        randomNumber = new RandomGoalsGenerator();
        console = new Scanner(System.in);
    }
    
    public ArrayList<Team> getTeamList()
    {
        return teamList;
    }
    
    /**
     * Add players to team
     */
    public void addPlayerToTeam()
    {
       System.out.println("The four teams from the file are: ");
       for(Team team : teamList)
       {
           System.out.println("Team name: " + team.getName() + " \t" + "Team Ranking: " + team.getRanking());
       }
       System.out.println();
       for(Team team : teamList)
       {
           String playerName1 = "";
           String playerName2 = "";
           int i = 0;
           int j = 0;
           System.out.println("Please enter players for team: " + team.getName());
           do {
               System.out.println("Player1: ");
               if (i < 2)
               {
                   playerName1 = console.nextLine();
                   i++;
               }
               else
               {
                   playerName1 = "player-1-";
                   String teamname = team.getName();                   
                   playerName1 = playerName1 + teamname;
                   System.out.println("Default name has been given to player1!");
                   break;
               }
           } while (!Player.isLegalInput(playerName1));     //  Judge if the player's name meet the requirement
           team.addPlayer(playerName1);
           
           do {
               System.out.println("Player2: ");
               if (j < 2)
               {
                   playerName2 = console.nextLine();
                   j++;
               }
               else
               {
                   playerName2 = "player-2-";
                   String teamname = team.getName();                   
                   playerName2 = playerName2 + teamname;
                   System.out.println("Default name has been given to player2!");
                   break;
               }               
               if (playerName2.equals(playerName1))
               {
                   System.out.println("two Players can't have the same name!");
               }
           } while (playerName1.equals(playerName2) || !Player.isLegalInput(playerName2));
           team.addPlayer(playerName2);
           System.out.println("**************");
       }
       System.out.println();
    }
    
    /**
     * Option C: display Team's Information
     */
    public void displayTeams()
    {
        sortTeam();
        System.out.println(" \t\tPlayed\t  Won\t Lost\t Drawn\t Goals\t Points\t Fair Play Score");
        for (Team team : teamList)
        {
            int gamePlayed = team.getNumOfWon() + team.getNumOfLost() + team.getNumOfDrawn();
            System.out.println("\n" + team.getName() + "\n\t\t  " + gamePlayed + "\t   " + team.getNumOfWon() + "\t   "
                               + team.getNumOfLost() + "\t   " + team.getNumOfDrawn() + "\t   " + team.getNumOfTotalGoals() + "\t   "
                               + team.getPoints() +"\t\t " + team.getFairPlayScore());
        }
    }
    
    /**
     * Option D: display Player's Information
     */
    public void displayPlayers()
    {
        for (Team team : teamList)
        {
            team.disPlayersGoal();
            System.out.println("************");
        }
    }
    
    /**
     * Displays on the screen the result of the game at the end of each game
     */
    public void displayGameResult(Team teamHome, Team teamAway)
    {
        System.out.println("Game result: " + teamHome.getName() + " " + teamHome.getNumOfCurrentGoals()
                            + "  vs.  " + teamAway.getName() + " " + teamAway.getNumOfCurrentGoals());
        System.out.println("Cards awarded: ");
        if (teamHome.getYellowCards() != 0)
        {
            System.out.println(teamHome.getName() + " - " + teamHome.getYellowCards() + " yellow cards");
        }
        if (teamHome.getRedCards() != 0)
        {
            System.out.println(teamHome.getName() + " - " + teamHome.getRedCards() + " red cards");
        }
        if (teamAway.getYellowCards() != 0)
        {
            System.out.println(teamAway.getName() + " - " + teamAway.getYellowCards() + " yellow cards");
        }
        if (teamAway.getRedCards() != 0)
        {
            System.out.println(teamAway.getName() + " - " + teamAway.getRedCards() + " red cards");
        }
        System.out.println();
    }
    
    /**
     * Option E:
     * the name of the Football World Cup champion team, the Golden
     * Boot Award player, and the Fair Play Award team will be displayed to screen
     */
    public String[] displayWorldCupResult()
    {
        String[] a = new String[4];
        a[0] = "Footbal World Cup Winner: " + Team.getTeamWinner().getName();
        System.out.println("Footbal World Cup Winner: " + Team.getTeamWinner().getName());
        a[1] = getGoldenBootAward();
        a[2] = getFairPlayAward();
        System.out.println("");
        return a;
    }
    
    /**
     * Get each team's information from file team.txt
     */ 
    public void getTeamInfoFromFile()
    {
        String filename = ("teams.txt");    // Get the path of the file        
        int j = 0;        
        try
            {
                FileReader inputFile = new FileReader(filename);
                try
                {
                    Scanner parser = new Scanner(inputFile);
                    while (parser.hasNextLine())
                    {
                        String name = parser.nextLine();
                        String[] teamInfo = name.split(",");       //  Split the String with comma
                        Team team = new Team(teamInfo[0], Integer.parseInt(teamInfo[1]));   //  Call the Non-Default Constructor of Class
                                                                                            //  Team to pass the name and ranking
                        teamList.add(team);
                    }
                }
                finally
                {
                    inputFile.close();
                }
            }
        catch(FileNotFoundException exception)
            {
                System.out.println(filename + " not found");
            }
        catch(IOException exception)
            {
                System.out.println("Unexpected I/O exception occurs");
            } 
    }
    
    /**
     * Get Fair Play Award Winner
     */
    public String getFairPlayAward()
    {
        String a = "";
        String b = "";
        ArrayList<Team> newTeamList = teamList;        
        for (int i = 0; i < newTeamList.size() - 1; i++)
        {
            int min = i;
            for (int j = i + 1; j < newTeamList.size(); j++)
            {
                if (newTeamList.get(min).getFairPlayScore() > newTeamList.get(j).getFairPlayScore())
                {
                    min = j;
                }
            }
            if(min != i)
            {
                Collections.swap(newTeamList, i, min);
            }
        }
        System.out.print("Fair Play Award: " + teamList.get(0).getName());
        for (int j = 1; j <= 3; j++)
        {
            if (newTeamList.get(0).getFairPlayScore() == newTeamList.get(j).getFairPlayScore())
            {
                b += " and " + teamList.get(j).getName();
                System.out.print(" and " + teamList.get(j).getName());
            }
        }
        a = "Fair Play Award: " + teamList.get(0).getName() + b;
        return a;
    }
    
    /**
     * Get Golden Boot Award Player
     */
    public String getGoldenBootAward()
    {
        int maxGoal = 0;
        String a = "";
        for (Team team : teamList)
        {
            for (Player player : team.getPlayerList())
            {
                if ( maxGoal < player.getGoals() )
                {
                    maxGoal = player.getGoals();
                }
            }
        }
        
        for (Team team : teamList)
        {
            for (Player player : team.getPlayerList())
            {
                if ( maxGoal == player.getGoals() )
                {
                    a = "Golden Boot Award: " + player.getName() + " from " + team.getName();
                    System.out.println("Golden Boot Award: " + player.getName() + " from " + team.getName());
                }
            }
        }
        return a;
    }
   
    /**
     * Option A: simulate the playing of preliminary games
     */
    public void playPreStage()
    {
        for (Team team : teamList)
        {
            team.resetPreGame();
        }
            System.out.println("The result of Preliminary Stage are showed below:\n");
        for (Team teamHome : teamList)
        {
            for (Team teamAway : teamList)
            {
                if (teamHome != teamAway)   //  Remove the situation that a team versus itself, the total loop should be 3*4 = 12
                {
                    playGame(teamHome, teamAway);
                    displayGameResult(teamHome, teamAway);
                    updateTeamRecord(teamHome, teamAway);
                }
            }
        }
        sortTeam();
    }
    
    /**
     * Option B: simulate the playing of final game
     */
    public void playFinalStage()
    {
        Team teamTop = teamList.get(0);
        Team teamSecond = teamList.get(1);
        int goalDifferent = playGame(teamTop, teamSecond);
        System.out.println("The result of Final Stage are showed below:\n");
        displayGameResult(teamTop, teamSecond);
        if (goalDifferent == 0)     //  the final game ends in a draw
        {
            Team teamWinner = playPenaltyShootOut(teamTop, teamSecond);
            Team.setTeamWinner(teamWinner);
        }
        else if (goalDifferent > 0)
        {
            Team.setTeamWinner(teamTop);
        }
        else
        {
            Team.setTeamWinner(teamSecond);
        }
    }
    
    /**
     * simulates the playing of a penalty shoot-out, if required for a
     * Final that ends in a draw
     */
    public Team playPenaltyShootOut(Team teamTop, Team teamSecond)
    {
        int topTeamPShot = 0;
        int secondTeamPShot = 0;
        int totalT = 0;
        int totalS = 0;
        for (int i = 0; i <= 4; i++)
        {
            topTeamPShot = randomNumber.generateNumber(0,2);
            secondTeamPShot = randomNumber.generateNumber(0,2);
            totalT = totalT + topTeamPShot;
            totalS = totalS + secondTeamPShot;
        }
        if (totalT == totalS)
        {
            do
            {
                topTeamPShot = randomNumber.generateNumber(0,2);
                secondTeamPShot = randomNumber.generateNumber(0,2);                    
            }while (topTeamPShot == secondTeamPShot);
            System.out.println("The result of penalty shoot is: ");
            System.out.println(teamTop.getName() + " : " + totalT + "  vs  " + teamSecond.getName() + " : " + totalS);            
            if (topTeamPShot > secondTeamPShot)
            {
                return teamTop;
            }
            else
            {
                return teamSecond;
            }
        }
        else if (totalT > totalS)
        {   System.out.println("The result of penalty shoot is: ");
            System.out.println(teamTop.getName() + " : " + totalT + "  vs  " + teamSecond.getName() + " : " + totalS);
            return teamTop;
        }
        else
        {
            System.out.println("The result of penalty shoot is: ");
            System.out.println(teamTop.getName() + " : " + totalT + "  vs  " + teamSecond.getName() + " : " + totalS);
            return teamSecond;
        }        
    }
    
    /**
     * Simulate the playing of a game between two teams
     */
    public int playGame(Team teamHome, Team teamAway)
    {
        int homeGoal = 0;
        int awayGoal = 0;
        int rankDifference = teamHome.getRanking() - teamAway.getRanking();       
        if (rankDifference < 0)
        {
            homeGoal = randomNumber.generateNumber(0, 5+randomNumber.generateNumber(0,2));
            awayGoal = randomNumber.generateNumber(0, (5-rankDifference)+randomNumber.generateNumber(0,2));
        }
        else
        {
            homeGoal = randomNumber.generateNumber(0, (5-rankDifference)+randomNumber.generateNumber(0,2));
            awayGoal = randomNumber.generateNumber(0, 5+randomNumber.generateNumber(0,2));                        
        }
        teamHome.setNumOfCurrentGoals(homeGoal);
        teamAway.setNumOfCurrentGoals(awayGoal);
        teamHome.caculateFairPlayScore();
        teamAway.caculateFairPlayScore();
        
        return homeGoal - awayGoal;
    }
    
    /**
     * At the beginning of the game, show players' information of each team
     */
    public void showPlayerInfo()
    {
       for(Team team : teamList)
       {
           System.out.println("Team " + team.getName() + " has Players: ");
           team.showPlayer();
           System.out.println("**************");
       }        
    }
    
    /**
     * sort the team based on three criteria:
     * The teams will be listed from most to least points.
     * If teams have the same points, then the team with the higher number of goals scored will
     * be placed higher on the table.
     * If teams cannot be separated by any of the previous criteria, then the higher placing in the
     * table will be determined randomly.
     */
    public void sortTeam()     //   Selection Sort
    {
        for (int i = 0; i < teamList.size() - 1; i++)
        {
            int max = i;
            for (int j = i + 1; j < teamList.size(); j++)
            {
                if (teamList.get(max).getPoints() < teamList.get(j).getPoints())
                {
                    max = j;
                }
                if (teamList.get(max).getPoints() == teamList.get(j).getPoints())
                {
                    if (teamList.get(max).getNumOfTotalGoals() < teamList.get(j).getNumOfTotalGoals())
                    {
                        max = j;
                    }
                }
            }
            if(max != i)
            {
                Collections.swap(teamList, i, max);
            }
        }
    }
    
    /**
     * The team records (played, won, etc.) are updated after a game is completed.
     */
    public void updateTeamRecord(Team teamHome, Team teamAway)
    {
        teamHome.setNumOfTotalGoals(teamHome.getNumOfCurrentGoals());
        teamAway.setNumOfTotalGoals(teamAway.getNumOfCurrentGoals());
        if (teamHome.getNumOfCurrentGoals() > teamAway.getNumOfCurrentGoals())
        {
            teamHome.setNumOfWon(1);
            teamHome.setPoints(3);
            teamAway.setNumOfLost(1);
        }
        else if (teamHome.getNumOfCurrentGoals() == teamAway.getNumOfCurrentGoals())
        {
            teamHome.setNumOfDrawn(1);
            teamHome.setPoints(1);
            teamAway.setNumOfDrawn(1);
            teamAway.setPoints(1);
        }
        else
        {
            teamHome.setNumOfLost(1);
            teamAway.setNumOfWon(1);
            teamAway.setPoints(3);
        }
        teamHome.distributeGoals(teamHome.getNumOfCurrentGoals());     //  The goals will be randomly distributed between
        teamAway.distributeGoals(teamAway.getNumOfCurrentGoals());     //  the two players        
    }
   
    /**
     * Option X: Write the information to the file called 'statistics.txt'
     */
    public void writeLinesToFile(String filename, String[] content)
    {
        try // try to save to file
        {
            PrintWriter outputFile = new PrintWriter(filename);
            int i = 0;
            while (content[i] != null)
            {
                outputFile.println(content[i]);
                i++;
            }
            outputFile.close();
        }
        catch(IOException e) // save operation failed
        {
            System.out.println("something went wrong with accessing the file!");
        }
    }
    
    public void cal(int sum)
    {
        String star = "*";
        for (int i = 1; i < sum; i++)
        {
            if (i % 4 == 0)
                star += "*";
            System.out.println(star);
        }
    }    
}
