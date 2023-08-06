import java.awt.*;

class HashMap{

    class Entity{
        int key;
        int value;
    }
    class Basket{
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        int find(int key){
            Node  node = head;
            while(node != null){
                if(node.entity.key == key){
                    return  node.entity.value;// use comparator if compare objects
                }
                node = node.next;
            }

            return -1;
        }

        boolean push(Entity entity){
            Node node = new Node();
            node.entity = entity;

            if(head == null){
                head = node;
            }
            else{
                Node cur = head;
                while(cur.next != null){
                    if(cur.entity.key == entity.key){
                        return false;
                    }
                    cur = cur.next;
                }
                cur.next = node;
            }
            return true;
        }

    }
    static final int INIT_SIZE = 16;
    Basket[] baskets;

    public HashMap(){
        this(INIT_SIZE);
    }
    public HashMap(int size){
        baskets = new Basket[size];
    }

    int getIndex(int key){
        // key.hashCode();
        return key % baskets.length;
    }

    int find(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null) {
            return basket.find(key);
        }
        return -1;
    }

    boolean push(int key, int value){
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }
        return basket.push(entity);

    }

}


class BinaryTree{
    Node root;
    class Node {
        int value;
        Node left;
        Node right;
        private Color color;
    }
    boolean push(int value){
        if(root == null){
            root = new Node();
            root.value = value;
//            reb
            return true;
        }
        Node node = root;
        while(node != null){
            if(node.value == value){
                return false;
            }
            if(node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
//reb
                    return true;
                } else {
                    node = node.right;
                }
            }else{
                if(node.left ==null){
                    node.left = new Node();
                    node.left.value = value;
//                    reb
                    return true;
                }
                else{
                    node = node.left;
                }
            }
        }
        return false;

    }

    boolean find(int value){
        Node node = root;
        while(node != null){
            if(node.value == value){
                return true;
            }
            if(node.value < value) {
                node = node.right;
            }
            else {
                node = node.right;
                }
        }
        return false;

    }

}

    private boolean addNode(Node node, int value){
        if(node.value == value){
            return false;
        }
        else{
            if(node.value > value){
                if(node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                    }
                else{
                    node.left = new Node;
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            else{
                if(node.right != null){
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                }
                else{
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
                }
            }
        }
    }

    private void colorSwap(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftSwap(Node node){
        Node left = node.left;
        Node between = left.right;
        left.right = between;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightSwap(Node node){
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = color.RED;
        return right;
    }

    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do{
            needRebalance = false;
            if(result.right != null && result.right.color == color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if(result.left != null && result.left.color == Color.RED &&
                result.left.left != null && result.left.left.color == Color.RED){
                    needRebalance = true;
                    result = leftSwap(result);
            }
            if(result.left != null && result.left.color == Color.RED &&
                    result.right != null && result.right.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }
        while(needRebalance);
    }

    public boolean add(int value){
        if(root != null){
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        }
        else{
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }


public class Main{
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//        map.push(1,2);
//        map.push(3,4);
//        map.push(5,6);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));
//
//        map.push(17,8);
//
//        System.out.println(map.find(17));

        BinaryTree tree = new BinaryTree();
        tree.push(5);
        tree.push(3);
        tree.push(7);
        tree.push(2);
        tree.push(4);
        tree.push(6);
        tree.push(8);


    }

}