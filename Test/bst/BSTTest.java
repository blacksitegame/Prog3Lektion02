package bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    private BST<Integer> integerBST = new BST<>();;

    @BeforeEach
    void setUp() {
        integerBST.insert(45);
        integerBST.insert(22);
        integerBST.insert(11);
        integerBST.insert(15);
        integerBST.insert(30);
        integerBST.insert(25);
        integerBST.insert(77);
        integerBST.insert(90);
        integerBST.insert(88);

        //Preorder: 45, 22, 11, 15, 30, 25, 77, 90, 88
        //Inorder: 15, 11, 22, 25, 30, 45, 88, 90, 77
        //postOrder: 15, 11, 25, 30, 22, 88, 90, 77, 45

    }

    @Test
    void preOrderTest() {
        //Arange
        ArrayList<Integer> expected = new ArrayList<>(List.of(
                45, 22, 11, 15, 30, 25, 77, 90, 88
        ));

        //Act
        ArrayList<Integer> actual = integerBST.preorder();

        //Assert
        for (int i = 0; i < actual.size(); i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    void inOrderTest() {
        //Arange
        ArrayList<Integer> expected = new ArrayList<>(List.of(
                15, 11, 22, 25, 30, 45, 88, 90, 77
        ));

        //Act
        ArrayList<Integer> actual = integerBST.inorder();

        //Assert
        for (int i = 0; i < actual.size(); i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    void postOrderTest() {
        //Arange
        ArrayList<Integer> expected = new ArrayList<>(List.of(
                15, 11, 25, 30, 22, 88, 90, 77, 45
        ));

        //Act
        ArrayList<Integer> actual = integerBST.postorder();

        //Assert
        for (int i = 0; i < actual.size(); i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    void isLeaf(){
        //Arrange
        BST.TreeNode<Integer> fortyFive = integerBST.root;
        BST.TreeNode<Integer> twentyTwo = fortyFive.left;
        BST.TreeNode<Integer> fifteen = fortyFive.left.left.right;

        boolean expectedfortyFive = integerBST.isLeaf(fortyFive);
        boolean expectedtwentyTwo = integerBST.isLeaf(twentyTwo);
        boolean expectedfifteen = integerBST.isLeaf(fifteen);
        //Act
        boolean ActualfortyFive = false;
        boolean ActualtwentyTwo = false;
        boolean Actualfifteen = true;
        //Assert
        assertEquals(expectedfortyFive,ActualfortyFive);
        assertEquals(expectedfifteen,Actualfifteen);
        assertEquals(expectedtwentyTwo,ActualtwentyTwo);
    }


    @Test
    void isInternal(){
        //Arrange
        BST.TreeNode<Integer> fortyFive = integerBST.root;
        BST.TreeNode<Integer> twentyTwo = fortyFive.left;
        BST.TreeNode<Integer> fifteen = fortyFive.left.left.right;

        boolean expectedfortyFive = integerBST.isInternal(fortyFive);
        boolean expectedtwentyTwo = integerBST.isInternal(twentyTwo);
        boolean expectedfifteen = integerBST.isInternal(fifteen);
        //Act
        boolean ActualfortyFive = true;
        boolean ActualtwentyTwo = true;
        boolean Actualfifteen = false;
        //Assert
        assertEquals(expectedfortyFive,ActualfortyFive);
        assertEquals(expectedfifteen,Actualfifteen);
        assertEquals(expectedtwentyTwo,ActualtwentyTwo);
    }

    @Test
    void height(){
        //Arrange
        int actual = integerBST.height();
        //Act
        int expected = 4;
        int falseExpected = 5;
        //Assert
        assertEquals(expected,actual);
        assertNotEquals(falseExpected,actual);
    }

    @Test
    void Sum() {
        //Arrange
        int actual = integerBST.sum();
        //Act
        int expected = 45 + 22 + 11 + 15 + 30 + 25 + 77 + 90 + 88;
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void findMax() {
        //Arrange
        int actual = integerBST.findMax();
        //Act
        int expected = 90;
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void findMin() {
        //Arrange
        int actual = integerBST.findMin();
        //Act
        int expected = 11;
        //Assert
        assertEquals(expected,actual);
    }
}