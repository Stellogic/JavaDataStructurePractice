# 哈希表核心知识点复习笔记

## 1. 基础概念

*   **定义**：哈希表（Hash Table / Hash Map）是一种通过**哈希函数**将键（Key）映射到数组索引，从而实现快速访问值（Value）的数据结构。
*   **目标**：实现平均时间复杂度为 **O(1)** 的插入、删除、查找操作。
*   **核心组件**:
    1.  **桶数组 (Bucket Array)**：哈希表底层的数组，用于存储数据。
    2.  **哈希函数 (Hash Function)**：负责将 Key 转换为数组索引的函数。关键性质是**确定性**和**均匀性**。
    3.  **负载因子 (Load Factor)**：衡量哈希表填充程度的指标，用于决定何时进行扩容。
        *   `负载因子 = 已存储元素数量 / 桶数组总容量`

## 2. 核心问题：哈希冲突

**定义**：两个不同的 Key 经过哈希函数计算后得到了相同的数组索引。冲突是必然发生的，解决方案是哈希表设计的核心。

### 解决方案 1：链式地址法 (Separate Chaining)

*   **原理**：桶数组的每个位置不直接存储元素，而是存储一个数据结构（通常是链表）的头节点。所有哈希到同一索引的元素，都加入到该位置的链表中。
*   **操作流程**：
    *   **插入(Put)**：计算索引 -> 找到对应链表 -> 遍历链表。若 Key 已存在则更新 Value；若不存在则将新键值对添加到链表尾部。
    *   **查找(Get)**：计算索引 -> 找到对应链表 -> 遍历链表查找 Key。
    *   **删除(Remove)**：计算索引 -> 找到对应链表 -> 遍历链表找到并移除对应节点。
*   **关键优化：红黑树化**
    *   当链表长度超过某一阈值（Java 8 中为 8），链表会转换为红黑树。
    *   **目的**：防止极端情况下（大量冲突）查询性能从 O(1) 退化到 O(N)，红黑树能保证 O(logN) 的复杂度。
*   **特点**：
    *   实现相对简单，删除操作直观。
    *   负载因子可以大于 1。
    *   需要额外空间存储链表节点/指针，对 CPU 缓存不友好。

### 解决方案 2：开放寻址法 (Open Addressing)

*   **原理**：所有元素都存储在桶数组内。当发生冲突时，通过一个**探测序列**在数组中寻找下一个可用的空位。
*   **关键问题：懒删除 (Lazy Deletion)**
    *   不能直接删除元素（置为 `null`），否则会中断探测链，导致后续元素无法找到。
    *   **解决方案**：使用一个特殊的**墓碑 (TOMBSTONE)** 对象来标记被删除的位置。查找时遇到墓碑需继续探测，插入时可复用墓碑位置。
*   **探测方法**:
    1.  **线性探测 (Linear Probing)**：`index = (index + 1) % capacity`。最简单，但易产生**聚集 (Clustering)** 问题，导致性能下降。
    2.  **二次探测 (Quadratic Probing)**：`index = (index + i*i) % capacity`。可缓解主聚集。
    3.  **双重哈希 (Double Hashing)**：`index = (hash1(key) + i * hash2(key)) % capacity`。使用第二个哈希函数计算步长，是效果最好的探测方法之一。
*   **特点**:
    *   空间利用率高，CPU 缓存友好。
    *   负载因子必须小于 1。
    *   实现复杂，删除操作麻烦，对哈希函数质量要求高。

## 3. 动态扩容 (Rehashing)

*   **触发时机**：当 `负载因子` 超过设定的阈值（如 0.75）。
*   **目的**：降低负载因子，减少冲突，维持 O(1) 的性能。
*   **核心过程**：
    1.  创建一个容量通常为原来 2 倍的新桶数组。
    2.  遍历**旧数组**中的**每一个**元素。
    3.  **必须重新计算**每个元素在新容量下的哈希索引 (`key % new_capacity`)。
    4.  将元素放入新数组的对应位置。
*   **成本**：扩容是一个 O(N) 的操作，其中 N 是哈希表中的元素数量。会造成瞬时的性能抖动。

## 4. 两种方案对比

| 特性 | 链式地址法 (Separate Chaining) | 开放寻址法 (Open Addressing) |
| :--- | :--- | :--- |
| **空间效率** | 较低（有额外指针开销） | 较高（无额外结构） |
| **负载因子** | 可 > 1 | 必须 < 1 |
| **删除实现** | 简单，直接移除节点 | 复杂，需要懒删除机制 |
| **性能瓶颈** | 链表过长（可通过红黑树优化） | 元素聚集（可通过改进探测方法缓解） |
| **缓存友好性**| 差 | 好 |

---

## 5. 完整代码实现

### 实现 1：HashMapChaining.java (链式地址法)

```java
import java.util.ArrayList;
import java.util.List;

// 辅助类：键值对
class Pair {
    public int key;
    public String val;

    public Pair(int key, String val) {
        this.key = key;
        this.val = val;
    }
}

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

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        lodaThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    int hashFunc(int key) {
        return key % capacity;
    }

    double loadFactor() {
        return (double) size / capacity;
    }

    String get(int key) {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                return pair.val;
            }
        }
        return null;
    }

    void put(int key, String val) {
        if (loadFactor() > lodaThres) {
            extend();
        }
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                pair.val = val;
                return;
            }
        }
        Pair pair = new Pair(key, val);
        bucket.add(pair);
        size++;
    }

    void remove(int key) {
        int index = hashFunc((key));
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }

    void extend() {
        List<List<Pair>> bucketsTemp = buckets;
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0; // 重置size，因为put操作会重新增加它
        for (List<Pair> bucket : bucketsTemp) {
            for (Pair pair : bucket) {
                put(pair.key, pair.val);
            }
        }
    }

    void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + "->" + pair.val);
            }
            System.out.println(res);
        }
    }
}
```

### 实现 2：HashMapOpenAddressing.java (开放寻址法)

```java
// Pair 类复用上面的定义

public class HashMapOpenAddressing {
    /*
    包含懒删除的开放寻址（线性探测）的哈希表。
    实现构造，hashFunc ,loadFactor,findBucket,get,put,remove,extend,print
     */
    private int size;
    private int capacity = 4;
    private final double loadThres = 2.0 / 3.0;
    private final int extendRatio = 2;
    private Pair[] buckets;
    private final Pair TOMBSTONE = new Pair(-1, "-1"); // 墓碑标记

    public HashMapOpenAddressing() {
        size = 0;
        buckets = new Pair[capacity];
    }

    private int hashFunc(int key) {
        return key % capacity;
    }

    private double loadFactor() {
        return (double) size / capacity;
    }

    private int findBucket(int key) {
        int index = hashFunc(key);
        int firstTombstone = -1;
        // 循环探测
        while (buckets[index] != null) {
            // 如果找到匹配的key，直接返回
            if (buckets[index].key == key) {
                // 如果之前遇到了墓碑，做交换优化，让元素更靠近初始位置
                if (firstTombstone != -1) {
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone;
                }
                return index; // 直接返回当前位置
            }
            // 记录遇到的第一个墓碑
            if (firstTombstone == -1 && buckets[index] == TOMBSTONE) {
                firstTombstone = index;
            }
            // 线性探测，移动到下一个位置
            index = (index + 1) % capacity;
        }
        // 循环结束，表示未找到key。
        // 如果曾遇到墓碑，返回第一个墓碑的位置（用于插入）；否则返回null的位置。
        return firstTombstone == -1 ? index : firstTombstone;
    }

    public String get(int key) {
        int index = findBucket(key);
        // 如果找到的位置不为null且不是墓碑，说明找到了
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            return buckets[index].val;
        }
        return null;
    }

    public void put(int key, String val) {
        if (loadFactor() > loadThres) {
            extend();
        }
        int index = findBucket(key);
        // 如果该位置已有元素（不是null或墓碑），说明是更新操作
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index].val = val;
            return;
        }
        // 否则是插入新元素
        buckets[index] = new Pair(key, val);
        size++;
    }

    public void remove(int key) {
        int index = findBucket(key);
        // 确保该位置有元素再删除
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index] = TOMBSTONE;
            size--;
        }
    }

    private void extend() {
        Pair[] bucketsTemp = buckets;
        capacity *= extendRatio;
        buckets = new Pair[capacity];
        size = 0; // 重置size，因为put会重新计算
        // 遍历旧数组，将非null且非墓碑的元素重新插入
        for (Pair pair : bucketsTemp) {
            if (pair != null && pair != TOMBSTONE) {
                put(pair.key, pair.val);
            }
        }
    }

    public void print() {
        for (Pair pair : buckets) {
            if (pair == null) {
                System.out.println("null");
            } else if (pair == TOMBSTONE) {
                System.out.println("TOMBSTONE");
            } else {
                System.out.println(pair.key + "->" + pair.val);
            }
        }
    }
}
```