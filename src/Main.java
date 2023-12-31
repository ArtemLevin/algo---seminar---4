
class BinaryTree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;
        private Color color;

        @Override
        public String toString() {
            if (value == root.value) {
                return '\n'+ "<Root> value is " + value +
                        ", root color is " + color;
            }
            else if (color == null){
                return '\n'+ "There is no node with value = " + value + " in tree";
            }
            else {
                return '\n'+ "<Node> value is " + value +
                        ", node color is " + color;
            }

        }
    }

    private enum Color {
        RED, BLACK
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.left != null) {
                    boolean result = addNode(node.left, value);
                    node.left = treeRebalancing(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(node.right, value);
                    node.right = treeRebalancing(node.right);
                    return result;
                } else {
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }

            }
        }
    }

    Node find(int value) {
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                return node;
            }
            if (node.value < value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        Node nullNode = new Node();
        nullNode.value = value;

        return nullNode;

    }


    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        Node temp = left.right;
        left.right = node;
        node.left = temp;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightSwap(Node node) {
        Node right = node.right;
        Node temp = right.left;
        right.left = node;
        node.right = temp;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    private Node treeRebalancing(Node node) {
        Node result = node;
        boolean needForRebalancing;
        do {
            needForRebalancing = false;
            if (result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)) {
                needForRebalancing = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    result.left.left != null && result.left.left.color == Color.RED) {
                needForRebalancing = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    result.right != null && result.right.color == Color.RED) {
                needForRebalancing = true;
                colorSwap(result);
            }
        }
        while (needForRebalancing);
        return result;
    }

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = treeRebalancing(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }
}

public class Main {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(10);
        tree.add(15);
        tree.add(100499);

        System.out.println(tree.find(3));
        System.out.println(tree.find(7));
        System.out.println(tree.find(2));
        System.out.println(tree.find(5));
        System.out.println(tree.find(4));
        System.out.println(tree.find(6));
        System.out.println(tree.find(8));
        System.out.println(tree.find(10));
        System.out.println(tree.find(15));
        System.out.println(tree.find(100499));
        System.out.println(tree.find(100500));
    }

}

