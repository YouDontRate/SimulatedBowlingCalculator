class DiceSet
{   
   private final int MAX_DICE = 10;
   private final int RED_PIN_INDEX = 0;   
   private RedDie redDie;
   private Die[] dice;   
   
//---------------------------------

   public DiceSet()
   {
      dice = new Die[MAX_DICE];      
      redDie = new RedDie();
      makeDice();
   }// End constructor
   
//----------------------------   

   private void makeDice()
   {
      Die tempDie;      
      
      for (int i = 1; i < MAX_DICE; i++)
      {
         tempDie = new Die();
         dice[i] = tempDie;
      }// End for loop
   
   }// End method

//----------------------------------

   public boolean[] firstThrow()
   {
      boolean pinDown;
      boolean redPinRoll;
      boolean didRedLand;
      boolean[] pinFall;       
      
      pinFall = new boolean[MAX_DICE];
      
      for (int i = 1; i < MAX_DICE; i++)
      {
         pinDown = dice[i].roll();
         pinFall[i] = pinDown;
      }// End for loop      
      
      redPinRoll = redDie.roll();
      didRedLand = redDie.didRedRoll();
      
      pinFall[RED_PIN_INDEX] = redPinRoll;
      
      if (didRedLand)
      {
         for (int i = 1; i < MAX_DICE; i++)
         {
            pinDown = pinFall[i];
            
            if (!pinDown)
            {
               pinFall[RED_PIN_INDEX] = false;
               break;
            }// End if      
            
         }// End loop         
            
      }// End if     
      
      return pinFall;
   }// End method

//-------------------------------

   public boolean[] secondThrow(int downedPins)
   {
      boolean pinDown;      
      boolean redPinRoll;
      boolean didRedLand;
      boolean[] pinFall;
      int pinsRemaining;            
      
      pinsRemaining = MAX_DICE - downedPins;
      pinFall = new boolean[pinsRemaining];      
      
      for (int i = 1; i < pinsRemaining; i++)
      {
         pinDown = dice[i].roll();
         pinFall[i] = pinDown;
      }// End for loop       
      
      redPinRoll = redDie.roll();
      didRedLand = redDie.didRedRoll();
      
      if (pinsRemaining > RED_PIN_INDEX)   
         pinFall[RED_PIN_INDEX] = redPinRoll;        
         
      if (didRedLand)
      {
         for (int i = 1; i < pinsRemaining; i++)
         {
            pinDown = pinFall[i];
               
            if (!pinDown)
            {
               pinFall[RED_PIN_INDEX] = false;
               break;
            }// End !pinDown if      
            
         }// End loop         
            
      }// End if  
      
      return pinFall;
      
   }// End method

//--------------------------------




//-----------TESTING ONLY---------------

   public static void main(String[] args)
   {
      DiceSet test = new DiceSet();
      boolean[] pinsDown;
      
      pinsDown = test.firstThrow();
      
      for (int i = 0; i < pinsDown.length; i++)
         System.out.println(pinsDown[i]);
   
      System.out.println("-----SECOND THROW----");
   
      pinsDown = test.secondThrow(0);
      
      for (int i = 0; i < pinsDown.length; i++)
         System.out.println(pinsDown[i]);
   
   }// End test main
   

}// End class
