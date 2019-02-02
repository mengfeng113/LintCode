// Use map for O(1) get
// Use DLL for LRU purpose

public class LRUCache {
    class Node{
        final int key;
        int value;
        Node pre;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = this.next = null;
        }
        
        void addNext(Node node) {
            Node tmp = this.next;
            this.next = node;
            node.pre = this;
            node.next = tmp;
            if (tmp != null) {
                tmp.pre = node;
            }
        }
        
        Node delete() {
            Node pre = this.pre;
            Node next = this.next;
            if (pre != null)
                pre.next = next;
            if (next != null)
                next.pre = pre;
            return this;
        }
    }
    
    private final int cacheSize;
    private final Map<Integer, Node> map;
    
    // Double linked list
    private final Node head;
    private final Node tail;
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        cacheSize = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.addNext(tail);
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (map.containsKey(key)) {
            Node node = map.get(key);
            addToFront(node);
            return node.value;
        }
        return -1;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            addToFront(node);
        } else {
            if (map.size() >= cacheSize) {
                // delete from last
                map.remove(deleteFromBack().key);
            }
        
            // add at front
            Node node = new Node(key, value);
            map.put(key, node);
            addToFront(node);
        }
    }
    
    private void addToFront(Node node) {
        head.addNext(node.delete());
    }
    
    private Node deleteFromBack() {
        return tail.pre.delete();
    }
}