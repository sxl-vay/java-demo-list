package suanfa;

import java.util.HashMap;

/**
 * @author shxl
 * 一个插入删除个获取随机值时间复杂度都是O(1)的容器Pool
 * 核心思想：维护两个哈希表,key->index ；index->key
 * key->index表可以确保插入和删除的时间复杂度是O(1)
 * 两个hash表一起确保获取随机值的时间复杂度是O(1)
 * 当删除一个key时，从index->可以hash表中获取最大index的key,
 * 将它填补到被删除的key的位置，确保index->key的哈希表维护一个index完整的hash表，可以根据生成的随机数在池中以O(1)获取到一个key；
 */
public class Pool {
    private HashMap<String,Integer> keyMap;
    private HashMap<Integer,String> indexMap;
    private Integer size;

    public Pool() {
        this.keyMap = new HashMap<>();
        this.indexMap = new HashMap<>();
        this.size = 0;
    }

    public Integer insert(String key){
        if (keyMap.containsKey(key))
            return -1;
        this.keyMap.put(key,this.size);
        this.indexMap.put(++this.size,key);
        return this.size;
    }

    public Integer delete(String key){

        if (!this.keyMap.containsKey(key))
            return -1;
        //删除keyMap中的键值对，并获取被删除的index
        Integer removeIndex = this.keyMap.remove(key);
        //获取到indexMap最大index的key
        String maxIndexKey = this.indexMap.get(this.size);
        //将keyMap中最大index的key转换到被删除的index位置（分为两步进行操作）
        this.keyMap.put(maxIndexKey,removeIndex);
        this.indexMap.put(removeIndex, maxIndexKey);
        //最后删除indexMap最大index的键值对，并且将size减一然后返回
        this.indexMap.remove(this.size--);
        return this.size;
    }

    public String getRandom(){
        if (this.size == 0)
            return null;
        return this.indexMap.get((int)(Math.random()*this.size)+1);
    }

    public static void main(String[] args) {
        double random = Math.random();
        double v = random * 49;

        System.out.println("random = " + v);

    }

}
