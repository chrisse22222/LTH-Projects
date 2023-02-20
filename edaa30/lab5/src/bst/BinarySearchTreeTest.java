package bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree<Integer> bstInt;
    BinarySearchTree<String> bstString;
    @BeforeEach
    void setUp() {
        bstInt = new BinarySearchTree<>();
        bstString = new BinarySearchTree<>();
    }

    @AfterEach
    void tearDown() {
        bstInt = null;
        bstString = null;
    }

    @Test
    void testSize(){
        bstInt.add(10);
        bstInt.add(5);
        bstInt.add(12);
        assertEquals(bstInt.size(), 3);
    }

    @Test
    void testHeight(){
        bstInt.add(10);
        bstInt.add(5);
        bstInt.add(12);
        bstInt.add(4);
        assertEquals(bstInt.height(), 3);
    }

    @Test
    void testClear(){
        bstInt.add(10);
        bstInt.add(15);
        bstInt.clear();
        bstInt.add(1);
        assertEquals(bstInt.size(), 1);
    }

    @Test
    void testAdd(){
        assertTrue(bstInt.add(10));
        assertTrue(bstInt.add(15));
        assertTrue(bstString.add("Hej"));
        assertTrue(bstString.add("Noice"));
    }

    @Test
    void testAddDuplicate(){
        bstInt.add(10);
        bstInt.add(15);
        assertFalse(bstInt.add(15));

        bstString.add("Noob");
        assertFalse(bstString.add("Noob"));
    }

    @Test
    void testPrintTree(){
        bstInt.add(10);
        bstInt.add(5);
        bstInt.add(6);
        bstInt.add(15);
        bstInt.printTree();

        bstString.add("Christoffer");
        bstString.add("Jonas");
        bstString.add("Pontus");
        bstString.printTree();
    }
}