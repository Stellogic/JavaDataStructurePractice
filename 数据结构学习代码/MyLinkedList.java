public class MyLinkedList {
    private ListNode head;
    public void add(int val)
    {
        ListNode newNode = new ListNode(val);
        if (head == null){
            head = newNode;
            return;
        }
        ListNode current = head ;
        while (current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }

    public void print(){
        ListNode current = head;
        while (current != null){
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.print("null");
    }
    public int get(int index){
        if(index < 0){
            System.out.println("index不能小于零");
            return -1;
        }
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            if(current == null)
            {
                System.out.println("索引越界");
                return -1;
            }
            current = current.next;
        }
        if(current != null){
            return current.val;
        }
        else {
            System.out.println("索引越界");
            return -1;
        }
    }

    public void remove(int index){
        if (index < 0) {
            System.out.println("错误：索引不能为负数");
            return;
        }

        if (index == 0) {
            if (head != null) {
                head = head.next;
                System.out.println("成功删除头节点");
            } else {
                System.out.println("错误：链表为空，无法删除");
            }
            return;
        }

        ListNode prev = head;
        for (int i = 0; i < index - 1; i++) {
            if (prev == null || prev.next == null) {
                System.out.println("错误：索引越界");
                return;
            }
            prev = prev.next;
        }
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next; // 让前一个节点“跳过”要删除的节点
            System.out.println("成功删除索引为 " + index + " 的节点");
        } else {
            // 这种情况发生在 index 恰好是链表长度时
            System.out.println("错误：索引越界");
        }

    }




    public static void main(String[] args){
        MyLinkedList mylist = new MyLinkedList();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(5);
        mylist.print();
    }
}
