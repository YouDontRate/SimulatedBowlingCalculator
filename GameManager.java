class GameManager
{
   private Bowler[] playerList;
   private Display myDisplay;

//-----------------------------

   public GameManager(int playerCount)
   {
      playerList = new Bowler[playerCount];
      myDisplay = new Display(playerCount);
      
      makeBowlers();
      
   }// End constructor   

//---------------------------------
   
   private void makeBowlers()
   {
      Bowler temp;
      
      for (int i = 0; i < playerList.length; i++)
      {
         temp = new Bowler(i+1);
         playerList[i] = temp;
      
      }// End loop
   
   }// End method
   
//--------------------------------

   public void startGame()
   {
      final int TENTH_FRAME = 10;
      int pinsDown;
      int pinsRemaining;
      boolean giveExtraThrow = false;      
      
      // This double for loop handles frames 1-9
      for (int frame = 1; frame < TENTH_FRAME; frame++)
      {
         for (int j = 0; j < playerList.length; j++)
         {            
            assert frame > 0: "Invalid frame number";
            pinsDown = playerList[j].handleFirstThrow(frame);
            assert pinsDown < 11: "Invalid pin count";
            
            if (pinsDown < 10)
               playerList[j].handleSecondThrow(pinsDown, frame);
         }// End j loop   
         
      }// End outer loop
      
      // This for loop handles the tenth frame
      for (int i = 0; i < playerList.length; i++)
      {
         giveExtraThrow = false;
         pinsDown = playerList[i].handleFirstThrow(TENTH_FRAME);       
         
         if (pinsDown == TENTH_FRAME)
         {
            pinsRemaining = playerList[i].handleExtraThrow(0, TENTH_FRAME);  
            giveExtraThrow = true;
         } // End if  
            
         else
         {
            pinsRemaining = playerList[i].handleSecondThrow(pinsDown, TENTH_FRAME);        
               
            if (pinsRemaining == 0)
               giveExtraThrow = true;
         }// End else           
            
         if (giveExtraThrow)
            playerList[i].handleExtraThrow(pinsRemaining, TENTH_FRAME);
      
      }// End for loop
      
      handleScore();
   
   }// End method

//-----------------------------------------

   private void handleScore()
   {
      final int MAX_FRAMES = 11;
      int bowlerNumber;
      String pinFall;
      String score;      
      Bowler temp;
   
      for (int i = 0; i < playerList.length; i++)
      {
      
         temp = playerList[i];
         temp.calculateScore();
         
         for (int frame = 1; frame < MAX_FRAMES; frame++)
         {
            bowlerNumber = temp.getPlayerNumber();
            pinFall = temp.getPinFallByFrame(frame);
            score = temp.getFrameScore(frame);            
            
            myDisplay.submitFrame(pinFall, score, frame, bowlerNumber);
            
         }// End loop      
      
      }// End for loop
      
      myDisplay.displayScoreBoard();
      
   }// End method

//--------------------------------


}// ENd class
