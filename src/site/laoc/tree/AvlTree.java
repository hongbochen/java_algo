package site.laoc.tree;

import java.util.NoSuchElementException;

public class AvlTree<T extends Comparable<T>> {

    private static class AvlNode<T>{
        T t;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T t, AvlNode<T> left,AvlNode<T> right){
            this.t = t;
            this.left = left;
            this.right = right;
        }

        AvlNode(T t){
            this(t,null,null);
        }

        public void print(){
            System.out.print(this.t + " ");
        }
    }

    private AvlNode<T> root;

    /**
     * 返回节点node的高度
     * @param node
     * @return
     */
    private int height(AvlNode<T> node){
        return node == null ? -1 : node.height;
    }

    public void insert(T t){
        root = insert(t,root);
    }

    private AvlNode<T> insert(T t,AvlNode<T> node){
        if(node == null)
            return new AvlNode<>(t,null,null);

        int comp = t.compareTo(node.t);

        if(comp < 0){
            // 向左子树添加
            node.left = insert(t,node.left);
        }else if(comp > 0){
            node.right = insert(t,node.right);
        }else{
            ;
        }

        // 去平衡
        return balance(node);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<T> balance(AvlNode<T> node){
        if(node == null)
            return node;

        if(height(node.left) - height(node.right) > ALLOWED_IMBALANCE){
            if(height(node.left.left) >= height(node.left.right)){
                // 左左，右旋
                node = rotateWithLeftChild(node);
            }else{
                // 左右，先左旋，再右旋
                node = doubleWithLeftChild(node);
            }
        }else if(height(node.right) - height(node.left) > ALLOWED_IMBALANCE){
            if(height(node.right.right) >= height(node.right.left)){
                // 右右，左旋
                node = rotateWithRightChild(node);
            }else{
                // 右左，先右旋，再左旋
                node = doubleWithRightChild(node);
            }
        }

        node.height = Math.max(height(node.left),height(node.right)) + 1;

        return node;
    }

    public void remove(T x){
        root = remove(root,x);
    }

    private AvlNode<T> remove(AvlNode<T> node,T x){
        if(node == null)
            return node;

        int comp = x.compareTo(node.t);

        if(comp < 0){
            node.left = remove(node.left,x);
        }else if(comp > 0){
            node.right = remove(node.right,x);
        }else if(node.left != null && node.right != null){
            // 找到右子树中最小的元素
            node.t = findMin(node.right).t;
            node.right = remove(node.right,node.t);
        }else{
            // 有一个为空
            node = (node.left != null) ? node.left : node.right;
        }

        return balance(node);
    }

    public T findMin(){
        return findMin(root).t;
    }

    private AvlNode<T> findMin(AvlNode<T> node){
        if(node == null)
            throw new NoSuchElementException();

        if(node.left == null)
            return node;

        return findMin(node.left);
    }

    public T findMax(){
        return findMax(root).t;
    }

    private AvlNode<T> findMax(AvlNode<T> node){
        if(node == null)
            throw new NoSuchElementException();

        if(node.right == null)
            return node;

        return findMax(node.right);
    }

    public boolean contains(T x){
        return contains(root,x);
    }

    private boolean contains(AvlNode<T> node, T x){
        if(node == null)
            return false;

        int comp = x.compareTo(node.t);

        if(comp == 0)
            return true;

        return contains(node.left,x) || contains(node.right, x);
    }

    // 右旋
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.left;

        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),k2.height) + 1;

        return k1;
    }

    // 左旋
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.right;

        k2.right = k1.left;
        k1.left = k2;

        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(k2.height,height(k1.right)) + 1;

        return k1;
    }

    // 左-右情况，先左旋，再右旋
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
        k3.left = rotateWithRightChild(k3.left);

        return rotateWithLeftChild(k3);
    }


    // 右-左情况，先右旋，再左旋
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
        k3.right = rotateWithLeftChild(k3.right);

        return rotateWithRightChild(k3);
    }

    //前序遍历
    public void prePrint(){
        AvlNode<T> tNode = root;

        innerPrePrint(tNode);
    }

    private void innerPrePrint(AvlNode<T> node){
        if(node != null){
            node.print();
            innerPrePrint(node.left);
            innerPrePrint(node.right);
        }
    }


    // 中序遍历
    public void middlePrint(){
        AvlNode<T> tNode = root;

        innerMiddlePrint(tNode);
    }

    private void innerMiddlePrint(AvlNode<T> node){
        if(node != null){

            innerMiddlePrint(node.left);
            node.print();
            innerMiddlePrint(node.right);
        }
    }

    // 后序遍历
    public void sufPrint(){
        AvlNode<T> tNode = root;

        innerSufPrint(tNode);
    }

    private void innerSufPrint(AvlNode<T> node){
        if(node != null){
            innerSufPrint(node.left);
            innerSufPrint(node.right);

            node.print();
        }
    }

    public static void main(String [] args){
        AvlTree<Integer> avlTree = new AvlTree<>();
        for(int i = 1;i < 11;i++){
            avlTree.insert(i);
        }

        avlTree.prePrint();
    }
}
