package bst;

public class Main {
    public static void main(String[] args) {
        BST<Integer> integerBST = new BST<>();

        integerBST.insert(45);
        integerBST.insert(22);
        integerBST.insert(30);
        integerBST.insert(25);
        integerBST.insert(11);
        integerBST.insert(15);
        integerBST.insert(77);
        integerBST.insert(90);
        integerBST.insert(88);

        //Preorder: 45, 22, 11, 15, 30, 25, 77, 90, 88
        //Inorder: 15, 11, 22, 25, 30, 45, 88, 90, 77
        //postOrder: 15, 11, 25, 30, 22, 88, 90, 77, 45


    }
}
