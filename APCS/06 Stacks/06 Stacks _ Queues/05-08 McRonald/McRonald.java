//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald {
   public static final int TIME = 1080; //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0; // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information

   public static int timeToOrderAndBeServed() {
      return (int)(Math.random() * 6 + 2);
   }
   public static void displayTimeAndQueue(Queue < Customer > q, int min) {
      outfile.println(min + ": " + q);
   }
   public static int getCustomers() {
      return customers;
   }
   public static double calculateAverage() {
      return (int)(1.0 * totalMinutes / customers * 10) / 10.0;
   }
   public static int getLongestWaitTime() {
      return longestWaitTime;
   }
   public static int getLongestQueue() {
      return longestQueue;
   }

   public static void main(String[] args) {
      try {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      } catch (IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
      mcRonald(TIME, outfile); //run the simulation
      outfile.close();
   }

   public static void mcRonald(int TIME, PrintWriter of) {
      Queue<Customer> q = new LinkedList<>();
      int i = 0;
      while(i < TIME) {
         if(q.isEmpty()) {
            if(Math.random() <= .2) {
               customers++;
               q.add(new Customer(i, timeToOrderAndBeServed(), customers));
            }
            displayTimeAndQueue(q, i);
            i++;
         } else {
            int temp = q.peek().getOrder();
            q.peek().setOrder(q.peek().getOrder() + i - q.peek().getArrivedAt());
            for(int j = temp; j > 0; j--) {
               if(Math.random() <= .2) {
                  customers++;
                  q.add(new Customer(i, timeToOrderAndBeServed(), customers));
               }
               outfile.println(q.peek().getCus() + " is now being served for " + j + " minutes.");
               displayTimeAndQueue(q, i);
               i++;
            }
            totalMinutes += q.peek().getOrder();
            longestWaitTime = Math.max(longestWaitTime, q.peek().getOrder());
            q.remove();
         }
         longestQueue = Math.max(longestQueue, q.size());
      }
      while(!q.isEmpty()) {
         int temp = q.peek().getOrder();
         q.peek().setOrder(q.peek().getOrder() + i - q.peek().getArrivedAt());
         for(int j = temp; j > 0; j--) {
            outfile.println(q.peek().getCus() + " is now being served for " + j + " minutes.");
            displayTimeAndQueue(q, i);
            i++;
         }
         totalMinutes += q.peek().getOrder();
         longestWaitTime = Math.max(longestWaitTime, q.peek().getOrder());
         q.remove();
         longestQueue = Math.max(longestQueue, q.size());
      }
      /*   report the data to the screen    */
      System.out.println("1 queue, 1 service window, probability of arrival = " + CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }

   static class Customer {
      /*Complete the Customer class with
       constructor, accessor methods, toString.*/
      private int arrivedAt;
      private int orderAndBeServed;
      private int cus;
      public Customer(int arriv, int ord, int cust) {
         arrivedAt = arriv;
         orderAndBeServed = ord;
         cus = cust;
      }
      public int getArrivedAt() {
         return arrivedAt;
      }
      public int getOrder() {
         return orderAndBeServed;
      }
      public int getCus() {
         return cus;
      }
      public void setOrder(int x) {
         orderAndBeServed = x;
      }
      @Override
      public String toString() {
         return Integer.toString(cus);
      }
   }
}