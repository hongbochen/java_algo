package site.laoc.practice.practice_3;

import java.util.Collection;

public class Practice_3_10 {

    //查看 MyLinkedList的removeAll方法

    /**
     * 现在分析ArrayList的removeAll的方法
     */

    /*
    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }*/

    /**
     * complement为补集的意思，这里不是取补集，所以这个方法传递false
     * 1:首先从头开始遍历该数组中的数据
     * 2：判断给定的集合里面是否包含该数组的数据
     * 3：如果不包含，则将该数据放入新的数组中
     * 4：在finally中，首先判断r与size是否相等，
     * 如果不相等，表示尚未遍历完成，就抛出异常了，则将剩下的数据复制到新数组中
     * 接着判断w与size是否相等，如果相等，表示里面没有相同的数据，则不进行处理；
     * 如果不相等，则表示有些数据被移除了，则剩下的位置置为null，来进行GC处理。
     */

}
