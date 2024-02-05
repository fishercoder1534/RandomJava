package hashmap;

import java.util.HashMap;
import java.util.Map;

public class MainApp {
    private static final int NUMBER_OF_MAP_ENTRIES = 2;

    public static void main(String... args) {
        System.out.println("Program started.");
        MainApp mainApp = new MainApp();
        mainApp.understandHashMapInternalWorkings();
        System.out.println("Program finished.");
    }

    private void understandHashMapInternalWorkings() {
        /**This arcitle says it pretty well: https://levelup.gitconnected.com/internal-working-of-hashmap-in-java-latest-updated-4c2708f76d2c
         * 1. HashMap uses its static inner class Node<K,V> for storing map entries. That means each entry in hashMap is a Node.
         * 2. Internally HashMap uses a hashCode of the key Object and this hashCode is further used by the hash function to find the index of the bucket where the new entry can be added.
         * 3. HashMap uses multiple buckets and each bucket points to a Singly Linked List where the entries (nodes) are stored.
         * 4. Once the bucket is identified by the hash function using hashcode, then hashCode is used to check if there is already a key with the same hashCode or not in the bucket (I mean corresponding singly linked list).
         *      If there already exists a key with the same hashCode, then the equals() method is used on the keys.
         *      If the equals method returns true, that means there is already a node with the same key and hence the value against that key is overwritten in the entry (node),
         *      otherwise, a new node is created and added to this Singly Linked List of that bucket.
         *      If there is no key with the same hashCode in the bucket found by the hash function then the new Node is added to the bucket found.
         * 5. There's a threshold after which is reached, HashMap will change from using singly linked list to use a self-balancing BST, static final int TREEIFY_THRESHOLD = 8;
         *      the motive for this change is that it could take O(n) worst case for look up with linked list, however, with a self-balancing BST, e.g. red-black tree, we could get O(logn) lookup time;
         *
         * To have a high-performance hashMap we need good implementation of hashCode() and equals() method along with hash function.
         * */
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < NUMBER_OF_MAP_ENTRIES; i++) {
            map.put("key" + i, "value" + i);
        }
        map.put("key1", "value_new");
        System.out.println("this method finishes.");
    }
}
