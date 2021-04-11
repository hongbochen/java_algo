package site.laoc.tree;

// 二叉搜索树

import java.util.NoSuchElementException;

/**
 * 对于树中的每个节点X，他的左子树中所有项的值小于X中的项，
 * 他的右子树中所有项的值大于X中的项。
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {

    public BinarySearchTree(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean contains(T x){
        return contains(x,root);
    }

    public T findMin(){
        return findMin(this.root).element;
    }

    public T findMax(){
        return findMax(root).element;
    }

    public void insert(T x){
        root = insert(root,x);
    }

    public void remove(T x){
        root = remove(root,x);
    }

    private BinaryNode<T> remove(BinaryNode<T> node,T x){
        if(!contains(x)){
            throw new NoSuchElementException();
        }

        if(node == null){
            return node;
        }

        if(x.compareTo(node.element) < 0){
            node.left = remove(node.left,x);
        }else if(x.compareTo(node.element) > 0){
            node.right = remove(node.right,x);
        }else if(node.left != null && node.right != null){
            // 找到元素之后，如果node的左右子树都不为null
            // 则向右查询，找到右子树中最小的元素
            node.element = findMin(node.right).element;
            node.right = remove(node.right,node.element);
        }else{
            //如果有一个为空，则
            return node.left != null ? node.left : node.right;
        }

        return node;
    }

    private BinaryNode<T> insert(BinaryNode<T> node,T x){
        if(node == null)
            node = new BinaryNode<>(x);

        if(x.compareTo(node.element) < 0)
            node.left = insert(node.left,x);
        else if(x.compareTo(node.element) > 0)
            node.right = insert(node.right,x);
        else
            ;

        return node;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node){
        if(node.left == null)
            return node;

        return findMin(node.left);
    }

    /**
     * 使用循环不适用递归实现查找最小节点
     * @param node
     * @return
     */
    private BinaryNode<T> findMinNoRecur(BinaryNode<T> node){

        if(node == null)
            throw new NoSuchElementException();

        BinaryNode<T> tNode = node;

        while(tNode.left != null){
            tNode = tNode.left;
        }

        return tNode;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node){
        if(node.right == null)
            return node;

        return findMax(node.right);
    }

    private boolean contains(T x, BinaryNode<T> node){
        if(node == null)
            return false;

        if(node.element.compareTo(x) == 0){
            return true;
        }

        return this.contains(x,node.left) || this.contains(x,node.right);
    }

    public void printTree(){
        printTree(root);
    }

    private void printTree(BinaryNode<T> node){
        if(node == null)
            return;

        node.print();
        printTree(node.left);
        printTree(node.right);
    }


    public static void main(String [] args){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
//        binarySearchTree.insert(8);
//        binarySearchTree.insert(2);
//        binarySearchTree.insert(3);
//        binarySearchTree.insert(10);
//        binarySearchTree.insert(5);
//        binarySearchTree.insert(6);
//
//        binarySearchTree.middlePrint();
//        System.out.println();
//        System.out.println(binarySearchTree.findMin());
//        System.out.println(binarySearchTree.findMax());
//        System.out.println(binarySearchTree.contains(12));
//
//        //binarySearchTree.remove(10);
//        //binarySearchTree.middlePrint();
//
//        binarySearchTree.remove(2);
//        binarySearchTree.middlePrint();
        binarySearchTree.insert(100);
        binarySearchTree.insert(80);
        binarySearchTree.insert(120);
        binarySearchTree.insert(60);
        binarySearchTree.insert(90);
        binarySearchTree.insert(110);
        binarySearchTree.insert(130);
        binarySearchTree.insert(50);
        binarySearchTree.insert(65);
        binarySearchTree.insert(85);
        binarySearchTree.insert(95);

        binarySearchTree.middlePrint();
        System.out.println();
        binarySearchTree.remove(80);
        binarySearchTree.middlePrint();
    }
}
