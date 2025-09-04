public class HashMapOpenAddressing {
    /*
    包含懒删除的开放寻址（线性探测）的哈希表。
    实现构造，hashFunc ,loadFactor,findBucket,get,put,remove,extend,print
     */
    private int size;
    private int capacity = 4;
    private final double loadThres = 2.0/3.0;
    private final int extendRatio = 2;
    private Pair[] buckets;
    private final Pair TOMBSTONE = new Pair(-1,"-1");

    public HashMapOpenAddressing(){
        size = 0;
        buckets = new Pair[capacity];
    }
    private int hashFunc(int key)
    {
        return key % capacity;
    }
    private double loadFactor(){
        return (double) size / capacity;
    }
    private int findBucket(int key){
        //把key转换成对应的index
        int index = hashFunc(key);
        int firstTombstone = -1;
        while (buckets[index] != null){
            if(buckets[index].key == key)
            {
                if (firstTombstone != -1)
                {
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone;
                }

            }
            if (firstTombstone == -1 && buckets[index] == TOMBSTONE){
                firstTombstone = index;
            }
            index = (index + 1) % capacity;
        }
        //如果最终找不到，返回添加的索引
        return firstTombstone == -1 ? index : firstTombstone;
    }
    public String get (int key){
        int index = findBucket(key);
        if (buckets[index] != null && buckets[index] != TOMBSTONE){
            return buckets[index].val;
        }
        return null;
    }
    public void put (int key , String val)
    {
        if(loadFactor() > loadThres)
        {
            extend();
        }
        int index = findBucket(key);
        if(buckets[index] != null && buckets[index] != TOMBSTONE)
        {
            buckets[index].val =val;
            return;
        }
        buckets[index] = new Pair(key,val);
        size++;

    }
    public void remove(int key){
        int index = findBucket(key);
        if(buckets[index] != null && buckets[index] != TOMBSTONE){
            buckets[index] = TOMBSTONE;
            size--;
        }
    }
    private void extend(){
        Pair[] bucketsTemp = buckets;
        capacity *= extendRatio;
        buckets = new Pair[capacity];
        for(Pair pair : bucketsTemp){
            if(pair != null && pair != TOMBSTONE){
                put(pair.key,pair.val);
            }
        }
    }
    public void print(){
        for(Pair pair : buckets)
        {
            if (pair == null){
                System.out.println("null");
            } else if (pair == TOMBSTONE) {
                System.out.println("TOMBSTONE");

            } else {
                System.out.println(pair.key + "->" + pair.val);
            }
        }
    }
}
