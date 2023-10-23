import java.util.*;

public class LunchRoom {
   public static final int TIME = 1080; //18 hrs * 60 min
   public static void main(String[] args) {
      PriorityQueue <Customer> queue = new PriorityQueue<>();
      int i = 0;
      int year;
      int[][] data = new int[4][3];
      while(i < TIME) {
         if (!queue.isEmpty()) {
            Random rn = new Random();
            for(int j = 0; j < rn.nextInt(5) + 2; j++) {
               if(Math.random() < .2) {
                  double r = Math.random();
                  if (r < .25) {
                     year = 2024;
                  } else if (r < .5) {
                     year = 2023;
                  } else if (r < .75) {
                     year = 2022;
                  } else {
                     year = 2021;
                  }
                  queue.add(new Customer(year, i));
               }
               i++;
               System.out.println(i + ": " + queue);
            }
            int curtime = i - queue.peek().getTime();
            data[queue.peek().getYear() - 2021][0]++;
            data[queue.peek().getYear() - 2021][1] = Math.max(data[queue.peek().getYear() - 2021][1], curtime);
            data[queue.peek().getYear() - 2021][2] += curtime;
            queue.remove();
         } else {
            if(Math.random() < .2) {
               double r = Math.random();
               if (r < .25) {
                  year = 2024;
               } else if (r < .5) {
                  year = 2023;
               } else if (r < .75) {
                  year = 2022;
               } else {
                  year = 2021;
               }
               queue.add(new Customer(year, i));
            }
            i++;
            System.out.println(i + ": " + queue);
         }
      }
      System.out.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      System.out.println("Senior\t\t"+data[0][0]+"\t\t"+data[0][1]+"\t\t"+(float)data[0][2]/data[0][0]);
      System.out.println("Junior\t\t"+data[1][0]+"\t\t"+data[1][1]+"\t\t"+(float)data[1][2]/data[1][0]);
      System.out.println("Sophmore\t\t"+data[2][0]+"\t\t"+data[2][1]+"\t\t"+(float)data[2][2]/data[2][0]);
      System.out.println("Freshman\t\t"+data[3][0]+"\t\t"+data[3][1]+"\t\t"+(float)data[3][2]/data[3][0]);
   }
}

class Customer implements Comparable < Customer > {
   private int myGradYear;
   private int entry;
   public Customer(int year, int time) {
      myGradYear = year;
      entry = time;
   }
   public int getYear() {
      return myGradYear;
   }
   public int getTime() {
      return entry;
   }
   public int compareTo(Customer obj) {
      return myGradYear - obj.getYear();
   }
   @Override
   public String toString() {
      switch(myGradYear) {
         case(2021):
            return (entry + ":Se");
         case(2022):
            return (entry + ":Ju");
         case(2023):
            return (entry + ":So");
      }
      return (entry + ":Fr");
   }
}