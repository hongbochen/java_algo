package site.laoc.practice.practice_3;

import java.util.HashSet;

/**
 * 判断链表中是否有循环，时间复杂度为O(N)，空间复杂度为O(N)
 *
 *  使用HashSet缓存节点ID
 */

/**
 * 1、判断单链表中是否有环
 * 2、如果存在环，找出环的入口点
 * @param <T>
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
     * 使用HashSet保存节点 --- 对应问题"a"
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
     *  --- 对应问题 “b”
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

    public static void main(String args[]){
        Practice_3_34_a<Integer> practice_3_34_a = new Practice_3_34_a<>();

        practice_3_34_a.add(1);
        practice_3_34_a.add(2);
        practice_3_34_a.add(3);
        practice_3_34_a.add(4);
        practice_3_34_a.add(5);

        practice_3_34_a.circle(2);
        System.out.println(practice_3_34_a.hasCircleByLowAndFast());
    }
}
