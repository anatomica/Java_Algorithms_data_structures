package Homework6;

import java.util.NoSuchElementException;
import java.util.Random;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {
        Random random = new Random();
        int sum = 0;
        BinarySearchTree<Integer, Integer> searchTree = new BinarySearchTree<>();

        for (int i = 0; i < 20; i++) {
            while (searchTree.depthBinaryTree() < 6) {
                int number = random.nextInt(200) - 100;
                searchTree.put(number, 10);
                System.out.println(number);
            }
            if (searchTree.isBalance()) {
                System.out.println("Balance: true \n");
                sum++;
            } else {
                System.out.println("Balance: false \n");
            }
            searchTree = new BinarySearchTree<>();
        }
        System.out.println("Balanced Tree: " + sum*100/20 + " %");
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private Node root = null;

    public boolean isBalance() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        } else {
            int left = depthBinaryTree(root.left);
            int right = depthBinaryTree(root.right);
            System.out.println("left: " + left + " right: " + right);
            if (Math.abs(left - right) > 1) {
                return false;
            }
        }
        return true;
    }

    public int depthBinaryTree() {
        return depthBinaryTree(root);
    }

    private int depthBinaryTree(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(depthBinaryTree(node.left), depthBinaryTree(node.right));
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return this.size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("The key can not be  Null");
        }
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public boolean contains(Key key) {
        return get(root, key) != null;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("The key can not be Null");
        }
        root = put(root, key, value);
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        return node;
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMax(root);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node tmp = node;
            node = max(node.left);
            node.left = deleteMax(tmp.left);
            node.right = tmp.right;
            tmp = null;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }
}
