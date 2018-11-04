
/**
 * Write a description of class Player here.
 * 
 * @Student Name: Zhang Tianyang 
 * @Student Id: 28484452
 * 
 */
public class Player
{
    /**
     * Fields
     */
    private String name;
    private int goals;
    
    /**
     * Default Constructor
     */
    public Player()
    {
        name = " ";
        goals = 0;
    }
    
    /**
     * Non-Default Constructor
     */
    public Player(String name)
    {
        this.name = name;
        goals = 0;
    }
    
    /**
     * Accessor method and Mutator method
     */
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getGoals()
    {
        return goals;
    }
    
    public void setGoals(int goals)
    {
        this.goals = this.goals + goals;
    }
    
    /**
     * Judge Player's name by the given rules
     * This method will have some test strategy
     */
    public static boolean isLegalInput(String playerName)
    {
        playerName = playerName.trim();     //delete header and tailer spaces
        int length = playerName.length();
        int hyphen = 0;
        if (playerName.length() < 2 || playerName.length() > 20) //make sure the length of name
        {
            System.out.println("The length of name is illegal! Please enter again~");
            return false;
        } 
        else if (playerName.charAt(0) == '-' || playerName.charAt(length - 1) == '-') 
        {
            System.out.println("The name can't have hyphen in the tailer and header!");
            return false;
        }
        else
        {
            for (int i = 0; i < length; i++)
            {
                if (playerName.charAt(i) == '-')
                {
                    hyphen++;
                    if (hyphen > 1)
                    {
                        System.out.println("The name should only contains one hyphen!");
                        return false;
                    }
                }
                else if (!Character.isLetter(playerName.charAt(i)))
                {
                    System.out.println("This is not a character!");
                    return false;
                }
            }
        }
        return true;
    }        
}
