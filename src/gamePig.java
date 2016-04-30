import java.util.InputMismatchException;
import java.util.Scanner;

public class gamePig {

	//Variables
	final int winPoints = 100;
	
	private int userScore;
	@SuppressWarnings("unused")
	private int computerScore;
	private int computerMaxTurn = 0;
	private int gameCount = 0;
	private int win=0;
	private int loss=0;
	
	private boolean checkTurn;
	private Dice dice;

	private String userName="";
	playerName yourName = new playerName(null);
	
	private int userDifficulty = 0;
	difficulty difficulty = new difficulty(0);
	
	Scanner scan = new Scanner(System.in);
	
	public gamePig(){
		//Variables
		userScore = 0; 
		computerScore = 0;
		checkTurn = false;
		dice = new Dice();	
		int firstRoll = 0;
		
		//Starting game prompts
		System.out.println("===============Pig Dice Game===============");
		//User input name
		System.out.println("Enter your name: ");
		userName = scan.next();
		yourName.setplayerName(userName);
		//User input game difficulty
		System.out.println("Enter number to set level: (easy=1, medium=2, hard=3) ");

		userDifficulty = scan.nextInt();
		difficulty.setlevel(userDifficulty);

		System.out.println("Enter how many sides for dice: (6, 12, or 20) ");
		 int numOfSides = scan.nextInt();
		 dice = new Dice(numOfSides);
		
		//Starting game message
		System.out.println("=========================================== \n");
		System.out.println(userName + " has started a game of Pig. \n");
		
		//Determine who goes first
		System.out.println("Coin toss to see who goes first...\n");
		firstRoll = (int)(Math.random() * 2) + 1; 
		if(firstRoll==1){
			checkTurn=false; //Set user as first turn
			System.out.println(userName + " goes first!\n");
		}
		else{
			checkTurn=true; //Set computer as first turn
			System.out.println("Computer goes first!\n");	
		}
	}
	
	//Player name
	public class playerName {
		
		private String name;
		
		public playerName(String name){
			this.name = name;
		}

		public void setplayerName(String name){
			this.name=name;
		}
		
		public String getName(){
			return name;
		}
	}
	
	//Player difficulty level
	
	///////////////////////////////////
	//****NEED USER INPUT FROM GUI  ///
	//////////////////////////////////
	public class difficulty {
		
		private int level;
		
		public difficulty(int level){
			if(level == 1) this.level = 1;
			else if (level == 2)this.level = 20;
			else this.level = 40;
		}

		public void setlevel(int level){
			if(level == 1) this.level = 1;
			else if (level == 2)this.level = 20;
			else this.level = 40;
		}
		
		public int getlevel(){
			return level;
		}
	}
	

	public void start(){
				//Variables
				int winner = 0;
				userScore = 0;
				int computerScore = 0;
				boolean rollAgain = false;
				int die1Score, die2Score, diceScore;
				String userTurn;
				String rolling;
				
				
				do{
				//roll dice
				dice.rollDice();
				//get each dice value & total score
				die1Score = dice.getdieValue(1);
				die2Score = dice.getdieValue(2);
				diceScore = dice.getPairValue();
				
				//Check whose turn it is
				if(checkTurn==false)
					userTurn = yourName.getName();
				else
					userTurn = "Computer";
				//Display whose turn it is
				System.out.println("================================================= \n");
				System.out.println("\nIts " + userTurn + "'s turn \n");

				//Display scores from turn
				System.out.println("Die One:"+die1Score+" || Die Two:"+die2Score +" || Total:" +diceScore + "\n");
				
				//Check if a one was rolled
				if(dice.rollOne()==true){
					System.out.println("You rolled a 1. You forfeit this turn's points. \n"+ userTurn + " hands over the dice.");
					checkTurn = !checkTurn; //End current turn
					
				}
				//Check for snake eyes
				else if(dice.snakeEyes()==true){
					// Check whose turn & reset that score
					if( checkTurn == false ){
						userScore = 0;
					}
					else{
						computerScore = 0;
					}
					System.out.println("SNAKE EYES - Your total score is now 0.\n "+ userTurn + " hands over dice.");
					checkTurn = !checkTurn;  // //End current turn
					
				}
				else{//If no 1 or no snake eyes
					if(checkTurn == false ){ //user's turn
						//Calculate user's score
						userScore = userScore+diceScore;
						//Print results
						System.out.println(yourName.getName()+"'s Score:"+userScore);
						System.out.println("Computer Score:"+computerScore);
						
						//Check if winner
						if ( checkWin(userScore) == true ){
							winner = 1;
							win++; // Add to win count
							gameCount++; // Add to total game count
							
							//Display win and game stats
							System.out.println("\n~~*"+ yourName.getName() +" wins!*~~\n");
							System.out.println("=================================");
							System.out.println("Total games played: " +gameCount);
							System.out.println("Times won: "+win+" loss: "+loss);
							System.out.println("=================================");
							//Prompt to play again
							System.out.println("[Play again? Y/N]");
							rolling = scan.next();
							if ( rolling.equals("Y") || rolling.endsWith("y") ){
								start(); //Start new round
								
							}
						}
						
					}
					else{ // Computer's turn
						//Calculate computer's score
						computerScore = computerScore+diceScore;
						//Print results
						System.out.println(yourName.getName()+"'s Score:"+userScore);
						System.out.println("Computer Score:"+computerScore);
						
						//Check if winner
						if ( checkWin(computerScore) == true ){
							winner = 1;
							loss++; //Add to loss count
							gameCount++;// Add to total game count
							
							//Display win and game stats
							System.out.println("\n~~*Computer wins!*~~\n");
							System.out.println("================GAME STATS=================");
							System.out.println("Total games played: " +gameCount);
							System.out.println("Times won: "+win+" loss: "+loss);
							System.out.println("===========================================");
							//Prompt to play again
							System.out.println("[Play again? Y/N]");
							rolling = scan.next();
							if ( rolling.equals("Y") || rolling.endsWith("y") ){
								start(); //Start new round
							}
						}
						
					}
					//No winners yet
					if ( winner == 0){
						//After end of user turn, prompt to roll again or hold
						rollAgain = prompt(checkTurn,diceScore);	
						if(rollAgain == true){
							//Continue rolling
						}
						else{
							// End turn. Hand dice over
							checkTurn = !checkTurn; 
							computerMaxTurn = 0; //Resets computer's max points per turn
						}	
					}						
				}		
			} while (winner == 0); // loop until a win is initiated				
	}
	
	//At the end of turns check score for a winner
	private boolean checkWin(int score){
		boolean win = false; //initially no winner
		
		if(score>=winPoints) //check if score >= 100
			win = true; // trigger win
		return win; // set win
	}
	
	//Determine when computer holds
	private boolean prompt(boolean computer, int currentScore){
		String userInput;
		boolean rolling = false;
		
		//When it's the computer's turn
		if(computer == true){
			// Max points per turn
			computerMaxTurn = computerMaxTurn+currentScore;
			//Keep rolling until max points (based on difficulty)
			if(computerMaxTurn < difficulty.getlevel()){
				rolling = true;
			}
			//Hold when max points are reached
			else{
				System.out.println("The computer has decided to hold.");
			}	
		}
		else{
		//When it's user's turn prompt to roll or hold
	
		userInput = scan.next();
		if ( userInput.equals("Y") || userInput.endsWith("y") ){
			rolling = true;
		}
		}
		return rolling;
	}
}
