public class LinkedListStack {
    /*
    用链表实现栈。实现getStkSize,isEmpty,push,pop,peek,toArray方法
    头节点视为栈顶
     */
    private ListNode stackPeek;
    private int stkSize = 0;
    public LinkedListStack()
    {
        stackPeek = null;
    }
    public int getStkSize()
    {
        return stkSize;
    }
    public boolean isEmpty ()
    {
        return stkSize == 0;
    }
    public void push(int val)
    {
        ListNode node = new ListNode(val);
        node.next = stackPeek;
        stackPeek = node;
        stkSize++;
    }
    public int pop ()
    {
        if(isEmpty())
        {
            return -1;
        }
        else{
            int val = stackPeek.val;
            stackPeek = stackPeek.next;
            stkSize--;
            return val;
        }
    }
    public int peek()
    {
        if(isEmpty())
        {
            return -1;
        }
        return stackPeek.val;
    }
    public int[] toArray()
    {
        int[] res = new int[stkSize];
        ListNode curr = stackPeek;
        for (int i = 0; i < stkSize; i++) {
            res[i] = curr.val;
            curr = curr.next;
        }
        return  res;
    }
}
