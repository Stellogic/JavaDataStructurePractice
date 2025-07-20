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
        }
        System.out.print("null");
    }
    public




    public static void main(String[] args){
        MyLinkedList mylist = new MyLinkedList();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(5);
        mylist.print();
    }
}
