package example.tree;

/**
 * @author zhaoyu
 * @date 2019-03-04
 */
public class TreeNode<E extends Comparable> {
    private E e;
    private TreeNode<E> left;
    private TreeNode<E> right;


    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }


    public static void main(String[] args) {

        Integer[] arr = (Integer[])(new Object[100]);

        arr[0] = 10;

        arr[1] = 20;
        System.out.println(arr);
    }
}
