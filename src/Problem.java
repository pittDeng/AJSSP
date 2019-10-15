import java.io.Serializable;
import java.util.ArrayList;

public class Problem implements Serializable {
    public int [] time;
    public ArrayList<ArrayList<Integer>> leaf;
    public int [] machine;
    public int [] dueDate;
    public int [] orderNum;
    public int profit;
    public int machineNum;
    public int operationNum;
    public int smallestTime;

    /**
     *
     * @return the longest distance in the tree, i.e.
     *      The minimum time need to be paid in the processing of part indexed by {@code index}
     */
    public int getSmallestTime(){
        return (this.smallestTime=getSmallestTime(0));
    }
    /**
    @param index The index of the subtree which needs to calculate the longest distance in subtree
     @return the longest distance in the subtree index, i.e.
     The minimum time need to be paid in the processing of part indexed by {@code index}
     */
    public int getSmallestTime(int index){
        int smallest=0;
        for(int ti=0;ti<leaf.get(index).size();++ti){
            smallest=Math.max(smallest,getSmallestTime(leaf.get(index).get(ti)));
        }
        return time[index]+smallest;
    }


}
