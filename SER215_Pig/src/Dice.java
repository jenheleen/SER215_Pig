
public class Dice {
    //Variables
    private final Die die1, die2;
    int value;
    
    //Create dice
    public Dice(){
        die1 = new Die();
        die2 = new Die();
    }
    
    public Dice(int numOfSides){
        die1 = new Die(numOfSides);
        die2 = new Die(numOfSides);
    }
    //Get value of each die
    public int getdieValue(int number){
        if(number == 1) //Return value for die 1
        	value = die1.getdieValue();
            
        if(number == 2)//Return value for die 2
            value = die2.getdieValue();
        
        return value;
    }
    
    //If a die is a one
    public boolean rollOne(){
    	if(die1.getdieValue()==1 ^ die2.getdieValue()==1){
    		return true;
    	}
    	return false;
    }
    
    //If both dies are a one
    public boolean snakeEyes(){
    	if(die1.getdieValue()==1 && die2.getdieValue()==1){
    		return true;
    	}
    	return false;
    }
    //Roll each die
    public void rollDice(){
        die1.roll();
        die2.roll();
    }
    
    //Total value of dice
    public int getPairValue(){
    	int pair = die1.getdieValue() + die2.getdieValue();
        return pair;
    }
    
    public int getDiceMaxValue(){
    	return die1.getMAX()+die2.getMAX();
    }
    
}


