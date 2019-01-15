/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval l1, Interval l2){
                return l1.start - l2.start;
            }
        });
        Interval p = null;
        for(Interval in : intervals){
            if(p == null || in.start >= p.end){
                p = in;
            }
            else {
                return false;
            }
        }
        return true;
    }
}