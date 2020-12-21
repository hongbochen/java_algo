package site.laoc.practice.practice_3;
import site.laoc.list.myLinkedList.*;

public class Practice_3_18<T> extends MyLinkedList{

    /**
     * 在首部添加元素
     * @param x 被添加的元素
     */
    public void addFirst(T x){
        add(0,x);
    }

    /**
     * 在链表尾部添加元素
     * @param x 要被添加的元素
     */
    public void addLast(T x){
        add(x);
    }

    /**
     * 移除链表的第一个元素
     * @return 被移除的元素
     */
    public T removeFirst(){
        if(size() == 0)
            throw new IndexOutOfBoundsException();
        return (T) remove(0);
    }

    /**
     * 移除链表的最后一个元素
     * @return 被移除的元素
     */
    public T removeLast(){
        if(size() == 0)
            throw new IndexOutOfBoundsException();
        int index = size();
        return (T) remove(index-1);
    }

    /**
     * 获取链表的第一个元素
     * @return 链表的第一个元素
     */
    public T getFirst(){
        if(size() == 0)
            return null;
        return (T) get(0);
    }

    /**
     * 获取链表的最后一个元素
     * @return 链表的最后一个元素
     */
    public T getLast(){
        if(size() == 0)
            return null;
        int index = size();
        return (T) get(index - 1);
    }

    public void printList(){
        for(int i = 0;i < size();i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Practice_3_18<Integer> list = new Practice_3_18<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("原始列表：");
        list.printList();

        // addFirst
        list.addFirst(10);
        System.out.println("addFirst之后：");
        list.printList();

        // addLast
        list.addLast(11);
        System.out.println("addLast之后：");
        list.printList();

        // removeFirst
        list.removeFirst();
        System.out.println("removeFirst之后：");
        list.printList();

        // removeLast
        list.removeLast();
        System.out.println("removeLast之后：");
        list.printList();

        System.out.println("getFirst: " + list.getFirst());
        System.out.println("getLast: " + list.getLast());
    }
}
