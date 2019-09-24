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
}
