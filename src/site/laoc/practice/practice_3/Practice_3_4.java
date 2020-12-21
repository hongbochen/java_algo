package site.laoc.practice.practice_3;

import site.laoc.list.myarrayList.MyArrayList;

public class Practice_3_4 {

    // 两个有序数组的交集
    public MyArrayList<Integer> intersection(MyArrayList<Integer> a, MyArrayList<Integer> b){
        MyArrayList<Integer> cons = new MyArrayList<>();

        int m = a.size();
        int n = b.size();
        int i = 0,j = 0;

        while(i < m && j < n){
            //System.out.println(i + "----" + j);

            if(a.get(i) == b.get(j)){
                //System.out.println(a.get(i) + ",,,,," + b.get(j));
                cons.add(a.get(i));
                i++;
                j++;
                continue;
            }

            if(a.get(i) < b.get(j)){
                i++;
            }

            if(a.get(i) > b.get(j)){
                j++;
            }
        }

        return cons;
    }

    public static void main(String [] args){
        MyArrayList<Integer> a = new MyArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);

        MyArrayList<Integer> b = new MyArrayList<>();
        b.add(3);
        b.add(4);

        Practice_3_4 p = new Practice_3_4();
        p.intersection(a,b);
//        for(int i = 0;i < a.size();i++){
//            System.out.print(a.get(i) + " ");
//        }
    }
}
