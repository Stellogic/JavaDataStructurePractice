public class ArrayDeque {
    /*
    环形数组实现
    capacity,size,isEmpty,index,pushFirst,pushLast,popFirst
    popLast,peekFirst,peekLast,toArray方法
     */
    private int[] nums;
    private int front;
    private int queSize;
    public ArrayDeque(int capacity)
    {
        this.nums = new int[capacity];
        front = 0;
        queSize = 0;
    }
    public int capacity()
    {
        return nums.length;
    }
    public boolean isEmpty()
    {
        return queSize == 0;
    }
    public int index(int i)
    {
        /*
        为什么不直接 i % capacity()？
        因为在Java中，负数取余的结果还是负数或0（例如 -1 % 10 的结果是 -1）
        这仍然是无效索引。
         */
        return (i + capacity())%capacity();
    }
    public void pushFirst(int num)
    {
        if(queSize == capacity())
        {
            return;
        }
        front = index(front-1);
        nums[front] = num;
        queSize++;
    }
    public void pushLast(int num)
    {
        if(queSize == capacity())
        {
            return;
        }
        int rear =index (front + queSize) ;
        nums[rear] = num;
        queSize++;
    }
    public int popFirst()
    {
        if(isEmpty())
        {
            return -1;
        }
        int temp = nums[front];
        front = index(front+1);
        queSize--;
        return temp;
    }
    public int popLast()
    {
        if(isEmpty())
        {
            return -1;
        }
        int temp = peekLast();
        queSize--;
        return temp;
    }
    public int peekFirst()
    {
        if(isEmpty())
        {
            return -1;
        }
        return nums[front];
    }
    public int peekLast()
    {
        if(isEmpty())
        {
            return -1;
        }
        int last = index(front + queSize -1);
        return nums[last];
    }
    public int[] toArray()
    {
        int[] res = new int[queSize];
        int curr = front;
        for (int i = 0; i < queSize; i++) {
            res[i] = nums[curr];
            curr = index(curr + 1);
        }
        return res;
    }
}
