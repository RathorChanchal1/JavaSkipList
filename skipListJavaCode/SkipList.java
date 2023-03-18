import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

    public SkipListNode head;
    public SkipListNode tail;
    public int height;
    public Randomizer randomizer;
    private final int NEG_INF = Integer.MIN_VALUE;
    private final int POS_INF = Integer.MAX_VALUE;
    private int len = 0;
    private ArrayList<SkipListNode> level = new ArrayList<>();

    SkipList() {
        /*
         * DO NOT EDIT THIS FUNCTION
         */
        this.head = new SkipListNode(NEG_INF, 1);
        this.tail = new SkipListNode(POS_INF, 1);
        this.head.next.add(0, this.tail);
        this.tail.next.add(0, null);
        this.height = 1;
        this.randomizer = new Randomizer();
    }

    public boolean delete(int num) {
        System.out.println("delete: "+num);
        if (search(num) == false) {
            return false;
        } else {
            level.clear();
            search(num);
            int j = 0;
            for (int i = level.size() - 1; i >= 0; i--) {
                if (level.get(i).next.get(j).value == num) {
                    level.get(i).next.set(j, level.get(i).next.get(j).next.get(j));
                }
                j++;
            }
            for(int i=height-1; i>=0; i--){
                if(head.next.get(i)==tail){
                    head.next.remove(i);
                    tail.next.remove(i);
                    height--;
                    head.height--;
                    tail.height--;
                }
            }
            return true;
        }
        
        // TO be completed by students

    }

    public boolean search(int num) {
        // System.out.println("search: "+num);

        boolean res = false;
        SkipListNode temp = head;
        int len = 0;
        for (int i = this.height - 1; i >= 0; i--) {

            while (num > temp.next.get(i).value) {
                temp = temp.next.get(i);

            }
            level.add(temp);
            if (num == temp.next.get(i).value) {
                res = true;
            }
            len++;
        }
        len = level.size();
        return res;
    }

    public Integer upperBound(int num) {
        // TO be completed by students
        System.out.print("upperBound " +num+" : ");

        SkipListNode temp = head;
        for (int i = this.height - 1; i >= 0; i--) {

            while (num > temp.next.get(i).value) {
                temp = temp.next.get(i);
            }
            while (num == temp.next.get(i).value) {
                temp=temp.next.get(i);
            }
            if(num<temp.next.get(0).value ){
                return temp.next.get(0).value;
            }
        }


        return Integer.MAX_VALUE;

    }

    public void insert(int num) {
        System.out.println("insert: "+num);

        SkipListNode newNode = new SkipListNode(num, 1);
        if (len == 0) {
            head.next.set(0, newNode);
            newNode.next.add(tail);
            len++;
            while (randomizer.binaryRandomGen() == true) {
                newNode.height++;
                if (newNode.height > height) {
                    head.next.add(newNode);
                    newNode.next.add(tail);
                    tail.next.add(null);
                    height++;
                    tail.height++;
                    head.height++;
                    break;
                }
                head.next.add(newNode);
                newNode.next.add(tail);
                tail.next.add(null);
                height++;
                tail.height++;
                head.height++;
            }
        } else {
            level.clear();
            // System.out.print(num);
            search(num);
            SkipListNode afterLevel = level.get(level.size() - 1).next.get(0);
            level.get(level.size() - 1).next.set(0, newNode);
            newNode.next.add(afterLevel);
            int j = 1;
            int i = level.size() - 2;

            while (randomizer.binaryRandomGen() == true) {
                if (i >= 0 && head.height >= newNode.height) {
                    // System.out.println("this");
                    SkipListNode temp2 = level.get(i).next.get(j);
                    level.get(i).next.set(j, newNode);
                    newNode.next.add(temp2);
                    j++;
                    i--;
                    newNode.height++;
                    // System.out.println(newNode.height+": newNode hieght");
                } else {
                    newNode.height++;

                    // if(head.height<newNode.height){
                    // break;

                    // }
                    head.next.add(newNode);
                    newNode.next.add(tail);
                    tail.next.add(null);
                    height++;
                    tail.height++;
                    head.height++;
                    j++;
                    i--;
                    break;
                }

            }
        }
        // TO be completed by students
    }

    public void print() {
        /*
         * DO NOT EDIT THIS FUNCTION
         */
        for (int i = this.height; i >= 1; --i) {
            SkipListNode it = this.head;
            while (it != null) {
                if (it.height >= i) {
                    System.out.print(it.value + "\t");
                } else {
                    System.out.print("\t");
                }
                it = it.next.get(0);
            }
            System.out.println("null");
        }
    }
    // public static void main(String[] args) {
    //     SkipList s1= new SkipList();
    //     // s1.insert(1);
    //     // s1.insert(1);
    //     // s1.insert(1);
    //     // s1.insert(1);




    //     s1.insert(3);
    //     s1.insert(1);
    //     s1.insert(5);
    //     s1.insert(8);
    //     System.out.println( s1.search(1));
    //     System.out.println( s1.search(10));
    //     s1.insert(1);
    //     s1.insert(1);
    //     s1.insert(1);
    //     s1.insert(1);
    //     s1.delete(5);
    //     s1.insert(4);
    //     s1.insert(5);
    //     s1.insert(4);
    //     s1.insert(3);

    //     // System.out.println(s1.upperBound(3));
    //     s1.delete(1);
    //     // s1.delete(8);
    //     System.out.println(  s1.upperBound(2));
            
    //     System.out.println(s1.upperBound(0));
    //     s1.print();
    // }
}