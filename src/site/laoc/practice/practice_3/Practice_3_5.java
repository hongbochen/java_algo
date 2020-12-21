package site.laoc.practice.practice_3;

import site.laoc.list.myarrayList.MyArrayList;

public class Practice_3_5 {

    /**
     * 求两个已拍好顺序的表的并集
     * @param L1
     * @param L2
     */
    public void union(MyArrayList<Integer> L1,MyArrayList<Integer> L2){
        int m = L1.size();
        int n = L2.size();

        int i = 0,j = 0;

        while(i < m && j < n){

            if(L1.get(i) == L2.get(j)){
                System.out.print(L1.get(i) + " ");
                i++;
                j++;
            }else if(L1.get(i) < L2.get(j)){
                System.out.print(L1.get(i) + " ");
                i++;
            }else if(L1.get(i) > L2.get(j)){
                System.out.print(L2.get(j) + " ");
                j++;
            }
        }


        if(i == m && j < n){
            for(int p = j; p < n;p++){
                System.out.print(L2.get(p) + " ");
            }
        }

        if(j == n && i < m){
            for(int p = i; p < m;p++){
                System.out.print(L1.get(p) + " ");
            }
        }
    }

    public static void main(String [] args){
        MyArrayList<Integer> a = new MyArrayList<>();
        a.add(1);
        //a.add(2);
        //a.add(3);
        //a.add(4);

        MyArrayList<Integer> b = new MyArrayList<>();
        b.add(3);
        b.add(4);
        b.add(5);

        Practice_3_5 p = new Practice_3_5();
        p.union(a,b);

    }
}
