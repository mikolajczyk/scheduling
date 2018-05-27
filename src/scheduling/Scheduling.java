/*
 * Matt Mikolajczyk
   CSC 401
   Professor Zuo
 */
package scheduling;
import java.util.*;
/**
 *
 * @author matthew
 */
public class Scheduling {
    
   public static void main(String[]args){
        Scheduling scheduling = new Scheduling();
        scheduling.run();
    }

    
    public void run() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int n, lower, upper,temp1,temp2;
        
        System.out.println("Input number of intervals"); //get number of intervals
        n = scan.nextInt();
        System.out.println("input lower bound");         //get lower boound
        lower = scan.nextInt();
        System.out.println("Insert upper bound");        //get upper bound
        upper = scan.nextInt();
        
        Interval [] intervals = new Interval[n];
        
        for(int i = 0; i < n; i++){
            temp1 = rand.nextInt((upper + 1) + lower);
            temp2 = rand.nextInt((upper + 1) + lower);
            
            if(temp1 > temp2){
                int check = temp1;
                temp1 = temp2;
                temp2 = check;
            }
            
            intervals[i] = new Interval(temp1, temp2);
        }
        
        findOptimalIntervals(intervals);
    };
    
    public static void findOptimalIntervals(Interval[] intervals){
        System.out.println("Intervals to schedule: \t" + Arrays.toString(intervals));
        Arrays.sort(intervals);
        
        LinkedList<Interval> currentInterval = new LinkedList<Interval>();
        currentInterval.add(intervals[0]);
        Interval previousInterval = intervals[0];
        
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start >= previousInterval.finish){
                currentInterval.add(intervals[i]);
                previousInterval = intervals[i];
            }
        }
        
        System.out.println("Maximum set of compatible Intervals: ");
        for(Interval interval : currentInterval){
            System.out.println(interval);
        }
    }

   
class Interval implements Comparable<Interval>{
    public int start;
    public int finish;
    
    public Interval(int start, int finish){
        this.start = start;
        this.finish = finish;
    }
    @Override
    public int compareTo(Interval interval) {
        return this.finish - interval.finish;
    }
    
    public String toString(){
        return "[" + start + "," + finish + "]";
    }
    
    
}

}
