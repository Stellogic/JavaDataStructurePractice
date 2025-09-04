public class ArrayQueue {
    /*
    环形数组实现队列。
    front指向首元素，维护size，rear = （size + front）%nums.length
    实现capacity，size,isEmpty,push,pop,peek,toArray方法
     */
    private int[] nums;
    private int front ;
    private int queSize;
    public ArrayQueue(int capacity)
    {
        nums = new int[capacity];
        front = queSize = 0;
    }
    public int capacity()
    {
        /*
        返回整个数组长度
         */
        return nums.length;
    }
    public int size()
    {
        return queSize;
    }
    public boolean isEmpty()
    {
        return queSize == 0;
    }
    public void push(int num)
    {
        if(isEmpty())
        {
            nums[front] = num;
            queSize++;
            return;
        }
        if(capacity() == queSize)
        {
            System.out.println("wrong");
            return;
        }
        int rear = (front + queSize)%capacity();
        nums[rear] = num;
        queSize++;
        return;
    }
    public int pop()
    {
        if(isEmpty())
        {
            return -1;
        }
        int temp = nums[front];
        front = (front+1) % capacity();
        queSize--;
        return temp;
    }
    public int peek()
    {
        if(isEmpty())
        {
            return -1;
        }
        return nums[front];
    }
    public int[] toArray()
    {
        int[] res = new int[queSize];
        int curr = front;
        for (int i = 0; i < queSize; i++) {
            res[i] = nums[curr];
            curr = (curr + 1)%capacity();
        }
        return res;
    }


}
