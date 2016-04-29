
public class Die {
   
	//Variables
    private int MAX = 6; // 6 sides
    private int dieValue;
    
    public Die(){
        dieValue = 0;
    }
    
    public Die(int numOfSides){
        dieValue = 0;
        MAX = numOfSides;
    }
    
    //Roll & set value
    public int roll(){
        dieValue = (int)(Math.random() * MAX) + 1; //Random number 1-6
        return dieValue;
    }
    
    public int getdieValue(){
        return dieValue;
    }
    
    public int getMAX(){
        return MAX;
    }

}

