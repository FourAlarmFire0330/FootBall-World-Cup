
/**
 * Write a description of class Team here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Team
{
    private String name;
    private int ranking;
    private int yellowCards;
    private int redCards;
    private int fairPlayScore;
    private int numOfWon;
    private int numOfLost;
    private int numOfDrawn;
    private int points;
    private int numOfCurrentGoals;
    private int numOfTotalGoals;
    private static Team teamWin;
    
    private ArrayList<Player> playerList;
    /**
     * Default Constructor
     */  
    public Team()
    {
        
    }    
    /**
     * Non-Default Constructor
     */
    public Team(String name, int ranking)
    {
        playerList = new ArrayList<Player>();
        teamWin = new Team();
        yellowCards = 0;
        redCards = 0;
        fairPlayScore = 0;
        numOfWon = 0;
        numOfLost = 0;
        numOfDrawn = 0;
        numOfTotalGoals = 0;
        this.name = name;
        this.ranking = ranking;
    }
    /**
     * Accessor and Mutator Method for each attribute in the field
     */  
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    } 
    
    public int getRanking()
    {
        return ranking;
    }
    
    public void setYellowCards(int yellowCards)
    {
        this.yellowCards = yellowCards;
    }
    
    public int getYellowCards()
    {
        return yellowCards;
    }
    
    public void setRedCards(int redCards)
    {
        this.redCards = redCards;
    }
    
    public int getRedCards()
    {
        return redCards;
    }
    
    public void setFairPlayScore(int fairPlayScore)
    {
        this.fairPlayScore = this.fairPlayScore + fairPlayScore;
    }
    
    public int getFairPlayScore()
    {
        return fairPlayScore;
    }
    
    public void setNumOfWon(int numOfWon)
    {
        this.numOfWon = this.numOfWon + numOfWon;
    }
    
    public int getNumOfWon()
    {
        return numOfWon;
    }
    
    public void setNumOfLost(int numOfLost)
    {
        this.numOfLost = this.numOfLost + numOfLost;
    }
    
    public int getNumOfLost()
    {
        return numOfLost;
    }

    public void setNumOfDrawn(int numOfDrawn)
    {
        this.numOfDrawn = this.numOfDrawn + numOfDrawn;
    }
    
    public int getNumOfDrawn()
    {
        return numOfDrawn;
    }
    
    public void setPoints(int points)
    {
        this.points = this.points + points;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void setNumOfCurrentGoals(int numOfCurrentGoals)
    {
        this.numOfCurrentGoals = numOfCurrentGoals;
    }
    
    public int getNumOfCurrentGoals()
    {
        return numOfCurrentGoals;
    }
    
    public void setNumOfTotalGoals(int numOfTotalGoals)
    {
        this.numOfTotalGoals = this.numOfTotalGoals +numOfTotalGoals;
    }
    
    public int getNumOfTotalGoals()
    {
        return numOfTotalGoals;
    }
    
    public ArrayList<Player> getPlayerList()
    {
        return playerList;
    }
    
    public static void setTeamWinner(Team teamWinner)
    {
        teamWin = teamWinner;
    }
    
    public static Team getTeamWinner()
    {
        return teamWin;
    }

    /**
     * Add players in a team
     */ 
    public void addPlayer(String pName)
    {
        Player p = new Player(pName);
        playerList.add(p);
    }
    
    /**
     * Determine the yellow and red card a team get in a game
     */
    public void caculateFairPlayScore()
    {
        int yellowCard = 0;
        int redCard = 0;
        int fairPlayScore = 0;
        RandomGoalsGenerator randomGetCards = new RandomGoalsGenerator();
        for (int i = 0; i <= 3; i++)
        {
            int isGetCards = randomGetCards.generateNumber(1, 500);
            if (isGetCards <= 20)
            {
                int getCards = randomGetCards.generateNumber(1, 20);
                if (getCards >= 4 && getCards < 20)
                {
                    yellowCard++;
                }
                else if (getCards >= 1 && getCards <4)
                {
                    redCard++;
                }
            }
        }
        setYellowCards(yellowCard);
        setRedCards(redCard);
        fairPlayScore = 1*getYellowCards() + 2*getRedCards();
        setFairPlayScore(fairPlayScore);
    }
    
    /**
     * Display players' goals
     */
    public void disPlayersGoal()
    {
        for(Player player : playerList)
        {
            System.out.println(player.getName() + " (" + getName() + ") " + " - " + player.getGoals());
        }
    }
    
    /**
     * The goals will be randomly distriubuted between the two players
     */
    public void distributeGoals(int totalGoals)
    {
        int[] playerGoals = new int[2];
        int i = 0;
        RandomGoalsGenerator randomGoals = new RandomGoalsGenerator();
        playerGoals[0] = randomGoals.generateNumber(0, totalGoals);
        playerGoals[1] = totalGoals - playerGoals[0];
        for (Player player : playerList)
        {
            player.setGoals(playerGoals[i]);
            i++;
        }        
    }
    
    /**
     * Clear the information before each Pre stage
     */
    public void resetPreGame()
    {
        yellowCards = 0;
        redCards = 0;
        fairPlayScore = 0;
        numOfWon = 0;
        numOfLost = 0;
        numOfDrawn = 0;
        points = 0;
        numOfCurrentGoals = 0;
        numOfTotalGoals = 0;
        Team teamWin = new Team();
    }
    
    /**
     * Show players info in a team
     */  
    public void showPlayer()
    {
        for (Player player : playerList)
        {
            System.out.println(player.getName());
        }        
    }  
}
