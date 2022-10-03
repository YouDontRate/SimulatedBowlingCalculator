class Display
{
   private final int MAX_COLUMNS = 65;
   private final int PLAYER_ROWS = 3;
   private final int HEADER = 2;
   private final int FRAME_START = 4;
   private final int FRAME_GAP = 6;   
   private final char SPACE = ' ';
   private final char BAR = '|';
   private final char DASH = '-';  
   private final int ROWS;
   private char[][] scoreBoard;
   
//-----------------------------------------   
      
   public Display(int playerCount)
   {      
      ROWS = playerCount * PLAYER_ROWS + HEADER;      
      scoreBoard = new char[ROWS][MAX_COLUMNS];
      
      createScoreBoard(playerCount);
      
   }// End constructor

//--------------------------------------
   
   private void createScoreBoard(int playerCount)
   {
      int row = HEADER;
      int player = 1;
      
      clearBoard();  
      buildHeader();
      
      for (int i = 0; i < playerCount; i++)
      {
         addPlayer(row, player);
         row += PLAYER_ROWS;
         player++;         
      }// End for loop
      
   }// End method

//--------------------------------------

   private void clearBoard()
   {
   
      for (int i = 0; i < ROWS; i++)      
         for (int j = 0; j < MAX_COLUMNS; j++)         
            scoreBoard[i][j] = SPACE;      
      
   }// End method

//---------------------------------

   private void buildHeader()
   {   
      final char CROSS = '+';
      char frameNumber = 49;      // **49 is the ASCII value for '1'**
      
      scoreBoard[0][0] = 'N';
      scoreBoard[0][1] = 'A';
      scoreBoard[0][2] = 'M';
      scoreBoard[0][3] = 'E';           
      
      for (int i = FRAME_START; i < MAX_COLUMNS; i += FRAME_GAP)
         scoreBoard[0][i] = BAR;
      
      for (int i = 7; i < MAX_COLUMNS; i += FRAME_GAP) // This for loop starts at 7, that is the first column to add a frame number
      {        
         
         if (frameNumber == 58)      // **57 is the ASCII value for '9'** This is how i choose to mark the tenth frame**
         {
            scoreBoard[0][i-1] = '1';
            scoreBoard[0][i] = '0';
         }// End if
         
         else
         {
            scoreBoard[0][i] = frameNumber;
            frameNumber++;
         }// End else         
         
      }// End for loop
      
      
      for (int i = 0; i < MAX_COLUMNS; i++)
         scoreBoard[1][i] = DASH;
         
      
      for (int i = FRAME_START; i < MAX_COLUMNS; i += FRAME_GAP)
      {
         if (i == MAX_COLUMNS - 1)
            scoreBoard[1][i] = BAR;
            
         else
            scoreBoard[1][i] = CROSS;
            
      }// End for loop      
      
   }// End method

//-----------------------------------

   private void addPlayer(int pinFallRow, int playerNumber)
   {
      final char STAR = '*';
      final char HASH = '#';
      char firstNumber;
      char secondNumber;
      int scoreRow;
      int dividerRow;
      String number;      
      
      scoreRow = pinFallRow + 1;
      dividerRow = pinFallRow + HEADER;
      
      number = Integer.toString(playerNumber);
      
      if (number.length() > 1)
      {
         firstNumber = number.charAt(0);
         secondNumber = number.charAt(1);
         
         scoreBoard[scoreRow][2] = firstNumber;
         scoreBoard[scoreRow][3] = secondNumber;
         
      }// End if
      
      else
      {
         firstNumber = number.charAt(0);         
         scoreBoard[scoreRow][2] = firstNumber;
         
      }// End else
      
      scoreBoard[pinFallRow][0] = BAR;
      scoreBoard[pinFallRow][2] = STAR;
      
      scoreBoard[scoreRow][0] = BAR;
      scoreBoard[scoreRow][1] = HASH;       
      
      for (int i = FRAME_START; i < MAX_COLUMNS; i++)
      {
         if (i % 2 == 0)
            scoreBoard[pinFallRow][i] = BAR;      
         
      }// End for loop
      
      
      for (int i = FRAME_START; i < MAX_COLUMNS; i += FRAME_GAP)
         scoreBoard[scoreRow][i] = BAR;     
            
            
      for (int i = 0; i < MAX_COLUMNS; i++)
      {
         if (i == 0 || i == MAX_COLUMNS - 1)
            scoreBoard[dividerRow][i] = BAR;
            
         else
            scoreBoard[dividerRow][i] = DASH;
      }// End for loop     
   
   }// End method

//--------------------------------------     

   public void submitFrame(String pinFall, String score, int frame, int playerNumber)
   {
      int scoreRow;
      int pinFallRow;
      int pinFallColumn;
      int scoreColumn;
      final int[] frameColumn = {7, 13, 19, 25, 31, 37, 43, 49, 55, 61};     // *** each number represents the column index for each frame ***
      
      scoreRow = playerNumber * PLAYER_ROWS;
      pinFallRow = scoreRow - 1;     
      
      pinFallColumn = frameColumn[frame - 1]; 
      scoreColumn = pinFallColumn;  
      
      handleScore(score, scoreRow, scoreColumn);            
      handlePinFall(pinFall, pinFallRow, pinFallColumn);
      
   }// End method   

//--------------------------------------
   
   private void handleScore(String score, int scoreRow, int scoreColumn)
   {
      if (score.length() == 3)
      {
         scoreBoard[scoreRow][scoreColumn - 1] = score.charAt(0);
         scoreBoard[scoreRow][scoreColumn] = score.charAt(1);
         scoreBoard[scoreRow][scoreColumn + 1] = score.charAt(2);               
      }// End if
            
      else if (score.length() == 2)
      {               
         scoreBoard[scoreRow][scoreColumn] = score.charAt(0);
         scoreBoard[scoreRow][scoreColumn + 1] = score.charAt(1);               
      }// End else if
            
      else
         scoreBoard[scoreRow][scoreColumn] = score.charAt(0);
   
   }// end method
   
//----------------------------------------

   private void handlePinFall(String pinFall, int pinFallRow, int pinFallColumn)
   {
      
      if (pinFall.length() == 3)
      {
         scoreBoard[pinFallRow][pinFallColumn - 2] = pinFall.charAt(0);
         scoreBoard[pinFallRow][pinFallColumn] = pinFall.charAt(1);
         scoreBoard[pinFallRow][pinFallColumn + 2] = pinFall.charAt(2);
      }// End if
                     
      else if (pinFall.length() == 2)
      {
         scoreBoard[pinFallRow][pinFallColumn] = pinFall.charAt(0);
         scoreBoard[pinFallRow][pinFallColumn + 2] = pinFall.charAt(1);               
      }// End else if
            
      else
         scoreBoard[pinFallRow][pinFallColumn] = pinFall.charAt(0);
   
   }// end method

//---------------------------------

   public void displayScoreBoard()
   {      
      for (int i = 0; i < ROWS; i++)
      {
         for (int j = 0; j < MAX_COLUMNS; j++)
         {
            System.out.print(scoreBoard[i][j]);
         }// End j loop
         
         System.out.println();
      }// End i loop   
   
   }// End method
   
//-------------------------------------   
   
   
   
   //        ***     FRAME ONE      ***
   // scoreBoard[pinFallRow][7] = single roll score;
   // scoreBoard[pinFallRow][7] scoreBoard[firstRow][9] = double roll score;
   // scoreBoard[pinFallRow][59] scoreBoard[firstRow][61] scoreBoard[firstRow][63] = tenth frame;
   
   // scoreBoard[scoreRow][7] = score single digit
   // scoreBoard[scoreRow][7] scoreBoard[nextRow][8] = score double digit
   // scoreBoard[scoreRow][6] scoreBoard[nextRow][7] scoreBoard[nextRow][8] = score triple digit
   
   
   /*
   
      frame =         1,  2,  3,  4,  5,  6,  7,  8,  9, 10
      column index =  7, 13, 19, 25, 31, 37, 43, 49, 55, 61
      
      
      
      player = 1..n
      row index = playerN * 3
      
      eg.
      player = 5
      row index 5 * 3 = 15 
   
   */
  

//-------------TESTING ONLY-----------------

   public static void main(String[] args)
   {
      
      Display myDisplay = new Display(5);
      
      myDisplay.submitFrame("X", "30", 3, 3);
      myDisplay.submitFrame("8/", "113", 5, 4);
      myDisplay.submitFrame("XXX", "300", 10, 1);
      
      
      myDisplay.displayScoreBoard();
   
   }// End test main



}// end class
