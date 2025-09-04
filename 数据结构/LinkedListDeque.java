public class LinkedListDeque {
    /*
    实现双向队列的size,isEmpty,push,pushFirst,pushLast,
    pop,popFirst,popLast,peekFirst,peekLast,toArray.
     */
    private static class ListNode
    {
        /*双向节点*/
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int val)
        {
            this.val = val;
            prev = next = null;
        }
    }

    private ListNode front,rear;
    private int queSize = 0;
    public LinkedListDeque()
    {
        front = rear = null;
    }
    public int size(){
        return queSize;
    }
    public boolean isEmpty()
    {
        return queSize == 0;
    }
    public void push(int num ,boolean isFirst)
    {
        if (isFirst)
        {
            ListNode node = new ListNode(num);
            if(front == null)
            {
                front = rear = node;
                queSize++;
            }
            else{
                front.prev = node;
                node.next = front;
                front = node;
                queSize++;
            }
        }
        else{
            ListNode node = new ListNode(num);
            if (isEmpty())
            {
                front = rear = node;
                queSize++;
            }
            else {
                rear.next = node;
                node.prev = rear;
                rear = node;
                queSize++;
            }
        }
    }
    public void pushFirst(int num)
    {
        push(num,true);
    }
    public void pushLast(int num)
    {
        push(num,false);
    }
    public int pop(boolean isFirst){
        if(isEmpty())
        {
            return -1;
        }
        if (isFirst)
        {
            int temp = front.val;
            front = front.next;
            if(front == null)
            {
                rear = null;
                queSize--;
                return temp;
            }
            front.prev = null;
            queSize--;
            return temp;
        }
        else {
            int temp = rear.val;
            rear = rear.prev;
            if (rear == null)
            {
                front = null;
                queSize--;
                return temp;
            }
            rear.next = null;
            queSize--;
            return temp;
        }
    }
    public int popFirst()
    {
        return pop(true);
    }
    public int popLast()
    {
        return pop(false);
    }
    public int peekFirst()
    {
        if(isEmpty())
        {
            return -1;
        }
        return front.val;
    }
    public int peekLast(){
        if(isEmpty())
        {
            return -1;
        }
        return rear.val;
    }

    public int[] toArray()
    {
        int[] res = new int[queSize];
        ListNode curr = front;
        for (int i = 0; i < queSize; i++) {
            res[i] = curr.val;
            curr = curr.next;
        }
        return res;
    }
}


