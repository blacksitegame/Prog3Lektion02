package bst;

import javax.swing.text.html.parser.Element;
import java.util.ArrayList;
import java.util.Comparator;

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected Comparator<E> c;

    /**
     * Create a default BST with a natural order comparator
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a BST with a specified comparator
     */
    public BST(Comparator<E> c) {
        this.c = c;
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                found = true; // Element is found
        }

        return found;
    }

    @Override
    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        boolean inserted = true;
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null && inserted)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    inserted = false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return inserted; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    public boolean isLeaf(TreeNode<E> treeNode){
        return treeNode.left == null && treeNode.right == null;
    }

    public boolean isInternal(TreeNode<E> treeNode){
        return treeNode.left != null || treeNode.right != null;
    }

    public int height(){
        ArrayList<E> arrayList = new ArrayList<>();
        int height = 0;
        int temp = 0;
        TreeNode<E> current = root;
        while (current != null){
            if (current.left == null && current.right == null && !arrayList.contains(current.element)){
                arrayList.add(current.element);
                delete(current.element);
                temp++;
                if (temp>height){
                    height = temp;
                }
                temp = 0;
                if (current == root){
                    current = null;
                }

            } else if (current.left != null){
                current = current.left;
                temp++;
            } else {
                current = current.right;
                temp++;
            }
        }
        return height;
    }

    @Override
    /** Inorder traversal from the root */
    public ArrayList<E> inorder() {
        ArrayList<E> arrayList = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            if (current.left == null && current.right == null && !arrayList.contains(current.element)){
                arrayList.add(current.element);
                delete(current.element);
                if (current == root){
                    current = null;
                }

            } else if (current.left != null){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return arrayList;
    }

    public int sum() {
        return sumHelper(root);
    }

    public int findMax() {
        int max = 0;
        TreeNode<E> current = root;
        while (current != null){
            if (current.right != null){
                current = current.right;
            } else {
                max = (Integer) current.element;
                current=null;
            }
        }

        return max;
    }

    public int findMin(){
        int min = 0;
        TreeNode<E> current = root;
        while (current != null){
            if (current.left != null){
                current = current.left;
            } else {
                min = (Integer) current.element;
                current=null;
            }
        }

        return min;
    }



    private int sumHelper(TreeNode<E> node) {
        if (node == null) return 0;
        return (Integer) node.element + sumHelper(node.left) + sumHelper(node.right);
    }

    @Override
    /** Postorder traversal from the root */
    public ArrayList<E> postorder() {
        ArrayList<E> arrayList = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            if (current.left == null && current.right == null && !arrayList.contains(current.element)){
                arrayList.add(current.element);
                delete(current.element);
                if (current == root){
                    current = null;
                }

            } else if (current.left != null){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return arrayList;
    }



    @Override
    /** Preorder traversal from the root */
    public ArrayList<E> preorder() {
        ArrayList<E> arrayList = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
                if (!arrayList.contains(current.element)){
                    arrayList.add(current.element);
                }
                if (current.left == null && current.right == null){
                    delete(current.element);
                    current = root;
                } else if (current.left != null){
                    current = current.left;
                } else {
                    current = current.right;
                }
        }
        return arrayList;
    }


    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }


    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                found = true; // Element is in the tree pointed at by current
        }

        if (found) {
            // Case 1: current has no left child
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (c.compare(e, parent.element) < 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            } else {
                // Case 2: The current node has a left child
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
                }

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;

                // Eliminate rightmost node
                if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
                else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }
            size--; // Reduce the size of the tree
        }
        return found; // Element deleted successfully
    }

    public E removeMin(){
        if (root == null) return null;

        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current.left != null){
            parent = current;
            current = current.left;
        }

        E result = current.element;

        if (parent == null) {
            root = current.right;
        } else {
            parent.left = current.right;
        }

        size--;
        return result;
    }


    public E removeMax(){
        TreeNode<E> current = root;
        E result;
        while (current.right != null){
            current = current.right;

        }
        result = current.element;
        current.element = null;
        return result;
    }

    public ArrayList<E> greaterThan(E element){
        ArrayList<E> result = new ArrayList<>();
        greaterThanHelper(root, element, result);
        return result;
    }

    private void greaterThanHelper(TreeNode<E> node, E element, ArrayList<E> result) {
        if (node == null) return;

        if (c.compare(node.element, element) > 0) {
            result.add(node.element);
        }

        greaterThanHelper(node.left, element, result);
        greaterThanHelper(node.right, element, result);
    }

    public int numberOfLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(TreeNode<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int heightNodeCount(int targetHeight) {
        return countNodesAtHeight(root, 0, targetHeight);
    }

    private int countNodesAtHeight(TreeNode<E> node, int currentHeight, int targetHeight) {
        if (node == null) return 0;
        if (currentHeight == targetHeight) return 1;
        return countNodesAtHeight(node.left, currentHeight + 1, targetHeight) +
               countNodesAtHeight(node.right, currentHeight + 1, targetHeight);
    }
//
    //-------------------------------------------------------------------



}
