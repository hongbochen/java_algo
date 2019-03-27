package site.laoc.practice;

import java.util.ArrayList;

public class Practice_3_1 {
    //在这里，列表使用ArrayList，则get方法的复杂度为常数，则这里的复杂度为O(N);这里的N为P的长度
    public void printLots(ArrayList<Integer> L, ArrayList<Integer> P){

        for(int i = 0;i < P.size();i++){
            System.out.println(L.get(P.get(i) - 1));
        }
    }

    public static void main(String [] args){
        ArrayList<Integer> L = new ArrayList<>();
        L.add(10);
        L.add(20);
        L.add(30);
        L.add(40);
        L.add(50);
        L.add(60);
        L.add(70);

        ArrayList<Integer> P = new ArrayList<>();
        P.add(1);
        P.add(3);
        P.add(4);
        P.add(5);

        Practice_3_1 p = new Practice_3_1();
        p.printLots(L,P);
    }
}
