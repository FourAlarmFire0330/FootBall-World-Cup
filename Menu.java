
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Menu
{
    private Scanner console;
    private Game game;
    /**
     * Default Constructor
     */
    public Menu()
    {
        game = new Game();
        console = new Scanner(System.in);
    }

    public static void displayMenu()
    {
        System.out.println("**************************");
        System.out.println("A.  Play Preliminary Stage");
        System.out.println("B.  Play Final");
        System.out.println("C.  Display Teams");
        System.out.println("D.  Display Players");
        System.out.println("E.  Display Cup Result");
        System.out.println("X.  Close \n");
        System.out.println("Please enter a option, lowercase and uppercase are both acceptable~ \n");
    }   
    
    public void runningGame()
    {
        String[] exportFile = new String[4];
        //  This two variables guarantee that if another Preliminary has not been played, 
        //  the final can be played only one time
        int numOfPre = 0;
        int numOfFinal = 1;
        
        boolean isPrePlayed = false;
        boolean isFinalPlayed = false;
        
        game.getTeamInfoFromFile();
        game.addPlayerToTeam();
        game.showPlayerInfo();        
        
        String options;
        System.out.println("");
        System.out.println("The following displays the menu of the game, Enjoy your game!");        

        while (true)
        {
            displayMenu();
            options = console.nextLine();

            if (options.length() == 1)
            {
                switch (options)
                {
                    case "A": case "a":
                            game.playPreStage();
                            numOfPre++;
                            isPrePlayed = true;
                            break;
                    case "B": case "b":
                            if (isPrePlayed)
                            {
                                if ( numOfFinal == numOfPre )
                                {
                                    game.playFinalStage();
                                    numOfFinal++;
                                    isFinalPlayed = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("The final stage cannot be played twice!");
                                    break;
                                }
                            }
                            else
                            {
                                System.out.println("The Preliminary Stage has not been played!");
                                break;
                            }
                    case "C": case "c":
                            game.displayTeams();
                            break;
                    case "D": case "d":
                            game.displayPlayers();
                            break;
                    case "E": case "e":
                            if(isFinalPlayed)
                            {
                                exportFile = game.displayWorldCupResult();
                                break;
                            }
                            else
                            {
                                System.out.println("The Final Stage has not been played!");
                                break;
                            }
                    case "X": case "x":
                            System.out.println("System closed!");
                            game.writeLinesToFile("statistics.txt", exportFile);
                            System.exit(0);
                    default:
                            System.out.println("There is no such a option! Please enter again~ \n");
                            break;
                }
            }
            else
            {
                System.out.println("You should enter just one character! please enter again~ \n");
            }
        }
    }
}
