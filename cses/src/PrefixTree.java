import java.util.ArrayList;

public class PrefixTree {
    static class Node {
        char ch;
        ArrayList<Node> children;
        boolean ends;
        // count of number of elements which start with the given prefix.
        int count;

        Node(char ch) {
            this.ch = ch;
            children = new ArrayList<>();
            count = 1;
        }
    }

    public static void main(String[] args) {
        var start = new Node('@');
        String[] arr = {
                "abcd",
                "abc",
                "abxy",
                "abdf",
                "ab",
                "acd"
        };

        for (var el: arr) {
            var curr = start;
            for (var ch: el.toCharArray()) {
                // check if curr contains ch or not.
                Node next = null;
                for (var child: curr.children) {
                    if (child.ch == ch) {
                        next = child;
                        child.count++;
                    }
                }
                // if it does not, then add it.
                if (next == null) {
                    next = new Node(ch);
                    curr.children.add(next);
                }
                curr = next;
                if (curr.ch == 'b') {
                    System.out.println(curr.count);
                    System.out.println(el);
                }
            }
            curr.ends = true;
        }

        // How many strings start with ab; = 5
        var target = "ab";
        var res = 0;
        var curr = start;
        for (var ch: target.toCharArray()) {
            Node next = null;
            for (var child: curr.children) {
                if (child.ch == ch) next = child;
            }
            if (next == null) {
                curr = null;
                break;
            }
            curr = next;
        }
        if (curr == null) System.out.println(0);
        else System.out.println(curr.count);
    }
}
