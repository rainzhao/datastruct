package example.tree;

import java.util.*;

/**
 * @author zhaoyu
 * @date 2019-07-04
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 计算节点高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获取node节点平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断以node为根的二叉树是否是一棵平衡的二叉树
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void printNode(Node node) {
        if (node == null) {
            return;
        }
        // 队列进行层序遍历
        Queue<Node> list = new ArrayDeque<>();
        // push跟节点
        list.add(node);
        // 存放结果的list
        List<K> resultList = new ArrayList<>();
        // 进行广度优先遍历
        printNodeLoop(list, resultList);
        // 输出结果
        System.out.println(resultList);
    }


    /**
     * 广度优先遍历(层序遍历)
     * 使用队列实现
     * @param list
     */
    private void printNodeLoop(Queue<Node> list, List<K> resultList) {
        while (list.size() > 0) {
            Node pop = list.remove();
            resultList.add(pop.key);
            if (pop.left != null) {
                list.add(pop.left);
            }
            if (pop.right != null) {
                list.add(pop.right);
            }
        }
    }

    private void inOrder(Node root, List<K> keys) {
        if (root == null) {
            return;
        }

        inOrder(root.left, keys);
        keys.add(root.key);
        inOrder(root.right, keys);
    }


    /**
     *       y                       x              过程：
     *      / \                    /   \             1. Node x = y.left;
     *     x   T4    右旋转        z     y            2. Node T3 = x.right;
     *    / \      =========>    / \   /  \          3. x.right = y;
     *   z   T3                 T1 T2 T3  T4         4. y.left = T3;
     *  / \
     * T1  T2
     *
     * @param y 插入元素后新节点的根
     * @return 旋转后新的根节点
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;

        // 重新计算高度(先计算y节点的高度因为x节点的高度依赖于y)
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     *        y                              X
     *      /   \                          /   \
     *     T4    X         左旋转          y     Z
     *          / \      =========>      / \   /  \
     *         T3  Z                    T4 T3 T1  T2
     *            / \
     *           T1  T2
     * 过程：
     * 1. Node X = y.right;
     * 2. Node T3 = X.left;
     * 3. X.left = y;
     * 4. y.right = T3;
     * <p>
     * 旋转后为何还能保证是一棵二叉搜索树？
     * 旋转前：  T4 < Y < T3 < X < T1 < Z < T2
     * 旋转后：  T4 < Y < T3 < X < T1 < Z < T2  节点之间的大小关系不会变化，保证了其还是一个二叉搜索树
     * <p>
     * 旋转后为何可以保证其是一个平衡二叉树（AVL树）？
     * 旋转前后 T1，T2, T3, T4 都是叶子节点，前后高度值未变化，都是1
     * TODO 证明
     *
     * @param y
     * @return 返回旋转后新的根节点
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        x.left = y;
        y.right = T3;

        // 重新计算X和Y的节点高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 向以node为根的二分搜索树中插入元素(key, value) ，递归
     * 返回插入新节点后二分搜索树的根
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // 判断是否需要右旋转 RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR 右子树的高度大于左子树的高度 进行左旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }



        return node;
    }

    /**
     * 测试用例：
     * // 根节点
     * avlTree.add(20, "Y");
     * // 左子树
     * avlTree.add(18, "X");
     * avlTree.add(30, "X4");
     * avlTree.add(16, "Z");
     * avlTree.add(19, "X3");
     * avlTree.add(15, "X1");
     * avlTree.add(17, "X2");
     * // 右子树
     * avlTree.add(30, "B");
     * avlTree.add(28, "Q3");
     * avlTree.add(34, "A");
     * avlTree.add(32, "Q1");
     * avlTree.add(36, "Q3");
     * <p>
     * <p>
     * 第一次右旋转（添加15节点）后：
     *       18
     *      /  \
     *     16  20
     *    /    / \
     *   15   19  30
     * 添加32节点前：
     *          18
     *        /    \
     *       16     20
     *      /  \   /  \
     *     15  17 19  30
     *               /  \
     *              28  34
     * <p>
     * 第一次左旋转（添加32节点）后：
     *            18
     *          /    \
     *        16      30
     *       /  \    /   \
     *      15  17  20    34
     *             /  \   /
     *            19  28 32
     *
     * @param args
     */
    public static void main(String[] args) {

        AVLTree<Integer, String> avlTree = new AVLTree<>();
        // 根节点
        avlTree.add(20, "Y");
        // 左子树
        avlTree.add(18, "X");
        avlTree.add(30, "X4");
        avlTree.add(16, "Z");
        avlTree.add(19, "X3");
        avlTree.add(15, "X1");
        avlTree.add(17, "X2");
        // 右子树
        avlTree.add(28, "Q3");
        avlTree.add(34, "A");
        avlTree.add(32, "Q1");
        avlTree.add(36, "Q2");

        List<Integer> keys = new ArrayList<>();

        avlTree.inOrder(avlTree.root, keys);

        System.out.println("中序遍历：" + keys);

        System.out.print("层序遍历：");
        avlTree.printNode(avlTree.root);
        System.out.println();

        System.out.println("是否是一棵平衡二叉树：" +avlTree.isBalanced());



    }


}
