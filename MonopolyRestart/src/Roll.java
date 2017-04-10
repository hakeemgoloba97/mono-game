public class Roll {
			static int  dieValue1=0;
			static int dieValue2=0;
			private static int diceSum=0;
		    public static int roll()
			{
		    		dieValue1=((int) (Math.random() * 6))+1;
					
					dieValue2= ((int) (Math.random() * 6))+1 ;
				diceSum= dieValue1+dieValue2;
				return diceSum;
			}
		   
		    public int getDice1(){
		    	return dieValue1;
		    }
		    
		    public int getDice2(){
		    	return dieValue2;
		    }

			public static int getTotal() {
				return dieValue1 + dieValue2;
			}
	}

