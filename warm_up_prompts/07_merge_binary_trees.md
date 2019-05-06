# Binary Trees - Merge Binary Trees

#### Prompt

Given two binary trees, imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

#### Examples
Input: root1 {BinaryTreeNode}, root2 {BinaryTreeNode}


	Tree 1                     Tree 2  

          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7   


 Output: {BinaryTreeNode}

    Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7


#### Constraints
Time Complexity: O(N)   
Space Complexity: O(N)   


The merging process must start from the root nodes of both trees.   

#### Resources

[Binary Tree Traversals](http://www.ida.liu.se/opendsa/OpenDSA/Books/TDDD86_2014/html/BinaryTreeTraversal.html)

#### Hints

1. How can you traverse through both trees at the same time?
2. What happens when one of the nodes is null/None?
3. How can you break this problem into two smaller problems? ie. When should you merge, and when should you copy?

#### Solution

YOUR WORK HERE
