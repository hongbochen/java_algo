package site.laoc.tree.btree;

import java.util.Collections;
import java.util.List;

public class BTree {

    private final int m; // B树的阶
    private final int min; // 元素最小值
    private Node root;

    public BTree(int m){
        this.m = m;
        this.min = (int)Math.ceil(m/2.0) - 1;
    }

    public Node getRoot(){
        return root;
    }

    public void printBTree(){
        if(root == null){
            return;
        }


    }

    private void printNode(Node node){
        if(node == null){
            return;
        }

        for(int i = 0;i < node.getEntrys().size();i++){
            System.out.print(node.getEntrys().get(i) + " ");
        }
    }

    // 搜索
    public Entry searchEntry(int key){
        return searchEntry(root,key);
    }

    // 递归搜索
    private Entry searchEntry(Node node,int key){
        if(node == null){
            return null;
        }

        // 使用二分查找定位下表
        int index = Collections.binarySearch(node.getEntrys(),new Entry(key,null));

        if(index >= 0){
            return node.getEntrys().get(index);
        }else{
            if(node.getChildNodes().size() == 0){
                return null;
            }

            return searchEntry(node.getChildNodes().get(-index - 1),key);
        }
    }

    // 添加元素
    public void add(Entry entry){
        // root 为空，直接插入
        if(root == null){
            Node node = new Node();
            node.add(entry);
            root = node;
            return;
        }

        add(root,entry);
    }

    //  添加元素 - 递归
    private void add(Node node,Entry entry){
        // 当前节点为叶子节点

        if(node.getChildNodes().size() == 0){

            // 如果当前节点元素未满，直接添加节点
            if(node.getEntrys().size() < m - 1){
                node.add(entry);
                return;
            }

            // 如果当前节点的元素已满，则分裂插入
            node.getEntrys().add(entry);
            split(node);
        }

        // 当前节点为内部节点，继续往下调用（新插入的节点，只能是叶子节点）
        // 使用二分查找法找到要插入的分支
        int index = Collections.binarySearch(node.getEntrys(), entry);
        if (index < 0) {
            add(node.getChildNodes().get(-index - 1), entry);
        }
    }

    // 分离当前节点
    private void split(Node node){
        int mid = node.getEntrys().size() / 2;

        // 分隔值
        Entry seperateEntry = node.getEntrys().get(mid);
        // 分离后的左节点
        Node leftNode = new Node();
        leftNode.getEntrys().addAll(node.getEntrys().subList(0,mid));
        // 分离后的右节点
        Node rightNode = new Node();
        rightNode.getEntrys().addAll(node.getEntrys().subList(mid+1,node.getEntrys().size()));

        // 分离子节点
        if(node.getChildNodes().size() > 0){
            List<Node> leftChildNode = node.getChildNodes().subList(0,mid-1);

            for(Node temp : leftChildNode){
                temp.setParentNode(leftNode);
            }

            leftNode.getChildNodes().addAll(leftChildNode);

            List<Node> rightChildNode = node.getChildNodes().subList(mid+1,node.getEntrys().size() + 1);
            for(Node temp : rightChildNode){
                temp.setParentNode(rightNode);
            }

            rightNode.getChildNodes().addAll(rightChildNode);
        }

        // 当前节点为跟节点
        if(node.getParentNode() == null){
            Node newRoot = new Node();
            newRoot.add(seperateEntry);
            root = newRoot;
            leftNode.setParentNode(root);
            rightNode.setParentNode(root);

            root.addChild(leftNode).addChild(rightNode);
        }else{
            node.add(seperateEntry);
            leftNode.setParentNode(node.getParentNode());
            rightNode.setParentNode(node.getParentNode());
            node.getParentNode().addChild(leftNode).addChild(rightNode);
            node.getParentNode().getChildNodes().remove(node);

            // 若其父节点溢出，继续分离
            if(node.getParentNode().getEntrys().size() > m + 1){
                split(node.getParentNode());
            }
        }
    }
}
