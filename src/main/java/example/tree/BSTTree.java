package example.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyu
 * @date 2019-03-04
 */
public class BSTTree<E extends Comparable<E>> {
    class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node root;
    private int size;

    public BSTTree() {
        root = null;
    }

    public void addNode(E e) {
        if (root == null) {
            root = new Node(e);
            return;
        }

        addNode(root, e);

    }

    private Node addNode(Node node, E e) {

        if (node == null) {
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = addNode(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = addNode(node.right, e);
        } else {
            node.e = e;
        }
        return node;
    }

    public void inOrder(Node node, List<E> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.e);
        inOrder(node.right, keys);
    }

    /**
     *          y                       x              过程：
     *         / \                    /   \             1. Node x = y.left;
     *        x   T4    右旋转        z     y            2. Node T3 = x.right;
     *       / \      =========>    / \   /  \          3. x.right = y;
     *      z   T3                 T1 T2 T3  T4         4. y.left = T3;
     *     / \
     *    T1  T2
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;
        return x;
    }

    public static void main(String[] args) {
        BSTTree<Integer> bstTree = new BSTTree<>();
        bstTree.addNode(12);
        bstTree.addNode(8);
        bstTree.addNode(18);
        bstTree.addNode(5);
        bstTree.addNode(4);
        bstTree.addNode(11);
        bstTree.addNode(17);
        bstTree.addNode(20);
        List<Integer> keys = new ArrayList<>();
        bstTree.inOrder(bstTree.root, keys);
        System.out.println(keys);
    }




}
