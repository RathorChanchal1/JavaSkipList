import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        System.out.println(cakes);
        // TO be completed by students
        SkipList counting = new SkipList();
        // int forAns = 0;
        int res = 0;
        for(int i =0; i<cakes.size(); i++){
            int j=counting.upperBound(cakes.get(i));
                if(j!=Integer.MAX_VALUE){
                    counting.insert(cakes.get(i));
                    counting.delete(j);
                }else{
                    counting.insert(cakes.get(i));
                    res++;
                }
        }
        // res = forAns;
        return res;
    }
   
}
