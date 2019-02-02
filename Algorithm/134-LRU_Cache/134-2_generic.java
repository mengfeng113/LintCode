public class LRUCache {
    class Node<K,V> {
        final K key;
        V value;
        Node pre;
        Node next;
        
        Node(K key, V value) {
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
    
    class LRU<K, V> {
        private final int cacheSize;
        private final Map<K, Node<K, V>> map;
        private final Node<K, V> head, tail;
        
        public LRU(int capacity) {
            cacheSize = capacity;
            map = new HashMap<>();
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.addNext(tail);
        }
        
        public V get(K key) {
            // write your code here
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                addToFront(node);
                return node.value;
            }
            return null;
        }
        
        public void set(K key, V value) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                addToFront(node);
            } else {
                if (map.size() >= cacheSize) {
                    map.remove(deleteFromBack().key);
                }
            
                Node<K, V> node = new Node(key, value);
                map.put(key, node);
                addToFront(node);
            }
        }
        
        private void addToFront(Node<K, V> node) {
            head.addNext(node.delete());
        }
        
        private Node<K, V> deleteFromBack() {
            return tail.pre.delete();
        }
    }
    
    
    private final LRU<Integer, Integer> lru;
    
    /*
    * @param capacity: An integer
    */
    public LRUCache(int capacity) {
        lru = new LRU<Integer, Integer>(capacity);
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        Integer val = lru.get(key);
        return val == null ? -1 : val; 
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        lru.set(key, value);
    }
}