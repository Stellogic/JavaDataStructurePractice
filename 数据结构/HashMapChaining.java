import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class HashMapChaining {
    /*
    实现哈希表，链式地址
    实现构造，hashFunc,loadFactor,get,put,remove,extend,print方法。
     */
    int size;
    int capacity;
    double lodaThres;
    int extendRatio;
    List<List<Pair>> buckets;

    public HashMapChaining(){
        size = 0;
        capacity = 4;
        lodaThres = 2.0/3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i< capacity ;i++)
        {
            buckets.add(new ArrayList<>());
        }
    }
    int hashFunc(int key)
    {
        return key % capacity;
    }
    double loadFactor(){
        return (double)size / capacity;
    }
    String get (int key)
    {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        for(Pair pair : bucket)
        {
            if(pair.key == key)
            {
                return pair.val;
            }
        }
        return null;
    }
    void put(int key,String val)
    {
        if(loadFactor() > lodaThres){
            extend();
        }
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        for(Pair pair : bucket)
        {
            if(pair.key == key)
            {
                pair.val = val;
                return;
            }
        }
        Pair pair = new Pair (key,val);
        bucket.add(pair);
        size++;
    }
    void remove(int key)
    {
        int index = hashFunc((key));
        List<Pair> bucket = buckets.get(index);
        for(Pair pair : bucket)
        {
            if(pair.key == key)
            {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }
    void extend()
    {
        List<List<Pair>>  bucketsTemp = buckets;
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;//创建一个新的扩容后的空哈希表，再用put填进去
        for(List<Pair> bucket : bucketsTemp)
        {
            for (Pair pair : bucket)
            {
                put(pair.key,pair.val);
            }
        }
    }
    void print(){
        for(List<Pair> bucket : buckets)
        {
            List<String> res = new ArrayList<>();
            for(Pair pair : bucket)
            {
                res.add(pair.key + "->" + pair.val);
            }
            System.out.println(res);
        }
    }




}



















class Pair {
    public int key;
    public String val;

    public Pair(int key, String val) {
        this.key = key;
        this.val = val;
    }
}
