package site.laoc.practice.practice_3;

import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * 1、判断链表中是否有循环，时间复杂度为O(N)，空间复杂度为O(N)
 * 2、如果存在环，找出环的入口点
 * 3、如果存在环，则求出换上节点个数
 * 4、获取链表的长度
 * 5、如果存在环，求出环上距离任意一个节点最远的点（对面节点）
 * 6、（扩展）如何判断两个无环链表是否相交
 * 7、（扩展）如果相交，求出第一个相交的节点
 *
 * 针对6，7问题，例如两个链表A和B，两个链表无环，但相交，
 * 这样，我们将链表A的收尾项链，从链表B开始，便成为了一个有环链表，
 * 这样所有的问题都可以通过上述解决。
 *
 *  使用HashSet缓存节点ID
 */
public class Practice_3_34_a<T> {

    class Node<T>{
        T x;
        Node<T> next;

        public Node(T x,Node<T> next){
            this.x = x;
            this.next = next;
        }
    }

    private int theSize;
    private Node<T> startNode;

    public Practice_3_34_a(){
        startNode = null;
        theSize = 0;
    }

    public int size(){
        return theSize;
    }

    private Node<T> getNode(int index){
        if(index >= size())
            throw new IndexOutOfBoundsException();

        Node<T> node = startNode;
        for(int i = 0;i < index;i++){
            node = node.next;
        }

        return node;
    }

    public void add(T x){
        Node<T> node = new Node<>(x,null);

        if(startNode == null){
            startNode = node;
        }else{
            Node<T> tmp = getNode(size()-1);
            tmp.next = node;
        }

        theSize++;
    }

    public void circle(int nd){
        Node<T> tmp = getNode(size()-1);

        Node<T> tmp1 = getNode(nd);

        tmp.next = tmp1;
    }

    public void print(){
        Node<T> node = startNode;

        while(node != null){
            System.out.print(node.x + " ");
            node = node.next;
        }

        System.out.println();
    }

    /**
     * 使用HashSet保存节点
     * @return
     */
    public boolean hasCircle(){
        Node<T> node = startNode;

        HashSet hashSet = new HashSet();

        while(node != null){
            if(hashSet.contains(node.x)){
                return true;
            }

            hashSet.add(node.x);
            node = node.next;
        }

        return false;
    }

    /**
     * 使用快慢指针，慢指针每次移动一步，
     *            快指针每次移动两步，
     *            如果没有循环，则会退出遍历；
     *            如果有循环，则快慢指针最后会相遇
     * @return
     */
    public boolean hasCircleByLowAndFast(){
        Node<T> lowNode = startNode;
        Node<T> fastNode = startNode;

        int start = 1;
        while(lowNode != null && fastNode != null){
            if(start == 1){
                start = 0;
            }else{
                if(lowNode == fastNode){
                    return true;
                }
            }

            lowNode = lowNode.next;
            if(fastNode.next != null){
                fastNode = fastNode.next.next;
            }else{
                fastNode = null;
            }
        }

        return false;
    }

    /**
     * 获取快慢指针在链表中相遇的节点
     * @return
     */
    private Node<T> getLowAndFastMeetNode(){
        Node<T> lowNode = startNode;
        Node<T> fastNode = startNode;

        do{
            lowNode = lowNode.next;
            if(fastNode.next != null){
                fastNode = fastNode.next.next;
            }else{
                fastNode = null;
            }
        }while(lowNode != null && fastNode != null && lowNode != fastNode);

        if(lowNode == null || fastNode == null)
            return null;

        if(lowNode == fastNode)
            return lowNode;

        return null;
    }

    /**
     * 获取链表中环的入口点
     * @return
     */
    private Node<T> getCircleEntryNode(){
        Node<T> meetNode = getLowAndFastMeetNode();

        if(meetNode == null)
            throw new NoSuchElementException();

        // 从开始节点开始和从相遇节点开始
        Node<T> leftNode = startNode;
        Node<T> rightNode = meetNode;

        while(leftNode != null && rightNode != null && leftNode != rightNode){
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }

        if(leftNode == null)
            throw new NoSuchElementException();

        if(rightNode == null)
            throw new NoSuchElementException();

        if(leftNode == rightNode)
            return leftNode;

        return null;
    }

    public T getCircleEntryVal(){
        Node<T> node = getCircleEntryNode();

        if(node == null)
            return null;

        return node.x;
    }

    /**
     * 求出环上节点的个数
     * 思路：
     *  记录下slow指针和fast指针相遇的点tmp, 然后slow继续移动，直到移动到tmp节点，则为一圈，所经过的点都为环上节点
     * @return
     */
    public int getCircleNodeSize(){
        Node<T> tmp = getLowAndFastMeetNode();

        if(tmp == null)
            return 0;

        Node<T> sNode = tmp;

        int size = 0;
        do{
            sNode = sNode.next;
            size++;
        }while(sNode != tmp);

        return  size;
    }

    /**
     * 获取链表的长度：起始点到到入口点的长度 + 环的长度
     * @return
     */
    public int getLinkListSize(){
        int circleNums = getCircleNodeSize();

        // 获取起始点到入口点的长度
        Node<T> entryNode = getCircleEntryNode();
        if(entryNode == null)
            return circleNums;

        int size = 0;
        Node<T> sNode = startNode;
        do{
            size++;
            sNode = sNode.next;
        }while(sNode != entryNode);

        return circleNums + size;
    }

    private Node<T> getNode(T x){
        Node<T> sNode = startNode;

        while(sNode != null && sNode.x != x){
            sNode = sNode.next;
        }

        if(sNode == null)
            return null;

        return sNode;
    }

    /**
     * 如果存在环，求出环上距离任意一个节点最远的点（对面节点）
     *  从该节点为pt0,继续使用快慢指针，slow每次一步，fast每次2步；
     *  当fast = pt0 或 fast = pt0.next的时候，slow所在的位置就是
     *  pt0节点的最远距离的点。
     *
     * @param x 环上任意一点
     * @return
     */
    public T getCircleNodeFarawayNode(T x){
        Node<T> tNode = getNode(x);

        if(tNode == null)
            throw new NullPointerException();

        Node<T> slowNode = tNode;
        Node<T> fastNode = tNode;

        do{
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }while(fastNode != tNode && fastNode != tNode.next);

        return slowNode.x;
    }

    public static void main(String args[]){
        Practice_3_34_a<Integer> practice_3_34_a = new Practice_3_34_a<>();

        practice_3_34_a.add(1);
        practice_3_34_a.add(2);
        practice_3_34_a.add(3);
        practice_3_34_a.add(4);
        practice_3_34_a.add(5);

        practice_3_34_a.circle(2);
        // 判断链表中是否有环
        //System.out.println(practice_3_34_a.hasCircleByLowAndFast());

        // 找出环的入口点
        /*
        Integer entryVal = practice_3_34_a.getCircleEntryVal();

        if(entryVal == null){
            System.out.println("不存在入口点");
        }else{
            System.out.println(entryVal);
        }*/

        // 获取换上节点个数
        //int size = practice_3_34_a.getCircleNodeSize();
        //System.out.println(size);

        // 获取链表的长度
        //int length = practice_3_34_a.getLinkListSize();
        //System.out.println(length);

        // 获取环上某一个点的最远距离的相应点
        Integer x = practice_3_34_a.getCircleNodeFarawayNode(3);
        System.out.println(x);
    }
}
