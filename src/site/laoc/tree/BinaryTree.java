package site.laoc.tree;

import site.laoc.stack.stackImpl.ArrayStack;

// 二叉树
public class BinaryTree<T> {

    static class BinaryNode<T>{
        T element;
        BinaryNode<T> left; // 左子树
        BinaryNode<T> right; // 右子树

        public BinaryNode(T element){
            this(element,null,null);
        }

        public BinaryNode(T element,BinaryNode<T> left, BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public void print(){
            System.out.print(this.element + " ");
        }
    }

    protected BinaryNode<T> root;

    //前序遍历
    public void prePrint(){
        BinaryNode<T> tNode = root;

        innerPrePrint(tNode);
    }

    private void innerPrePrint(BinaryNode<T> node){
        if(node != null){
            node.print();
            innerPrePrint(node.left);
            innerPrePrint(node.right);
        }
    }


    // 中序遍历
    public void middlePrint(){
        BinaryNode<T> tNode = root;

        innerMiddlePrint(tNode);
    }

    private void innerMiddlePrint(BinaryNode<T> node){
        if(node != null){

            innerMiddlePrint(node.left);
            node.print();
            innerMiddlePrint(node.right);
        }
    }

    // 后序遍历
    public void sufPrint(){
        BinaryNode<T> tNode = root;

        innerSufPrint(tNode);
    }

    private void innerSufPrint(BinaryNode<T> node){
        if(node != null){
            innerSufPrint(node.left);
            innerSufPrint(node.right);

            node.print();
        }
    }


}
