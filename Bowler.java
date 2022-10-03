class Bowler
{
   private final int ASCII_ZERO = 48;
   private final int MAX_FRAMES = 11;
   private final int STRIKE_COUNT = 10;
   private final String STRIKE = "X";
   private final String SPARE = "/";
   private final String DASH = "-";
   private int bowlerNumber;   
   private String[] pinFall;
   private String[] frameScores; 
   private DiceSet dice; 
    
//----------------------------------

   public Bowler(int bowlerNumber)
   {
      this.bowlerNumber = bowlerNumber;
      pinFall = new String[MAX_FRAMES];
      frameScores = new String[MAX_FRAMES];
      dice = new DiceSet();      
      frameScores[0] = "0";
   }// End constructor
   
//---------------------------------------   
   
   public String getPinFallByFrame(int frame)
   {
      return pinFall[frame];
   }// End method
   
//--------------------------------------

   public String getFrameScore(int frame)
   {
      return frameScores[frame];
   }// End method   
   
//-------------------------------------------

   public int getPlayerNumber()
   {
      return bowlerNumber;
   }// End method

//-----------------------------------

   public void calculateScore()
   {
      int score;
      String downedPins;
      
      for (int i = 1; i < MAX_FRAMES; i++)
      {
          
         downedPins = pinFall[i];
         
         if (i < STRIKE_COUNT)
            countFrame(downedPins, i);
      
         else
            countTenthFrame();
      
      }// End for loop         
   
   }// End method
   
//-----------------------------------

   private void countTenthFrame()
   {
      char pin1;
      char pin2;
      char pin3;
      int score1=0;
      int score2=0;
      int score3=0;
      int frameScore;
      int previousFrame;
      String tenthFrame;
      
      tenthFrame = pinFall[STRIKE_COUNT];
      
      if (tenthFrame.length() == 2)
      {
         pin1 = tenthFrame.charAt(0);
         pin2 = tenthFrame.charAt(1);
         
         score1 = getPinValue(pin1);
         score2 = getPinValue(pin2);         
         
         previousFrame = Integer.parseInt(frameScores[STRIKE_COUNT-1]);         
         
         frameScore = score1 + score2 + previousFrame;      
         frameScores[STRIKE_COUNT] = Integer.toString(frameScore);
         
      }// End if
      
      else 
      {
         pin1 = tenthFrame.charAt(0);
         pin2 = tenthFrame.charAt(1);      
         pin3 = tenthFrame.charAt(2);         
         
         if (pin1 != 'X')   
         {
            score1 = 0;         
            
            score2 = getPinValue(pin2);
            score3 = getPinValue(pin3);        
         }// End if
         
         else         
         {
            score1 = getPinValue(pin1);
            
            if (Character.isDigit(pin2))
            {            
               if (Character.isDigit(pin3))
               {
                  score2 = getPinValue(pin2);
                  score3 = getPinValue(pin3);
               }// End if 
               
               else
               {
                  score2 = 0;
                  score3 = getPinValue(pin3);
               }// End else             
                             
            }// End if            
            
            else
            {
               score2 = getPinValue(pin2);
               score3 = getPinValue(pin3);
            }// End else 
                       
         
         }// End else         
         
         previousFrame = Integer.parseInt(frameScores[STRIKE_COUNT-1]);         
         
         frameScore = score1 + score2 + score3 + previousFrame;      
         frameScores[STRIKE_COUNT] = Integer.toString(frameScore);         
         
      }// End else       
   
   }// End method

//--------------------------------------

   private int getPinValue(char pin)
   {
      int score = 0;
      
      switch (pin)
      {
      
         case 'X':
            score = STRIKE_COUNT;
            break;
         
         case '/':
            score = STRIKE_COUNT;
            break;
         
         case '-':
            score = 0;
            break;
         
         default:
            score = pin - ASCII_ZERO;
            break;        
      
      }// End switch      
      
      return score;
   
   }// End method

//-----------------------------------

   private void handleSpare(int frame)
   {
      char nextPin;
      int score1;
      int score2;      
      int frameScore;
      int previousFrame;
      String nextFrame;
      
      nextFrame = pinFall[frame+1];
      nextPin = nextFrame.charAt(0);           
      
      score1 = STRIKE_COUNT;           
      score2 = getPinValue(nextPin);             
             
      previousFrame = Integer.parseInt(frameScores[frame-1]);     
             
      frameScore = score1 + score2 + previousFrame;      
      frameScores[frame] = Integer.toString(frameScore);
   
   }// End method

//-------------------------------------

   private void ninthFrameStrike()
   {  
      char pin2;
      char pin3;
      int score1;
      int score2;
      int score3;      
      int frameScore;
      int previousFrame;
      final int NINTH_FRAME = 9;
      String nextFrame;      
      
      score1 = STRIKE_COUNT;
      nextFrame = pinFall[STRIKE_COUNT];  
      
      pin2 = nextFrame.charAt(0);
      pin3 = nextFrame.charAt(1);        
      
      score3 = getPinValue(pin3); 
      
      if (score3 == STRIKE_COUNT)
         score2 = 0;        
      
      else
         score2 = getPinValue(pin2);
      
      previousFrame = Integer.parseInt(frameScores[NINTH_FRAME-1]);     
      
      frameScore = score1 + score2 + score3 + previousFrame;      
      frameScores[NINTH_FRAME] = Integer.toString(frameScore);      
      
   }// End method

//----------------------------------------

   private void handleStrike(int frame)
   {
      char pin2;
      char pin3;
      int score1;
      int score2 = 0;
      int score3 = 0;
      int frameScore;
      int previousFrame;      
      String nextFrame;
      String thirdFrame;
      
      score1 = STRIKE_COUNT;
      nextFrame = pinFall[frame+1];  
      
      if (frame == STRIKE_COUNT -1)
         ninthFrameStrike();    
      
      else
      {      
         if (nextFrame.length() == 1)
         {         
            score2 = STRIKE_COUNT;
            thirdFrame = pinFall[frame+2];
               
            pin3 = thirdFrame.charAt(0);
               
            score3 = getPinValue(pin3);        
            
         }// End if      
         
         else if (nextFrame.length() == 2)
         {
            pin2 = nextFrame.charAt(0);
            pin3 = nextFrame.charAt(1);            
            
            if (pin3 == '/')
            {           
               score2 = 0;   
               score3 = STRIKE_COUNT;               
            }// End if
            
            else
            {
               score2 = getPinValue(pin2);
               score3 = getPinValue(pin3);     
            }// End else         
            
         }// End else if 
         
         previousFrame = Integer.parseInt(frameScores[frame-1]);
      
         frameScore = score1 + score2 + score3 + previousFrame;      
         frameScores[frame] = Integer.toString(frameScore);         
      
      }// End else           
   
   }// End method

//-------------------------------------

   private void countFrame(String pins, int frame)
   {
      char pin1;
      char pin2;           
      int score1;
      int score2;
      int previousFrame;
      int frameScore;     
      
      if (pins.length() == 2)
      {         
         pin1 = pins.charAt(0);         
         pin2 = pins.charAt(1);         
         
         if (pin2 == '/')         
            handleSpare(frame);       
                        
         else
         {
            score1 = getPinValue(pin1);
            score2 = getPinValue(pin2);
            
            previousFrame = Integer.parseInt(frameScores[frame-1]);
            
            frameScore = score1 + score2 + previousFrame;            
            frameScores[frame] = Integer.toString(frameScore);           
            
         }// End else                             
            
      }// End if      
      
      else        
         handleStrike(frame);                     
   
   }// End method

//----------------------------------

   public int handleFirstThrow(int frame)
   {     
      int fallenPins = 0;          
      boolean[] pinsDown;
      boolean downedPin;     
      String pins;    
      
      pinsDown = dice.firstThrow();
      
      for (int i = 0; i < pinsDown.length; i++)
      {
         downedPin = pinsDown[i];
         
         if (downedPin)
            fallenPins++;
            
      }// End for loop            
      
      if (fallenPins == MAX_FRAMES - 1)
      {
         pins = STRIKE;
         pinFall[frame] = pins;
      }// End if             
         
      else
      {
         pins = Integer.toString(fallenPins);      
         pinFall[frame] = pins;
      }// End else                
      
      return fallenPins;
      
   }// End method   

//---------------------------------

   public int handleSecondThrow(int firstThrowPins, int frame)
   {     
      int fallenPins = 0;
      int spareCount;      
      int pinsRemaining;
      boolean[] pinsDown;
      boolean downedPin;      
      String pins;
      
      spareCount = STRIKE_COUNT - firstThrowPins;
      pinsDown = dice.secondThrow(firstThrowPins);
         
      for (int i = 0; i < pinsDown.length; i++)
      {
         downedPin = pinsDown[i];
         
         if (downedPin)
            fallenPins++;
            
      }// End for loop
      
      pinsRemaining = spareCount - fallenPins;
      
      if (fallenPins == spareCount)
      {
         pins = SPARE;
         pinFall[frame] += pins;
      }// End if 
      
      else if (fallenPins == 0) 
      {
         pins = DASH;
         pinFall[frame] += pins;
      }// End else if           
      
      else
      {
         pins = Integer.toString(fallenPins);      
         pinFall[frame] += pins;
      }// End else      
      
      return pinsRemaining;
      
   }// End method
   
//----------------------------------

   public int handleExtraThrow(int downedPins, int frame)
   {     
      int fallenPins = 0;
      int spareCount;    
      int pinsRemaining;      
      boolean[] pinsDown;
      boolean downedPin;           
      String pins;    
      
      spareCount = STRIKE_COUNT - downedPins;
      
      if (downedPins == STRIKE_COUNT)
         pinsDown = dice.firstThrow();
         
      else
         pinsDown = dice.secondThrow(downedPins);
      
      for (int i = 0; i < pinsDown.length; i++)
      {
         downedPin = pinsDown[i];
         
         if (downedPin)
            fallenPins++;
            
      }// End for loop            
      
      if (fallenPins == STRIKE_COUNT)
      {
         pins = STRIKE;
         pinFall[frame] += pins;         
      }// End if 
      
      else if (fallenPins == spareCount)
      {        
         pins = SPARE;                    
         pinFall[frame] += pins;
      }// End if
      
      else if (fallenPins == 0)
      {
         pins = DASH;
         pinFall[frame] += pins;
      }// End else if            
         
      else
      {
         pins = Integer.toString(fallenPins);      
         pinFall[frame] += pins;
      }// End else           
      
      return fallenPins;
      
   }// End method

//-------------------------------







//*********TESTING ONLY***************

   public void display()
   {     
      
      for (int i = 1; i < 11; i++)
         System.out.println(pinFall[i]);
   
   }// End test display

//-------------------------------

   public void display2()
   {
      for (int i = 1; i < 11; i++)
         System.out.println(frameScores[i]);
   
   }// End test display

//-----------TESTING ONLY---------------

   public static void main(String[] args)
   {
      Bowler test = new Bowler(1);
      int pinsDown;
      int pinsRemaining = 0;      
      boolean giveExtraThrow = false;     
      
      
      
      for (int i = 1; i < 10; i++)
      {
         pinsDown = test.handleFirstThrow(i);
         
         if (pinsDown < 10)
            test.handleSecondThrow(pinsDown, i);
            
      }// End loop    
      
       
      
      pinsDown = test.handleFirstThrow(10);       
      
      if (pinsDown == 10)
      {
         pinsRemaining = test.handleExtraThrow(0, 10);
         giveExtraThrow = true;
      }   
         
      else
      {
         pinsRemaining = test.handleSecondThrow(pinsDown, 10);         
            
         if (pinsRemaining == 0)
            giveExtraThrow = true;
      }   
         
         
      if (giveExtraThrow)
         test.handleExtraThrow(pinsRemaining, 10);      
      
            
      
      test.display();
      
      test.calculateScore();
      
      System.out.println();
      
      test.display2();
      
   }// End test main   
   
   

}// End class
