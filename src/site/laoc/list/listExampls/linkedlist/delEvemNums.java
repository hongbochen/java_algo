package site.laoc.list.listExampls.linkedlist;

import java.util.Iterator;
import java.util.List;

// 删除列表中的偶数
public class delEvemNums {

    /**
     * 1：对于ArrayList: get方法的时间复杂度是常数的，而remove()方法的时间复杂度是0(N)，
     *  所以对于该删除方法的时间复杂度是二次的。
     * 2：对于LinkedList：get方法的时间复杂度是O(N)，而remove()方法也是低效。因此该例程花费也是二次的。
     * @param lst
     */
    public static void removeEventsVer1(List<Integer> lst){
        int i = 0;

         while(i < lst.size())
             if(lst.get(i) % 2 == 0)
                 lst.remove(i);
             else
                 i++;
    }

    /**
     * 该放大是报异常的，因为当一项被删除的时候，由增强的for循环所使用的基础迭代器是非法的。
     * @param lst
     */
    public static void removeEventsVer2(List<Integer> lst){
        for(Integer x : lst)
            if(x % 2 == 0)
                lst.remove(x);
    }

    /**
     * 1：ArrayList: 迭代器操作是常数，remove()方法是O(N)，则该方法是二次时间
     * 2：LinkedList: 迭代器是常数，remove()方法也是常数，则该方法是线性时间
     * @param lst
     */
    public static void removeEventsVer3(List<Integer> lst){
        Iterator<Integer> itr = lst.iterator();

        while(itr.hasNext())
            if(itr.next() % 2 == 0)
                itr.remove();
    }
}
