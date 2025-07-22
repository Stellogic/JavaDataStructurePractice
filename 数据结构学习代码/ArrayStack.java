import java.util.ArrayList;

public class ArrayStack {
    //用数组实现栈，实现size,isEmpty,push,pop,peek,toarray,
    // 尾部作为栈顶
    private ArrayList<Integer> stack;
    public ArrayStack()
    {
        stack = new ArrayList<>();
    }
    public int size()
    {
        return stack.size();
    }
    public boolean isEmpty()
    {
        return stack.size() == 0;
    }
    public void push(int num)
    {
        stack.add(num);
    }
    public int pop()
    {
        if(isEmpty())
        {
            return -1;
        }
        return stack.remove(size() - 1);
    }
    public int peek()
    {
        if(isEmpty())
        {
            return  -1;
        }
        return stack.get(size() - 1);
    }
    public Object[] toArray(){
        return stack.toArray();
    }

}
//除法扩容机制时时间复杂度变成O(N)，链表的实现更稳定