package bst;

import java.util.Random;

public class Main {
    public static void main (String [] args){
        BinarySearchTree<Integer> d = new BinarySearchTree<>();
        Random random = new Random(420);
        for (int i = 0; i < 100; i++) {
            d.add(random.nextInt(400));
        }

        d.rebuild(); // Balanserar trädet
        BSTVisualizer bstVis = new BSTVisualizer("Binary Search Tree", 800, 600);
        bstVis.drawTree(d);

        d.printTree();
        System.out.println("Height: " + d.height());
    }
}
