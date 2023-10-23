// Name:
// Date:

import java.text.DecimalFormat;
import java.util.*;

public class Polynomial_Driver {
   public static void main(String[] args) {
      Polynomial poly = new Polynomial(); // 2x^3 + -4x + 2
      poly.makeTerm(1, (double) -4);
      poly.makeTerm(3, 2.0);
      poly.makeTerm(0, 2.0);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at " + evaluateAt + ": " + poly.evaluateAt(evaluateAt));

      System.out.println("-----------");
      Polynomial poly2 = new Polynomial(); // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, (double) -5);
      poly2.makeTerm(4, 2.0);
      poly2.makeTerm(0, (double) -3);
      poly2.makeTerm(2, 1.0);
      System.out.println("Map:  " + poly2.getMap());
      System.out.println("String:  " + poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at " + evaluateAt + ": " + poly2.evaluateAt(evaluateAt));
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));

      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial();   // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map:  " + poly3.getMap());
      // System.out.println("String:  " + poly3.toString());
      //
      // Polynomial poly4 = new Polynomial();    // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map:  " + poly4.getMap());
      // System.out.println("String:  " + poly4.toString());
      // System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1
      //
      //    /*  testing the one-arg constructor  */
       System.out.println("===========================");
       Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
       System.out.println("Map:  " + poly5.getMap());
       System.out.println(poly5);

   }
}
interface PolynomialInterface {
   public void makeTerm(Integer exp, Double coef);
   public Map < Integer, Double > getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface {
   private Map <Integer, Double> poly = new TreeMap<>();
   public Polynomial(String s) {
      for(String i: s.split(" \\+ ")) {
         double coef = 1;
         int es = 0;
         if(i.charAt(0) == '-') {
            coef = Integer.parseInt(i.substring(0, 2));
            es = 2;
         } else if(i.charAt(0) != 'x') {
            coef = Integer.parseInt(i.substring(0, 1));
            es = 1;
         }
         if(i.length() == es) {
            poly.put(0, coef);
         } else {
            poly.put(Character.getNumericValue(i.charAt(es+2)), coef);
         }
      }
   }

   public Polynomial() {

   }

   public void makeTerm(Integer exp, Double coef) {
      poly.put(exp, coef);
   }
   public Map <Integer, Double> getMap() {
      return poly;
   }
   public double evaluateAt(double x) {
      double sum = 0;
      for(Integer i : poly.keySet()) {
         sum += poly.get(i) * Math.pow(x,i);
      }
      return sum;
   }
   public Polynomial add(Polynomial other) {
      Polynomial sum = new Polynomial();
      for(Integer i : poly.keySet()) {
         if(other.poly.containsKey(i)) {
            sum.makeTerm(i, other.poly.get(i) + poly.get(i));
         } else {
            sum.makeTerm(i, poly.get(i));
         }
      }
      for(Integer i : other.poly.keySet()) {
         if(!sum.poly.containsKey(i)) {
            sum.makeTerm(i, other.poly.get(i));
         }
      }
      return sum;
   }
   public Polynomial multiply(Polynomial other) {
      Polynomial prod = new Polynomial();
      for(Integer i : poly.keySet()) {
         for(Integer j : other.poly.keySet()) {
            int exp = i + j;
            double coef = poly.get(i) * other.poly.get(j);
            if(prod.poly.containsKey(exp)) {
               coef += prod.poly.get(exp);
            }
            prod.makeTerm(exp, coef);
         }
      }
      return prod;
   }
   public String toString() {
      StringBuilder s = new StringBuilder();
      DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
      df.setMaximumFractionDigits(3);
      for(Integer exp : poly.keySet()) {
         double coef = poly.get(exp);
         StringBuilder cor = new StringBuilder();
         cor.append(df.format(coef)).reverse();
         if(coef != 0) {
            if(exp == 0) {
               s.append(cor);
            } else if(exp == 1) {
               if(coef == 1) {
                  s.append(" + ").append("x");
               } else {
                  s.append(" + ").append("x").append(cor);
               }
            }
            else if(coef == 1) {
               s.append(" + ").append(exp).append("^x");
            } else {
               s.append(" + ").append(exp).append("^x").append(cor);
            }
         }
      }
      return s.reverse().toString();
   }
}


