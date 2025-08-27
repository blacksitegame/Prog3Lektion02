package bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}