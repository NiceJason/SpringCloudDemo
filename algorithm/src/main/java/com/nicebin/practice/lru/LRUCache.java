package com.nicebin.practice.lru;

import java.util.LinkedHashMap;

public class LRUCache<K,V> extends LinkedHashMap<K, V> {

    //首先设定最大缓存空间 MAX_ENTRIES 为 3；
    private static final int MAX_ENTRIES = 3;

    //之后使用LinkedHashMap的构造函数将 accessOrder设置为 true，开启 LRU顺序；
    public LRUCache(boolean accessOrder) {
        super(MAX_ENTRIES, 0.75f, accessOrder);
    }

    //最后覆盖removeEldestEntry(）方法实现，在节点多于 MAX_ENTRIES 就会将最近最少使用的数据移除。
    //因为这个函数默认返回false，不重写的话缓存爆了的时候无法删除最近最久未使用的节点
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        //在容量超过最大允许节点数的时候返回true，使得在afterNodeInsertion函数中能执行removeNode()
        return size() > MAX_ENTRIES;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> accessOrderTrue = new LRUCache<>(true);

        //当accessOrder = true时，根据访问将数据移至链表尾
        System.out.println("当accessOrder=true时");
        accessOrderTrue.put(1, 1);
        accessOrderTrue.put(2, 2);
        accessOrderTrue.put(3, 3);
        System.out.println("插入完成，此时链表顺序："+accessOrderTrue.keySet());
        accessOrderTrue.get(1);
        System.out.println("访问完成，此时链表顺序："+accessOrderTrue.keySet());
        accessOrderTrue.put(4, 4);
        System.out.println("插入完成，此时链表顺序："+accessOrderTrue.keySet());

        //------------------------------------------------------------------

        LRUCache<Integer, Integer> accessOrderFalse = new LRUCache<>(false);
        //当accessOrder = false时，访问数据不影响列表排序
        System.out.println("\n当accessOrder=false时");
        accessOrderFalse.put(1, 1);
        accessOrderFalse.put(2, 2);
        accessOrderFalse.put(3, 3);
        System.out.println("插入完成，此时链表顺序："+ accessOrderFalse.keySet());
        accessOrderFalse.get(1);
        System.out.println("访问完成，此时链表顺序："+ accessOrderFalse.keySet());
        accessOrderFalse.put(4, 4);
        System.out.println("插入完成，此时链表顺序："+ accessOrderFalse.keySet());
    }
}
