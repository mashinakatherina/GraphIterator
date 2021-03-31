package Iterator;

import java.util.*;

class Node {
    public int value; // unique
    public Collection<Node> siblings; // collection of nodes such that for each sibling in collection edge between this node and sibling exists

    public Node(int value, Collection<Node> siblings) {
        this.value = value;
        this.siblings = siblings;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}


class NodeIterator implements Iterator<Node> {
    Node node;
    private final Stack<Node> stack = new Stack();
    private final List<Node> visited = new ArrayList();


    public NodeIterator(Node start) {
        this.node = start;
        this.stack.push(start);
        this.visited.add(start);
    }

    @Override
    public String toString() {
        return String.valueOf(node.value);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node next() {
        Node current = stack.pop();
        Collection<Node> siblings = current.siblings;
        for (Node iterNode : siblings) {
            if (!visited.contains(iterNode)) {
                stack.push(iterNode);
                visited.add(iterNode);
            }
        }
        return current;
    }


    public static void main(String[] args) {
        Node start = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Collections.singleton(
                        new Node(4, Collections.emptyList()))
                )
        ));

        NodeIterator iterator = new NodeIterator(start);
        // should print numbers 1-4 in any order
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
