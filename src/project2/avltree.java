/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2;

import java.util.ArrayList;

/**
 *
 * @author yarenk
 */
public class avltree {

    Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int index, String Data) {

        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return (new Node(index, Data));
        }

        if (Data.compareTo(node.Data) < 0) {
            node.left = insert(node.left, index, Data);
        } else if (Data.compareTo(node.Data) > 0) {
            node.right = insert(node.right, index, Data);
        } else // Duplicate keys not allowed
        {
            return node;
        }

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));

        /* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && Data.compareTo(node.left.Data) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && Data.compareTo(node.right.Data) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && Data.compareTo(node.left.Data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && Data.compareTo(node.right.Data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public int getSize(Node root) {
        int leftsize = 0;
        int rightsize = 0;
        if (root != null) {
            if (root.left != null) {
                leftsize = getSize(root.left);
            }
            if (root.right != null) {
                rightsize = getSize(root.right);
            }
            return 1 + leftsize + rightsize;
        }

        return 0;

    }

    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    Node deleteNode(Node root, String anahtar,Integer [] index) {
        if (root == null) {
            return null;
        }

        if (anahtar.compareTo(root.Data) < 0) {
            root.left = deleteNode(root.left, anahtar,index);
        } else if (anahtar.compareTo(root.Data) > 0) {
            root.right = deleteNode(root.right, anahtar,index);
        } else {
                index[0]=root.index;
            if ((root.left == null) || (root.right == null)) {
                Node temp;
                if (null == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                root = temp;
            } else {
                Integer temp1= index[0]; 
                Node temp = minValueNode(root.right);
                root.Data = temp.Data;
                root.index=temp.index;
                deleteNode(root.right,temp.Data,index);
                index[0]=temp1;
                
            }
        }

        if (root == null) {
            return null;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) > 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) < 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    

    public Node search(Node node, String Data) {
        if (node != null) {
            if (Data.compareTo(node.Data) < 0) {
                node = search(node.left, Data);
            }
            else if (Data.compareTo(node.Data) > 0) {
                node = search(node.right, Data);
            } else {
                return node;
            }
            return node;

        }
        return null;
    }

   

    public void searchRangeRecursive(Node focus, String Data, String Data2, ArrayList<Node> store) {

        if (focus != null) {
            searchRangeRecursive(focus.left, Data, Data2, store);

            if (Integer.parseInt(focus.Data) >= Integer.parseInt(Data) && Integer.parseInt(focus.Data) <= Integer.parseInt(Data2)) {
                store.add(focus);
                searchRangeRecursive(focus.right, Data, Data2, store);
            }

        }

    }
}