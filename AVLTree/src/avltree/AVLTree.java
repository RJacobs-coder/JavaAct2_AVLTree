
package avltree;

/*
Name: Robert Jacobs 30018755
Date: 25/02/2021
Project: AVLTree application
Description: Allows the user to create an AVL Tree based on their inputs and fill them
with String data.
*/



import java.util.*;

// Defines node that will be added to the AVLTree.
class Node  
{  
    int key, height;  
    Node left, right;  
    String data;
  
    Node(int d)  
    {  
        key = d;  
        height = 1;  
    }  
}  
// AVLTree Class creates, manipulates and controls the placement of the nodes inside the tree.
class AVLTree  
{  
    Node root;  
  
   // Determins how how the tree is.
    int height(Node N)  
    {  
        if (N == null)  
            return 0;  
        return N.height;  
    }  
  
   
    int max(int a, int b)  
    {  
        return (a > b) ? a : b;  
    }
    // Rotates the tree right.
    Node rightRotate(Node y)  
    {  
        Node x = y.left;  
        Node T2 = x.right;  
  
       
        x.right = y;  
        y.left = T2;  
  
       
        y.height = max(height(y.left), height(y.right)) + 1;  
        x.height = max(height(x.left), height(x.right)) + 1;  
  
      
        return x;  
    }
    // Rotates the tree right.
    Node leftRotate(Node x)  
    {  
        Node y = x.right;  
        Node T2 = y.left;  
  
       
        y.left = x;  
        x.right = T2;  
  
       
        x.height = max(height(x.left), height(x.right)) + 1;  
        y.height = max(height(y.left), height(y.right)) + 1;  
  
      
        return y;  
    } 
    // Checks to see if the tree is balanced.
    int getBalance(Node N)  
    {  
        if (N == null)  
            return 0;  
        return height(N.left) - height(N.right);  
    }  
  
    // Inserts a new node based on a users key.
    Node insertNode(Node node, int key)  
    {
        if (node == null)  
            return (new Node(key));  
  
        if (key < node.key)  
            node.left = insertNode(node.left, key);  
        else if (key > node.key)  
            node.right = insertNode(node.right, key);  
        else // Equal keys not allowed  
            return node;  
  
       
        node.height = 1 + max(height(node.left),  
                            height(node.right));  
  
     
        int balance = getBalance(node);  
  
       
        if (balance > 1 && key < node.left.key)  
            return rightRotate(node);  
  
       
        if (balance < -1 && key > node.right.key)  
            return leftRotate(node);  
  
       
        if (balance > 1 && key > node.left.key)  
        {  
            node.left = leftRotate(node.left);  
            return rightRotate(node);  
        }  
  
        
        if (balance < -1 && key < node.right.key)  
        {  
            node.right = rightRotate(node.right);  
            return leftRotate(node);  
        }
        return node;  
    }  
  
    // Gets the min values node to aid in deleting, finding and updating nodes.
    Node minValueNode(Node node)  
    {  
        Node current = node;  
  
        
        while (current.left != null)  
        current = current.left;  
  
        return current;  
    }  
  
    // Deletes a node using a key input by the user.
    Node deleteNode(Node root, int key)  
    {  
      
        if (root == null)  
            return root;  
  
      
        if (key < root.key)  
            root.left = deleteNode(root.left, key);  
  
       
        else if (key > root.key)  
            root.right = deleteNode(root.right, key);  
  
       
        else
        {
            if ((root.left == null) || (root.right == null))  
            {  
                Node temp = null;  
                if (temp == root.left)  
                    temp = root.right;  
                else
                    temp = root.left;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else 
                    root = temp; 
                                
            }  
            else
            {  
                Node temp = minValueNode(root.right);  
  
               
                root.key = temp.key;  
  
               
                root.right = deleteNode(root.right, temp.key);  
            }  
        }  
                balanceTree(root);
                return root;
    }
    
    // Finds a node using an input from the user.
    Node findNode(Node root, int key)  
    {  
      
        if (root == null)  
            return root;  
   
        if (key < root.key)  
            root.left = findNode(root.left, key);  
  
        else if (key > root.key)  
            root.right = findNode(root.right, key);  
  
        else
        {  
  
            if(root.key == key){
                
                System.out.println("Target Found - Key: " + root.key + "  height: " + root.height);
            }
            else{
                System.out.println("Target Not found.");
            }
        }  
                balanceTree(root);
                return root;
    }
    // Updates the node by allowing the user to select which node to update and then adding a user input strign to the node.
    Node updateNode(Node root, int key)  
    {  
        if (root == null)  
            return root;  
  
        if (key < root.key)  
            root.left = updateNode(root.left, key);  
  
        else if (key > root.key)  
            root.right = updateNode(root.right, key);  
  
        {  
  
            if (root.key == key) // Checks if the current key matches the target and asks the user to input values.
                {
                Scanner s = new Scanner(System.in);
                    System.out.println("Target Found - " + "key: " + root.key + "  height: " + root.height);
                    System.out.println("Please enter the string you wish to save into the node:");
                    root.data = s.nextLine();
                    System.out.println("Record has been updated - Key : " + root.key + "  height: " + root.height + "  data: " + root.data);

                }
                else
                {
                    System.out.println("Target not found , Please try again"); // *** Not yet implemented correctly***
                }
        }  
                balanceTree(root);
                return root;
    }
    
    //Allows the user to auto insert many nodes. Inserts strings without user prompt.
    Node updateNodeAuto(Node root, int key)  
    {  
        if (root == null)  
            return root;  
  
        if (key < root.key)  
            root.left = updateNodeAuto(root.left, key);  
  
        else if (key > root.key)  
            root.right = updateNodeAuto(root.right, key);  
  
        {  
  
            if (root.key == key) // Checks if the current key matches the target and asks the user to input values.
                {
                    System.out.println("Target Found - " + "key: " + root.key + "  height: " + root.height);
                    System.out.println("Please enter the string you wish to save into the node:");
                    root.data = "SampleData" + key+10;
                    System.out.println("Record has been updated - Key : " + root.key + "  height: " + root.height + "  data: " + root.data);

                }
                else
                {
                    System.out.println("Target not found , Please try again"); // *** Not yet implemented correctly***
                }
        }  
                balanceTree(root);
                return root;
    }
    
    // Checks tree for imbalances and corrects them after node manipulation.
    public Node balanceTree(Node Root){
        
        if (root == null)  
            return root;  
  
        
        root.height = max(height(root.left), height(root.right)) + 1;  
  
       
        int balance = getBalance(root);  
  
      
        if (balance > 1 && getBalance(root.left) >= 0)  
            return rightRotate(root);  
  
       
        if (balance > 1 && getBalance(root.left) < 0)  
        {  
            root.left = leftRotate(root.left);  
            return rightRotate(root);  
        }
        if (balance < -1 && getBalance(root.right) <= 0)  
            return leftRotate(root);  
  
      
        if (balance < -1 && getBalance(root.right) > 0)  
        {  
            root.right = rightRotate(root.right);  
            return leftRotate(root);  
        }  
  
        return root; 
    }
  
    // Prints result after the method is called.
    void printResults(Node node)
    {
        if(node != null)
        {
            System.out.println("Node Key - " + node.key + "  Node Height - " + node.height + "  Node Data - " + node.data);
            printResults(node.left);
            printResults(node.right);
        }
    }
  
    // Calls the methods from the user depending on how they wish to use the application.
    public static void main(String[] args)  
    {  
        int inputNumber;
        
        AVLTree tree = new AVLTree(); 
        Scanner s = new Scanner(System.in);
        
        System.out.println();
        System.out.println("Welcome to the application, please follow the onscreen instructions.");
        char userChoice;
        
        boolean loop = true;
        while (loop == true)
        {
            System.out.println();
            System.out.println("Please enter the command you wish to carry out - Insert (new) = 'i' -- Delete (existing) = 'd' -- Find (existing) = 'f' -- Update (existing) = 'u' -- Exit = Press Enter");
            System.out.println("You can also auto insert 10 nodes with user input Strings. = 'c' --");
            String hold = s.nextLine();
            userChoice = hold.charAt(0);
            
            switch (userChoice){
                
                case 'i':
                    System.out.println();
                    System.out.println("Please Input the key (number) of the new node : ");
                    inputNumber = Integer.parseInt(s.nextLine());
                    tree.root = tree.insertNode(tree.root, inputNumber);
                    tree.printResults(tree.root);
                    break;
                    
                case 'd':
                    System.out.println();
                    System.out.println("Please Input the key (number) of the node you wish to delete : ");
                    inputNumber = Integer.parseInt(s.nextLine());
                    tree.root = tree.deleteNode(tree.root, inputNumber);
                    tree.printResults(tree.root);
                    break;
                    
                case 'f':
                    System.out.println();
                    System.out.println("Please Input the key (number) of the node you wish to find: ");
                    inputNumber = Integer.parseInt(s.nextLine());
                    tree.root = tree.findNode(tree.root, inputNumber);
                    tree.printResults(tree.root);
                    break;
                    
                case 'u':
                    System.out.println();
                    System.out.println("Please Input the key (number) of the node update with data: ");
                    inputNumber = Integer.parseInt(s.nextLine());
                    tree.root = tree.updateNode(tree.root, inputNumber);
                    tree.printResults(tree.root);
                    break;
                 
                case 'c':
                    System.out.println();
                    System.out.println("Pre-filld nodes printing out for demo...");
                    for(int i = 0; i < 10; i++){
                        tree.root = tree.insertNode(tree.root, i+1);
                        tree.root = tree.updateNodeAuto(tree.root, i+1);
                    }
                    tree.printResults(tree.root);
                    break;
                    
                default:
                    System.out.println("Program Closing Now.");
                    loop = false;
                    break;
            }
        }
    }  
}
