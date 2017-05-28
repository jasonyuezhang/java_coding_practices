#  Java Coding Practices

`mvn test`: Run all tests

#### 1. Spatial Indexing

* Time Complexity

Actions | Average  | Worst | Remarks 
--------|----------|-------|----------
Insert  | O(log n) | O(n)  | We are basically doing binary tree insert, thus the insert option.
Find Closest | O(n) | O(n) | Generally we search both left side and right side, thus the total time complexity is O(n).
    
* Memory Space Complexity

Actions | Average  | Remarks 
--------|----------|----------
Insert  | O(n) | We create a node for each point, thus the total space consumed is the tree space, which is O(n).
Find Closest | O(n) | Same as above.