
public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.add(5);
        tree.add(7);
        tree.add(1);
        tree.contains(5);

        tree.printTree(tree.root);
        
    }
}
