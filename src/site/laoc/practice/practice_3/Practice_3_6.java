package site.laoc.practice.practice_3;

import java.util.ArrayList;
import java.util.Iterator;

// Josephus问题
// 程序运行时间为O(N)
public class Practice_3_6 {

    public void josephus(int M,int N){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0;i < N;i++){
            list.add(i+1);
        }

        int size = 0;
        Iterator<Integer> its = list.iterator();
        while(its.hasNext()){
            int n = its.next();

            if(size == M){
                size = 0;
                System.out.print(n + " ");
                its.remove();
            }else{
                size++;
            }

            if(!its.hasNext()){
                its = list.iterator();
            }
        }
    }

    public static void main(String[] args){
        Practice_3_6 p = new Practice_3_6();
        p.josephus(1,5);

    }
}
