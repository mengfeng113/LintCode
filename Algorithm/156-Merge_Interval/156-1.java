/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval l1, Interval l2){
                return l1.start - l2.start;
            }
        });
        List<Interval> res = new ArrayList();
        Interval p = null;
        for(Interval in : intervals){
            if(p == null || in.start > p.end){
                p = in;
                res.add(p);
            }
            else {
                p.end = Math.max(in.end, p.end);
            }
        }
        return res;
    }

}