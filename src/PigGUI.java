import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class PigGUI extends JFrame implements ActionListener, FocusListener
{
JPanel mainPanel;
JPanel optionPanel;
JPanel dicePanel;
private JPanel dicePanel_1;
JButton rollDice;
JButton hold;
JButton startGame;
JButton clear;
JMenu options;
JMenuItem quit;
JMenuItem seeStats;
JMenuItem howtoPlay;
JMenuBar menuBar;
JLabel diceLabel2;
JLabel diceLabel1;
Graphics die1;
Graphics die2;
private boolean rolling;
private JTextArea textArea;
private JLabel lblPlayerSettings;
private JLabel lblPlayer;
private JLabel lblComputerPlayer;
JLabel lblPlayerScore;
JLabel lblComputerScore;
private JLabel lblNewLabel_1;
private JLabel lblPlayerName;
private JTextField txtEnterNumber;
private JLabel lblDiceSides;
private JTextField txtEnterYourName;
private JTextField textField;
String EnterName = "Player 1";
private JMenu diceSides;
private JMenuItem Six;
private JMenuItem twelve;
int diceSideNumber = 6;
private JMenuItem twenty;
private JMenuBar diceMenu;
private JMenu difficulties;
private JMenuBar difficultyMenu;
private JMenuItem Easy;
private JMenuItem Medium;
private JMenuItem Expert;
int difficultyLevel = 1;


//pig game info
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

private String userName= EnterName;
playerName yourName = new playerName(null);

private int userDifficulty = 0;
difficulty difficulty = new difficulty(0);
private JScrollPane scrollPane;

public PigGUI()
{
    setTitle("Play Pig!");
    this.setPreferredSize(new Dimension(1200,600));

    mainPanel = new JPanel();
    mainPanel.setBounds(0, 0, 1200, 578);
    optionPanel = new JPanel();
    optionPanel.setBackground(Color.WHITE);
    optionPanel.setBounds(386, 471, 446, 37);
    dicePanel = new JPanel();
    options = new JMenu("Options");
    quit = new JMenuItem("Quit");
    seeStats = new JMenuItem("See Stats");
    howtoPlay = new JMenuItem("How to Play");
    menuBar = new JMenuBar();
    dice = new Dice();
    startGame = new JButton();

    Six = new JMenuItem("D-6");
    twelve = new JMenuItem("D-12");
    twenty = new JMenuItem("D-20");
    
    diceSides = new JMenu("Dice Sides");
    diceMenu = new JMenuBar();
    diceSides.add(Six);
    diceSides.add(twelve);
    diceSides.add(twenty);
    diceMenu.add(diceSides);
    
    
    Easy = new JMenuItem("Easy");
    Medium = new JMenuItem("Medium");
    Expert = new JMenuItem("EXPERT MODE!");
    
    difficulties = new JMenu("Difficulty Levels");
    difficultyMenu = new JMenuBar();
    difficulties.add(Easy);
    difficulties.add(Medium);
    difficulties.add(Expert);
    difficultyMenu.add(difficulties);

    options.add(quit);
    options.add(seeStats);
    options.add(howtoPlay);

    menuBar.add(options);

    optionPanel.add(menuBar);
    optionPanel.setPreferredSize(new Dimension(600,100));
    
    dicePanel_1 = new JPanel(new GridLayout(2,1));
    dicePanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
    dicePanel_1.setBackground(new Color(255, 255, 204));
    dicePanel_1.setBounds(530, 251, 200, 132);

    mainPanel.setPreferredSize(new Dimension(600,600));
    mainPanel.setLayout(null);
    
    //Console area with scrolling pane
    textArea = new JTextArea(); 
    scrollPane = new JScrollPane(textArea);
    scrollPane.setBounds(184, 251, 319, 173);
    //Auto scrolls console to the last line
    DefaultCaret caret = (DefaultCaret)textArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    mainPanel.add(scrollPane);
    //CONSOLE TEXT!
    textArea.setBackground(Color.WHITE);
    textArea.setBounds(184, 251, 319, 173);
    textArea.setText("Welcome to PIG!"
    		+ "\nEnter Player Settings before starting the game."
    		+ "\nSee options > How to Play"
    		+ "\n");
    
    //Console Label
    lblPlayerSettings = new JLabel("Console:");
    lblPlayerSettings.setBounds(184, 230, 141, 16);
    mainPanel.add(lblPlayerSettings);
   
    //Player 1 Label
    lblPlayer = new JLabel("PLAYER 1:");
    lblPlayer.setBounds(184, 184, 85, 16);
    mainPanel.add(lblPlayer);
    
    //Player 1 Score Label
    lblPlayerScore = new JLabel("PLAYER 1 SCORE", JLabel.CENTER);
    lblPlayerScore.setBounds(255, 184, 141, 16);
    mainPanel.add(lblPlayerScore);
    
    //Computer Label
    lblComputerPlayer = new JLabel("COMPUTER PLAYER:");
    lblComputerPlayer.setBounds(820, 184, 141, 16);
    mainPanel.add(lblComputerPlayer);
    
    //Computer Score Label
    lblComputerScore = new JLabel("COMPUTER SCORE",JLabel.CENTER);
    lblComputerScore.setBounds(957, 184, 122, 16);
    mainPanel.add(lblComputerScore);
    
    //Player Settings Label
    lblNewLabel_1 = new JLabel("Player Settings:");
    lblNewLabel_1.setBounds(771, 230, 122, 16);
    mainPanel.add(lblNewLabel_1);
    
    //Enter player name label
    lblPlayerName = new JLabel("Player Name:");
    lblPlayerName.setBounds(781, 258, 85, 16);
    mainPanel.add(lblPlayerName);
    
    //Player name field
    txtEnterYourName = new JTextField();
    txtEnterYourName.setText(EnterName);
    txtEnterYourName.setColumns(10);
    txtEnterYourName.setBounds(903, 246, 130, 26);
    mainPanel.add(txtEnterYourName);
    txtEnterYourName.addFocusListener(this);
    
    //Set dice sides Label
    lblDiceSides = new JLabel("Set dice sides:");
    lblDiceSides.setBounds(781, 294, 122, 16);
    mainPanel.add(lblDiceSides);
    
    //Dice menu button
    //txtEnterNumber = new JTextField();
    //txtEnterNumber.setText("enter number 1-20");
    diceMenu.setBounds(903, 289, 130, 26);
    mainPanel.add(diceMenu);
    //diceMenu.addFocusListener(this);
    
    //Set difficulty Label
    JLabel label_1 = new JLabel("Set difficulty:");
    label_1.setBounds(781, 338, 122, 16);
    mainPanel.add(label_1);
    
    //Difficulty Menu button
    difficultyMenu.setBounds(903, 333, 130, 26);
    mainPanel.add(difficultyMenu);
    
    //Difficulty options label
   // JLabel lblNewLabel_2 = new JLabel("*Easy = 1, Medium = 2, Hard = 3*");
    //lblNewLabel_2.setBounds(781, 366, 252, 16);
    //mainPanel.add(lblNewLabel_2);
    
    //Start Game Button
    startGame = new JButton("Start the Game!");
    startGame.setBounds(840, 395, 149, 29);
    
    //Clear Console Button
    clear = new JButton("Clear");
    clear.setBounds(279, 421, 117, 29);
    
    mainPanel.add(startGame);
    mainPanel.add(optionPanel);
    mainPanel.add(dicePanel_1);
	mainPanel.add(clear);
    diceLabel1 = new JLabel("",JLabel.CENTER);
    dicePanel_1.add(diceLabel1);
    diceLabel2 = new JLabel("",JLabel.CENTER);
    dicePanel_1.add(diceLabel2);

    quit.addActionListener(this);
    seeStats.addActionListener(this);
    howtoPlay.addActionListener(this);
    getContentPane().setLayout(null);
    Six.addActionListener(this);
    twelve.addActionListener(this);
    twenty.addActionListener(this);
    Easy.addActionListener(this);
    Medium.addActionListener(this);
    Expert.addActionListener(this);
    startGame.addActionListener(this);
    clear.addActionListener(this);
    
    txtEnterYourName.addActionListener(this);
    
    //CURRENTLY HIDING ROLL/HOLD BUTTONS
    //rollDice = new JButton("Roll Dice");
    //rollDice.setBounds(528, 395, 100, 29);
    //mainPanel.add(rollDice);
    //hold = new JButton("Hold");
    //hold.setBounds(630, 395, 100, 29);
    //mainPanel.add(hold);
      
    //Background image
    this.getContentPane().add(mainPanel);
    Image img = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(img));
    lblNewLabel.setBounds(6, 6, 1188, 567);
    mainPanel.add(lblNewLabel);
    
    //HIDING ROLL/HOLD BUTTONS
    //rollDice.addActionListener(this);

    this.pack();
    //Closes program on exit
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}

public void actionPerformed(ActionEvent e) {
	//Starts a new game
    if (e.getSource() == startGame)
    {
    	gamePig();
      
    }
    //Clears console
    if (e.getSource() == clear)
    {
    	textArea.setText("");
      
    }
    //Option > Quit
	if (e.getSource()== quit)
	{
        System.exit(0);
	}
	//Option > See Game Stats
    if (e.getSource() == seeStats)
    {
        JOptionPane.showMessageDialog(mainPanel,
                "Your stats are:\n"
                + "Wins: " + win
                + "\nLosses: " + loss
                + "\nTimes played: "+ gameCount); 
      
    }
    //Message dialog under options how to play & set up
    if (e.getSource() == howtoPlay)
    {
        JOptionPane.showMessageDialog(mainPanel,
                "=============================HOW TO SET UP=============================\n"
                + "Begin by entering player settings.\n"
                + "Ener your first name and how many sides you'd like on each die ex: 1-20.\n"
                + "Then select a difficulty level by entering the corresponding value.\n"
                + "EASY = 1, MEDIUM = 2, and HARD = 3\n"
                + "Finish by clicking 'Start Game'\n"
                + "=============================HOW TO PLAY=============================\n"
                + "The object of the game is to be the first player to reach 100 points.\n"
                + "You may choose to keep rolling the die or hold while it is your turn.\n"
                + "Your turn ends once you decide to hold or if you roll a 1, or snake eyes.\n"
                + "The game will end once you or the computer reaches 100 or more points.\n"); 
    }

    if (e.getSource() == rollDice)
    {
    	rolling = true;
    }
    
    if (e.getSource() == Six) {
    	diceSideNumber = 6;
    	diceSides.setText("D-6 Selected.");
    }
    
    if (e.getSource() == twelve) {
    	diceSideNumber = 12;
    	diceSides.setText("D-12 Selected.");
    }
    
    if (e.getSource() == twenty) {
    	diceSideNumber = 20;
    	diceSides.setText("D-20 Selected.");
    }
    
    if (e.getSource() == Easy){
    	
    	difficultyLevel = 1;
    	difficulty.setlevel(1);
    	difficulties.setText("Easy Level.");
    }
    
    if (e.getSource() == Medium){
    	
    	difficultyLevel = 2;
    	difficulty.setlevel(2);
    	difficulties.setText("Medium Level.");
    }
    
    if (e.getSource() == Expert){
    	
    	difficultyLevel = 3;
    	difficulty.setlevel(3);
    	difficulties.setText("EXPERT MODE!");
    }
    
    if (e.getSource().equals(txtEnterYourName)) //working on getting name entry to work.
    {
    	EnterName = textField.getText();
    }	
}

public void gamePig(){
	//Variables
	userScore = 0; 
	computerScore = 0;
	checkTurn = false;
	dice = new Dice();
	int firstRoll = 0;
	

	 dice = new Dice(diceSideNumber);
	
	//Starting game message
	 textArea.append("\n");
	 textArea.append(userName + " has started a game of Pig. \n");
	
	//Determine who goes first
	 textArea.append("Coin toss to see who goes first...\n");
	firstRoll = (int)(Math.random() * 2) + 1; 
	if(firstRoll==1){
		checkTurn=false; //Set user as first turn
		textArea.append(userName + " goes first!\n");

		JOptionPane.showMessageDialog(null, "Coin Toss! \n" + txtEnterYourName.getText() + " goes first!\n");
	}
		
	
	else{
		checkTurn=true; //Set computer as first turn
		textArea.append("Computer goes first!\n");	
		JOptionPane.showMessageDialog(null, "Coin Toss! \n Sorry, Computer goes first. :(\n");
	}

	start();
}
public void start(){
	//Variables
	int winner = 0;
	userScore = 0;
	int computerScore = 0;
	boolean rollAgain = false;
	int die1Score, die2Score, diceScore;
	String userTurn;
	int userTempScore = userScore;
	int computerTempScore = computerScore;
	lblPlayerScore.setText(String.valueOf(userTempScore));
	lblPlayerScore.repaint();
	
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
	textArea.append("\n");
	textArea.append("\nIts " + userTurn + "'s turn \n");
	

	//Display scores from turn
	textArea.append("Die One:"+die1Score+" || Die Two:"+die2Score +" || Total:" +diceScore + "\n");
	//textArea.append("Total Dice Score: " +diceScore + "\n");
	
	//Display die 1 in dice panel
	diceLabel1.setText("Die One:"+die1Score+" || Die Two:"+die2Score +" || Total:" +diceScore + "\n");
	diceLabel1.repaint();
	//Display corresponding die image
		if(die1Score==1){
		    Image imgdie1 = new ImageIcon(this.getClass().getResource("/die1.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel1.setIcon(new ImageIcon(imgdie1));
			diceLabel1.repaint();
			}
			else if(die1Score==2){
			    Image imgdie1 = new ImageIcon(this.getClass().getResource("/die2.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
				}
			else if(die1Score==3){
				 Image imgdie1 = new ImageIcon(this.getClass().getResource("/die3.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
				}
			else if(die1Score==4){
				 Image imgdie1 = new ImageIcon(this.getClass().getResource("/die4.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
				}
			else if(die1Score==5){
				 Image imgdie1 = new ImageIcon(this.getClass().getResource("/die5.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
				}
			else if(die1Score==6){
				 Image imgdie1 = new ImageIcon(this.getClass().getResource("/die6.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
				}
			else{
				 Image imgdie1 = new ImageIcon(this.getClass().getResource("/dieplus.gif")).getImage().getScaledInstance(50, 50, 50);
				diceLabel1.setIcon(new ImageIcon(imgdie1));
				diceLabel1.repaint();
			}
		
		//Display die 2 in dice panel
		diceLabel2.setText(String.valueOf(die2Score));
		diceLabel2.repaint();
		
		//Display corresponding die image
		if(die2Score==1){
	    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die1.gif")).getImage().getScaledInstance(50, 50, 50);
		diceLabel2.setIcon(new ImageIcon(imgdie2));
		diceLabel2.repaint();
		}
		else if(die2Score==2){
		    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die2.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
			}
		else if(die2Score==3){
		    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die3.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
			}
		else if(die2Score==4){
		    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die4.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
			}
		else if(die2Score==5){
		    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die5.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
			}
		else if(die2Score==6){
		    Image imgdie2 = new ImageIcon(this.getClass().getResource("/die6.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
			}
		else{
			Image imgdie2 = new ImageIcon(this.getClass().getResource("/dieplus.gif")).getImage().getScaledInstance(50, 50, 50);
			diceLabel2.setIcon(new ImageIcon(imgdie2));
			diceLabel2.repaint();
		}

	//Display corresponding die image
	
	//Check if a one was rolled
	if(dice.rollOne()==true){
		if( checkTurn == false ){
			checkTurn = !checkTurn; //End current turn
			userScore = userTempScore;
			lblPlayerScore.setText(String.valueOf(userTempScore));
			lblPlayerScore.repaint();
			//JOptionPane.showMessageDialog(null, "You rolled a 1. You forfeit this turn's points. \n"+ txtEnterYourName.getText() + " sadly hands over the dice.");
			if (JOptionPane.showConfirmDialog(null, "You rolled a 1. Keep playing (Yes) or RAGE QUIT(No)?",
			        userTurn, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, txtEnterYourName.getText() + " sadly hands over the dice.");
			} else {
			    break;
			}
		
		}
		else{
			
			checkTurn = !checkTurn; //End current turn
			JOptionPane.showMessageDialog(null, "Computer rolled a 1! \n It grumpily hands over the dice.");
			computerScore = computerTempScore;
			lblComputerScore.setText(String.valueOf(computerScore));
			lblComputerScore.repaint();
		}
		
		
		
		
	}
	//Check for snake eyes
	else if(dice.snakeEyes()==true){
		// Check whose turn & reset that score
		if( checkTurn == false ){
			userScore = 0;
			userTempScore = 0;
			lblPlayerScore.setText(String.valueOf(userTempScore));
			lblPlayerScore.repaint();
			JOptionPane.showMessageDialog(null, "SNAKE EYES - Your total score is now 0.\n "+ userTurn + " hands over dice.");
		}
		else{
			computerScore = 0;
			computerTempScore = 0;
			lblComputerScore.setText(String.valueOf(computerScore));
			lblComputerScore.repaint();
		}
		
		textArea.append("SNAKE EYES - Your total score is now 0.\n "+ userTurn + " hands over dice.");
		checkTurn = !checkTurn;  // //End current turn
		
	}
	else{//If no 1 or no snake eyes
		if(checkTurn == false ){ //user's turn

			userScore = userScore+diceScore;
			//Print results in JLabel
			lblPlayerScore.setText(String.valueOf(userScore));
			lblPlayerScore.repaint();
			//Print results in JLabel
			lblComputerScore.setText(String.valueOf(computerScore));
			lblComputerScore.repaint();
			
			//Check if winner
			if ( checkWin(userScore) == true ){
				winner = 1;
				win++; // Add to win count
				gameCount++; // Add to total game count
				
				//Display win and game stats
				textArea.append("\n~~*"+ txtEnterYourName.getText() +" wins!*~~\n");
				textArea.append("Click Start the Game! to play again.");
				JOptionPane.showMessageDialog(null, "YOU WIN!!!!! :D \n"+ " You can win again by clicking the 'Start the Game' button. \n Or see you stats by checking to the options menu!");
			}
			
		}
		else{ // Computer's turn
			//Calculate computer's score
			computerScore = computerScore+diceScore;

			lblComputerScore.setText(String.valueOf(computerScore));
			lblComputerScore.repaint();
			
			//Check if winner
			if ( checkWin(computerScore) == true ){
				winner = 1;
				loss++; //Add to loss count
				gameCount++;// Add to total game count
				
				//Display win and game stats
				textArea.append("\n~~*Computer wins!*~~\n");
				JOptionPane.showMessageDialog(null, "COMPUTER WINS :( \n You can try again by clicking the 'Start the Game' button.");
				textArea.append("Click Start the Game! to play again.");
	
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
				userTempScore = userScore;
				if(checkTurn == true){
					
				}
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
	textArea.append("\nThe computer has decided to hold.");
	JOptionPane.showMessageDialog(null, "The computer decides to hold, and smugly hands over the dice.");

	}	
}
else{
	//USER PROMPTS TO ROLL OR HOLD

	if (JOptionPane.showConfirmDialog(null, "Roll Again?\n You currently have " + userScore + " points. But you could lose it all If you keep going!", "Should you roll?!",
	        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	    rolling = true;
	} else {
	    rolling = false;
	    JOptionPane.showMessageDialog(null, "It's the computer's turn. It could win it all right now.\n Check out the dialog box to see how the computer rolled.");
	}
	

}

return rolling;
}


@Override
public void focusGained(FocusEvent e) {
	
	// TODO Auto-generated method stub
	if (e.getSource().equals(txtEnterYourName)){
		
		if(txtEnterYourName.getText().equals(EnterName)) {
			txtEnterYourName.setText("");
			
		}
		else{
			lblPlayer.setText(txtEnterYourName.getText());
		}
		
	}
}


@Override
public void focusLost(FocusEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource().equals(txtEnterYourName)){

		if(txtEnterYourName.getText().equals("")) {
			txtEnterYourName.setText(EnterName);
			txtEnterYourName.repaint();
			lblPlayerName.setText(EnterName);
			lblPlayerName.repaint();
			lblPlayer.setText(EnterName);
		}	
		else{
			lblPlayer.setText(txtEnterYourName.getText());
		}
	}

}
}


class playerName {
	
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

class difficulty {
	
	private int level;
	
	public difficulty(int level){
		if(level == 1) this.level = 1;
		else if (level == 2)this.level = 20;
		else this.level = 100;
	}

	public void setlevel(int level){
		if(level == 1) this.level = 1;
		else if (level == 2)this.level = 20;
		else this.level = 100;
	}
	
	public int getlevel(){
		return level;
	}
}
