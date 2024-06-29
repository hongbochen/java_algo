package site.laoc.tree.btree;

import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String args[]){
        int[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 15, 17, 18, 19, 20, 13};
        BTree tree = new BTree(5);
        for (int i : item) {
            tree.add(new Entry(i, "-->" + i));
        }
        System.out.println("----------------------打印当前树结构");
        System.out.println(JSONObject.toJSONString(tree));
        Entry entry = tree.searchEntry(16);
        System.out.println("查询键16：" + entry.getValue());

        System.out.println("----------------------删除键15");
        tree.remove(15);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键14");
        tree.remove(14);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键17");
        tree.remove(17);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键5");
        tree.remove(5);
        System.out.println(JSONObject.toJSONString(tree));

    }
}
