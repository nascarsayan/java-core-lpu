package collections;

class Node {
    int val;
    Node next, prev;

    Node(int _val, Node prev) {
        val = _val;
        this.prev = prev;
    }

}

public class LinkedList {
    public static void main(String[] args) {
        var head = new Node(10, null);
        head.next = new Node(20, head);
        var last = head.next.next = new Node(30, head.next);

        var curr = last;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.prev;
        }

    }
}
