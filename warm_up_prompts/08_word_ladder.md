# Graph Traversal - Word Ladder

#### Prompt

Given a begin and end word, along with a list of words, return the length
of the shortest transformation sequence

#### Examples:

```
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

result = 5

Reason:
The shortest transformation sequence from "hit" to "cog" is:

"hit" -> "hot" -> "dot" -> "dog" -> "cog"

Because this sequence contains 5 elements, we return 5

```

#### Input:

`beginWord` = `String`

`endWord` = `String`

`wordList` = `Array of Strings`

#### Output:
`result` = `Integer`

#### Constraints:

**Time**: None

**Space**: None

All words are the same length

You can only change one letter at a time

All words are made of letters in lowercase

Return 0 if there is no sequence

No duplicates in word list

You can assume beginWord and endWord are non-empty and not the same

endWord is contained in the wordList, but beginWord is not; that is intentional


#### Resources:

[Word Ladder] (https://en.wikipedia.org/wiki/Word_ladder)

[DFS Graph Traversals] (http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/)

[BFS Graph Traversals] (http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/)


#### Hints:

Consider breaking this problem into two parts: the first is creating a graph to represent the connections between words, and the second to traverse that graph to find the shortest path

Think about whether you want to do BFS or DFS and why

Consider what helper functions you may want to abstract out of your main solution

There are several different ways to approach this problem, and it might be helpful to go through them after to gain a deeper understanding

#### Solution

YOUR WORK HERE
