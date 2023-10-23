public class Warmup
{
    public static double findAvg(int[] array) {
        double sum = 0;
        for(int k = 0; k < array.length; k++)
            sum += array[k];
        return sum / array.length; 
    }
    public static void main(String[] args)
    {
        int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
        int sum = 0;
        for(int k = 0; k < tally.length; k++)
            sum += tally[k];
        System.out.println(sum); 
        System.out.println(findAvg(tally)); 
    }
}