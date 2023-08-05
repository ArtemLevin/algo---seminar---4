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

public class Main{
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.push(1,2);
        map.push(3,4);
        map.push(5,6);

        System.out.println(map.find(3));
        System.out.println(map.find(2));

        map.push(17,8);

        System.out.println(map.find(17));
    }
}