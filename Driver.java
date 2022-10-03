class Driver
{

   public static void main(String[] args)
   {
      GameManager myManager;
      final int MAX_PLAYERS = 99;      
      final int MIN_PLAYERS = 1;
      int playerCount;
      
      playerCount = Integer.parseInt(args[0]);
      
      if (playerCount < MIN_PLAYERS || playerCount > MAX_PLAYERS) 
         System.out.println("Usage: Java Driver <player count>");      
      
      else
      {
         myManager = new GameManager(playerCount);
         myManager.startGame();      
      }// End else               
   
   }// End main


}// End class
