package site.laoc.tree.btree;

public class Entry implements Comparable<Entry>{
    int key;
    String value;

    public Entry(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Entry o) {
        return Integer.compare(key,o.key);
    }

    @Override
    public String toString() {
        return "{key = " + key + "}";
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
