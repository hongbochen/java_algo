package site.laoc.tree;

import site.laoc.stack.stackImpl.ArrayStack;

public class ExpressBinaryTree extends BinaryTree{

    // 表达式树
    public void createExpressTree(String sufPress){
        ArrayStack<BinaryNode<Character>> stack = new ArrayStack<>();

        for(int i = 0;i < sufPress.length();i++){
            char c = sufPress.charAt(i);

            boolean sym = isSym(c);

            if(sym){
                // 处理
                BinaryNode c1 = stack.pop();
                BinaryNode c2 = stack.pop();

                BinaryNode<Character> sNode = new BinaryNode<>(c,c2,c1);
                stack.push(sNode);
            }else{
                BinaryNode<Character> tNode = new BinaryNode<>(c,null,null);
                stack.push(tNode);
            }
        }

        BinaryNode<Character> sNode = stack.pop();
        root = sNode;
    }

    private boolean isSym(char c){
        if(c == '+' || c == '-' || c == '*' | c == '/')
            return true;

        return false;
    }

    public static void main(String [] args){
        String sufs = "ab+cde+**";

        ExpressBinaryTree tree = new ExpressBinaryTree();
        tree.createExpressTree(sufs);

        // 中序遍历
        System.out.println("中序遍历结果：");
        tree.middlePrint();

        System.out.println();

        // 前序遍历
        System.out.println("前序遍历结果：");
        tree.prePrint();

        System.out.println();

        // 后序遍历
        System.out.println("后序遍历结果：");
        tree.sufPrint();
    }
}
