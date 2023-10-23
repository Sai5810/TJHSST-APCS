// Name: Sai Vaka
// Date: 9/12/20
import java.text.DecimalFormat;
public class SmartCard_Driver {
   public static void main(String[] args) {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.exit(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.exit(suburbia);				//Insufficient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insufficient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      //Make more test cases.  What else needs to be tested?
   }
}
class Station {
   private final int z;
   private final String n;
   public Station(String name, int zone) {
      z = zone;
      n = name;
   }
   int getZone() {
      return z;
   }
   String getName() {
      return n;
   }
}

class SmartCard {
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   private double m;
   private Station curs;
   private boolean pres;
   public SmartCard(double money) {
      m = money;
      curs = null;
      pres = false;
   }
   void addMoney(double d) {
      m += d;
   }
   String getBalance() {
      return df.format(m);
   }
   boolean isBoarded() {
      return pres;
   }
   double cost(Station s) {
      return MIN_FARE + (0.75 * Math.abs(curs.getZone() - s.getZone()));
   }
   void board(Station s) {
      if (pres) {
         System.out.println("Error: already boarded?!");
      }
      else if (m < MIN_FARE) {
         System.out.println("Insufficient funds to board. Please add more money.");
      }
      else {
         pres = true;
         curs = s;
         System.out.println("Boarded at " + s.getName() + ". SmartCard has " + df.format(m));
      }
   }
   void exit(Station s) {
      if (!pres) {
         System.out.println("Error: Did not board?!");
      }
      else if (cost(s) > m) {
         System.out.println("Insufficient funds to exit. Please add more money.");
      }
      else {
         pres = false;
         m -= cost(s);
         System.out.println("From " + curs.getName() + " to " + s.getName() + " costs " + df.format(cost(s)) + ". SmartCard has " + df.format(m));
         curs = null;
      }
   }
   double getMoneyRemaining() {
      return m;
   }
   Station getBoardedAt() {
      return curs;
   }
   boolean getIsOnBoard() {
      return pres;
   }
}



