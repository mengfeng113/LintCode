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
            node.next = next;
            node.pre = this;
            next.pre = node;
            next = node;
        }
        
        Node delete() {
            pre.next = next;
            next.pre = pre;
            pre = next = null;
            return this;
        }
    }
    
    class LRU<K, V> {
        private final int cacheSize;
        private final Map<K, Node<K, V>> map;
        // Double linked list
        private final Node<K, V> head, tail;
        
        public LRU(int capacity) {
            cacheSize = capacity;
            map = new HashMap<>();
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.pre = head;
        }
        
        public V get(K key) {
            // write your code here
            if (!map.containsKey(key)) {
                return null;
            }
            Node<K, V> node = map.get(key);
            node.delete();
            head.addNext(node);
            return node.value;
        }
        
        public void set(K key, V value) {
            Node<K, V> node = null;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.value = value;
                node.delete();
            } else {
                node = new Node(key, value);
                map.put(key, node);
                if (map.size() > cacheSize) {
                    map.remove(tail.pre.key);
                    tail.pre.delete();
                }
            }
            
            head.addNext(node);
        }
    }
    
    
    private final LRU<Integer, Integer> lru;
    
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
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