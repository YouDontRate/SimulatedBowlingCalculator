import java.util.Random;

class Die
{
   protected final int MAX_SIDES = 6;
   protected final int PIN_MISS = 0;
   protected Random random;

//-------------------------------

   public Die()
   {
      random = new Random();
   }// End constructor

//-------------------------

   public boolean roll()
   {      
      int number;
      boolean pinDown;
      
      number = random.nextInt(MAX_SIDES);
      
      if (number == PIN_MISS)
         pinDown = false;
         
      else
         pinDown = true;
            
      return pinDown;
   }// End method

}// End class
