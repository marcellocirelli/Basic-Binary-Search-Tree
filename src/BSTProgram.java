import java.util.Scanner;

public class BSTProgram {
    public static void main(String[] args) {

        // Node class. References left and right child nodes. If there's no left or right child, make it null
        class Node {
            int value;
            Node left, right;

            Node(int item) {
                value = item;
                left = right = null;
            }
        }

        // BST class
        class BinarySearchTree {
            Node root;

            // Initializes the root with null from the start
            BinarySearchTree() {
                root = null;
            }

            // Insert method: places a value in tree's root
            void insert(int value) {
                root = insertRec(root, value);
            }

            // Recursive insert method
            Node insertRec(Node root, int value) {
                // If the current subtree is empty, creates a new node here
                if (root == null)
                    return new Node(value);

                // Inserts a left child if the value is smaller than the current node
                if (value < root.value)
                    root.left = insertRec(root.left, value);
                    // Inserts a right child if the value is larger than the current node
                else if (value > root.value)
                    root.right = insertRec(root.right, value);
                return root;
            }

            // Delete
            void delete(int value) {
                root = deleteRec(root, value);
            }

            // Recursive delete method
            Node deleteRec(Node root, int key) {
                // Return null if there's nothing to delete
                if (root == null) return root;

                // Traverses left or right to find the node to delete
                if (key < root.value)
                    root.left = deleteRec(root.left, key);
                else if (key > root.value)
                    root.right = deleteRec(root.right, key);
                else {

                    // 1. Node with no left child - replace with right child
                    if (root.left == null)
                        return root.right;
                        // 2. Node with no right child - replace with left child
                    else if (root.right == null)
                        return root.left;

                    // 3. Node with both children
                    // Replaces the current node with inorder successor
                    root.value = minValue(root.right);
                    // Deletes the duplicate
                    root.right = deleteRec(root.right, root.value);
                }

                return root;
            }

            // Finds the smallest value in a subtree by going left as far as possible
            int minValue(Node root) {
                int minv = root.value;
                while (root.left != null) {
                    root = root.left;
                    minv = root.value;
                }
                return minv;
            }

            // Prints the inorder traversal
            void inorder() {
                System.out.print("Inorder traversal: ");
                inorderRec(root);
                System.out.println();
            }

            void inorderRec(Node root) {
                if (root != null) {
                    inorderRec(root.left);              // Traverses the left subtree
                    System.out.print(root.value + " "); // Prints the root
                    inorderRec(root.right);             // Traverses the right subtree
                }
            }

            // Prints the preorder traversal
            void preorder() {
                System.out.print("Preorder traversal: ");
                preorderRec(root);
                System.out.println();
            }

            void preorderRec(Node root) {
                if (root != null) {
                    System.out.print(root.value + " "); // Prints the root
                    preorderRec(root.left);             // Traverses the left subtree
                    preorderRec(root.right);            // Traverses the right subtree
                }
            }

            // Prints the postorder traversal
            void postorder() {
                System.out.print("Postorder traversal: ");
                postorderRec(root);
                System.out.println();
            }

            void postorderRec(Node root) {
                if (root != null) {
                    postorderRec(root.left);            // Traverses the left subtree
                    postorderRec(root.right);           // Traverses the right subtree
                    System.out.print(root.value + " "); // Prints the root
                }
            }
        }

        // Creates a new BST object
        BinarySearchTree bst = new BinarySearchTree();
        // Take user input
        Scanner scanner = new Scanner(System.in);

        // Preloads the tree with values
        int[] values = {4, 2, 6, 1, 3, 5, 7};
        for (int val : values) {
            bst.insert(val); // Inserts each value into the BST
        }

        // Menu/UI: run until the user exits
        while (true) {
            // Only print the menu once per valid loop
            System.out.println("\nBinary Search Tree Menu");
            System.out.println("1. Add Node");
            System.out.println("2. Delete Node");
            System.out.println("3. View Inorder Traversal");
            System.out.println("4. View Preorder Traversal");
            System.out.println("5. View Postorder Traversal");
            System.out.println("6. Exit");

            int choice;

            // Input loop: keep asking until a valid number is entered
            while (true) {
                System.out.print("Please enter an option: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break; // Valid input, exit inner loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                }
            }

            // Handle valid Add input
            if (choice == 1) {
                int val;
                while (true) {
                    System.out.print("Enter value to add: ");
                    try {
                        val = Integer.parseInt(scanner.nextLine());
                        bst.insert(val);
                        System.out.println("Node added.");
                        // This will delay the menu by 3 seconds
                        // Just for UI cleanup. It will show up for each option
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("That wasn’t a valid number. Try again.");
                    }
                }

            // Handle valid Delete input
            } else if (choice == 2) {
                int val;
                while (true) {
                    System.out.print("Enter value to delete: ");
                    try {
                        val = Integer.parseInt(scanner.nextLine());
                        bst.delete(val);
                        System.out.println("Node deleted (if it existed).");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("That wasn’t a valid number. Try again.");
                    }
                }
            }

            else if (choice == 3) {
                bst.inorder();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } else if (choice == 4) {
                bst.preorder();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } else if (choice == 5) {
                bst.postorder();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } else if (choice == 6) {
                System.out.println("All done.");
                scanner.close();
                return;

            } else if (choice == 13) {
                System.out.println("You’re now cursed. Please enter a valid option between 1-6.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } else {
                System.out.println("Please enter a number between 1-6.");
            }
        }
    }
}