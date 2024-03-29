package org.unallied.mmocraft.tools;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class CollectionTools {
    
    /**
     * Retrieves a sorted set from an unsorted map by values.
     * @param map The unsorted map.
     * @return sortedSet
     */
    public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1; // Special fix to preserve items with equal values
                }
            }
        );
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sortedEntries.add(entry);
        }
        return sortedEntries;
    }
}
