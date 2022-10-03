class RedDie extends Die
{  
   private final int RED_SIDE = 5;
   private boolean rolledRed;  

//-------------------------------

   public RedDie()
   {     
      super(); 
      rolledRed = false;      
   }// End constructor

//-------------------------

   public boolean didRedRoll()
   {
      return rolledRed;
   }// End method

//---------------------------

   public boolean roll()
   {      
      int number;
      boolean pinDown;
      
      rolledRed = false;
      number = random.nextInt(MAX_SIDES);
      
      if (number == RED_SIDE)
         rolledRed = true;
      
      if (number == PIN_MISS)
         pinDown = false;
         
      else
         pinDown = true;
            
      return pinDown;
   }// End method

}// End class
