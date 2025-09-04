public class LinkedListQueue {
    /*
    头节点队首，尾节点队尾。实现size,isEmpty,push,pop,peek,toArray
     */
    private ListNode front,rear;
    private int queSize = 0;
    public LinkedListQueue()
    {
        front = null;
        rear = null;
    }
    public int size()
    {
        return queSize;
    }
    public boolean isEmpty()
    {
        return queSize == 0;
    }
    public void push(int val)//入队到队尾
    {
        ListNode node = new ListNode(val);
        if(front == null)
        {
            front = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
        queSize++;
    }
    public int pop()
    {
        if(isEmpty())
        {
            return -1;
        }
        int val = front.val;
        ListNode Second = front.next;
        front = Second;
        if(front == null)
        {
            rear = null;
        }
        queSize--;
        return val;
    }
    public int peek()
    {
        if(isEmpty())
        {
            return -1;
        }
        return front.val;
    }
    public int[] toArray()
    {
        ListNode curr = front;
        int[] res = new int[queSize];
        for (int i = 0; i < queSize; i++) {
            res[i] = curr.val;
            curr = curr.next;
        }
        return res;
    }
}
