public class RedBlackTree<T extends Comparable<T>>{

    public Node root;
    
    public boolean contains (T value){
        Node node = root;
        node.value = value;
        while (node != null) {
            if(node.value.equals(value)){
                return true;
            }
            if(node.value.compareTo(value) >= 0){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return false;
    }

    public void add(T value){
        Node node = new Node();
        node.value = value;
        if(root == null){
            root = node;
            root.color = Color.BLACK;
            
        }
        Node parent = root;
        Node son = null;
        if(parent.getValue().compareTo(value) >= 0){
            son = parent.getLeft();         
        }else{
            son = parent.getRight();
        }
        
        while (son != null) {
            parent = son;
            if(parent.getValue().compareTo(value) >= 0){
                son = parent.getLeft();
            }else{
                son = parent.getRight();
            }
        }

        if(parent.getValue().compareTo(value) >= 0){
            parent.setLeft(node);
            node.setColor(Color.RED);

        }else{
            parent.setRight(node);
            node.setColor(Color.RED);

        }
            
        node.setParent(parent);
        balance(node);
    }
            
    private void balance(Node node){
        Node father;
        Node grandFather;
        while ((father = node.getParent()) != null && father.getColor() == Color.RED ) {
            grandFather = father.getParent();
            if(grandFather.getLeft() == father){
                Node uncle = grandFather.getRight();
                if(uncle != null && uncle.getColor() == Color.RED){
                    colorSwap(grandFather);
                    node = grandFather;
                    continue;
                }
            if(node == father.getRight()){
                leftSwap(father);
                Node temp = node;
                node = father;
                father = temp;
            }
            father.setColor(Color.BLACK);
            grandFather.setColor(Color.RED);
            rightSwap(grandFather);
            }else{
                Node uncle = grandFather.getLeft();
                if(uncle != null && uncle.getColor() == Color.RED){
                    colorSwap(grandFather);
                    node = grandFather;
                    continue;
                }
                if(node == father.getLeft()){
                    rightSwap(father);
                    Node temp = node;
                    node = father;
                    father = temp;
                }
                father.setColor(Color.BLACK);
                grandFather.setColor(Color.RED);
                leftSwap(grandFather);
            }
        }
        root.setColor(Color.BLACK);
    }    

    private void colorSwap(Node node){
        node.left.setColor(Color.BLACK);
        node.right.setColor(Color.BLACK);
        node.setColor(Color.RED);
    }

    private void leftSwap(Node node){
        Node right = node.getRight();
        Node parent = node.getParent();
        if(parent == null){
            root = right;
            right.setParent(null);
        }else{
            if(parent.getLeft() != null && parent.getLeft() == node){
                parent.setLeft(right);
            }else{
                parent.setRight(right);
            }
            right.setParent(parent);
        }
        node.setParent(right);
        node.setRight(right.getLeft());
        if(right.getLeft() != null){
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
    }

    private void rightSwap(Node node){
        Node left = node.getLeft();
        Node parent = node.getParent();
        if(parent == null){
            root = left;
            left.setParent(null);
        }else{
            if(parent.getLeft() != null && parent.getLeft() == node){
                parent.setLeft(left);
            }else{
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        node.setParent(left);
        node.setLeft(left.getRight());
        if(left.getRight() != null){
            left.getRight().setParent(node);
        }
        left.setRight(node);
    }

    public void printTree(Node node){    
        if (node == null)
            return;
        printTree(node.getLeft());
        System.out.println(node.toString() + " ");
        printTree(node.getRight());
        
    }
    

    class Node{
        private T value;
        private Node parent;
        private Node left;
        private Node right;
        private Color color;

        private Node(){

        }

        private Node(T value){
            this.value = value;
            this.color = Color.RED;
        }

        public T getValue() {
            return value;
        }
        
        /*public void setValue(T value) {
            this.value = value;
        }*/

        public Node getParent() {
            return parent;
        }
        
        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }
            
        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
        return "RedBlackTreeNode{" +
        ",value=" + value +
        ", color=" + color +
        '}';
        }
  
    }

    private enum Color{
        RED, BLACK
    }
}