package site.laoc.practice.practice_3;

import java.util.ArrayList;
import java.util.List;

public class Practice_3_7 {

    public static List<Integer> makeList(int N){
        ArrayList<Integer> lst = new ArrayList<>();

        for(int i = 0;i < N;i++){
            lst.add(i);
            lst.trimToSize();;
        }

        return lst;
    }

    /**
     * 分析上述程序的运行时间
     * 1：首先需要查看trimToSize()方法
     *  Arrays.copyOf(elementData, size);
     *  在这里，我们可以看到，每次循环都需要copy一遍，
     *  所以这里的复杂度为O(N2)
     */

}
