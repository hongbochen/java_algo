package site.laoc.practice.practice_3;

import java.util.List;

public class Practice_3_8 {

    public static void removeFirstHalf(List<?> lst){
        int theSize = lst.size() / 2;

        for(int i = 0;i < theSize;i++){
            lst.remove(0);
        }
    }
}


/**
 * a:首先存储theSIze，是为了能够存储删除元素的个数。
 *  如果动态机计算theSize()的话，则每删除一个元素，改值都会减一。
 *  b:运行时间为O(N2)
 *  c:运行时间为O(N)
 *  d:不会
 */
