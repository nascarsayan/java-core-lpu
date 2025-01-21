package collections;

public class BinaryTree {

    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        var node2 = new Node(-1);
        var node3 = new Node(5);
        var node1 = new Node(1, node2, node3);

//        node1.left = node2;
//        node1.right = node3;

        var root = node1;

    }
}
