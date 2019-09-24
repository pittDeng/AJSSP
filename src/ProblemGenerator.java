import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ProblemGenerator {
    public static int MAX_LEAF_FOR_SINGLE_NODE=3;
    public static int MAX_TIME=10;
    public static int HEIGHT=3;
    public static Random random=new Random();
    public static void saveObject(Object o,String objectName){
        ObjectOutputStream oos=null;
        FileOutputStream fos=null;
        try{
            fos=new FileOutputStream(objectName);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                oos.close();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static Object readObject(String fileName){
        ObjectInputStream ois=null;
        FileInputStream fis=null;
        try{
            fis=new FileInputStream(fileName);
            ois=new ObjectInputStream(fis);
            return ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                ois.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public static File generateFile(String fileName){
        File file=new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(String.format("Cannot create the file {1}",fileName));
                e.printStackTrace();
            }
        }
        return file;
    }
    public static void save(String fileName,String content){
        File file=new File("Hello.txt");
        FileWriter fw=null;
        BufferedWriter bw=null;
        try {
            fw=new FileWriter(file);
            bw=new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static Problem generateProblem(){
        Problem problem=new Problem();
        ArrayList<ArrayList<Integer>> leaf=new ArrayList<>();
        leaf.add(new ArrayList<>());
        int counter=0;
        int i=0;
        int lastleaf=0;
        for(int temp=0,height=1;i<=lastleaf&&height<HEIGHT;++i){
            temp=random.nextInt(MAX_LEAF_FOR_SINGLE_NODE+1);
            for(int j=0;j<temp;++j){
                if(height<HEIGHT-1)
                    leaf.add(new ArrayList<>());
                leaf.get(i).add(++counter);
            }
            if(i==lastleaf){
             lastleaf=counter;
             ++height;
            }
        }
        problem.leaf=leaf;
        problem.operationNum=lastleaf+1;
        int [] time=new int[lastleaf+1];
        int [] machine=new int[lastleaf+1];
        int machineNum=(int)(0.8*time.length);
        for(int index=0;index<time.length;++index){
            time[index]=random.nextInt(MAX_TIME+1);
            if(index<machineNum){
                machine[index]=index;
            }
            else
                machine[index]=random.nextInt(machineNum);
        }
        for(int index=0;index<machine.length;++index){
            int tempA=random.nextInt(machine.length);
            int tempB;
            while((tempB=random.nextInt(machine.length))==tempA);
            int extemp=machine[tempA];
            machine[tempA]=machine[tempB];
            machine[tempB]=extemp;
        }
        problem.machine=machine;
        problem.machineNum=machineNum;
        problem.time=time;
        return problem;
    }
    public static void main(String [] args){
        Problem problem=generateProblem();
        saveObject(problem,"p01");
        Problem copyProblem=(Problem) readObject("p01");
        System.out.println("pause");
    }
}
