
/**
 * Write a description of class RandomGoalsGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomGoalsGenerator
{
    int randomNumber;
    public RandomGoalsGenerator()
    {
        randomNumber = 0;
    }
    
    public int generateNumber(int Low, int High)
    {        
        randomNumber = Low + (int)(Math.random() * (High - Low));
        return randomNumber;
    } 
}
