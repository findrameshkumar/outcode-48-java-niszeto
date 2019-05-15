# Tell Me a Time when you've failed.

Should take one minute; minute and a half at most

##### Prompt

Say: "Tell me a time when you've failed"

##### Do you hear these things?

- *Identify*: Does the interviewee discuss their competencies based on what you have asked them? Competencies include:
   - Technical Skills (libraries, languages, etc.)
   - Interpersonal Skills  


- *Prove*: Does the interviewee provide a _specific_ example (past experience or hypothetical scenario)  to showcase competencies and/or fit? Is it presented in the form of a story (punchline, beginning, middle, positive end)?


- *Close*: Does the interviewee showcase why the company should hire them and how their skills/experience/values will add value to and align with the team/company? Does the interviewee showcase what they have learned in "Prove" and how they will apply it to the new role?

# 310 - Rat Path

Given a `maze`, represented by a matrix of bits (values `0` and `1`), a rat must find a path from index `[0][0]` to `[n-1][m-1]`. The rat can only travel to the `right` or `down`, and can only travel on `0` values. `1` values represent walls.

```
Input:   maze (Matrix of elements with values either 0 or 1)
Output:  Array of two-item arrays indicating the path.
```

# Example

```
Input:   [[0, 0, 0, 1],
          [0, 1, 0, 1],
          [0, 1, 0, 0],
          [0, 0, 1, 0]]

Output:  [[0, 0],
          [0, 1],
          [0, 2],
          [1, 2],
          [2, 2],
          [2, 3],
          [3, 3]]
```

# Constraints

```
Time Complexity: O(N^2)
Auxiliary Space Complexity: O(N)
```

* If not path found, return the following path: `[[-1, -1]]`
* Remember, the rat can only move `RIGHT` or `DOWN`

# Solution

* 1) Create scope variables called `result` and `path`, and initialize `path` to an empty array (or dynamic array).

* 2) Create a helper method that takes in two indices `x` and `y`, that represent the current position in the `maze` we'll be recursing over.

	* a) The first base case: If `x` or `y` are out of bound or the value at `maze[x][y]` equals `1` return

	* b) Push `[x, y]` into `path`, to store the current location in the maze

	* c) The second base case if `x` or `y` are at the bottom right corner, set `result` to be a copy of `path` and return

	* d) Perform a recursive call going down `(x + 1, y)`

	* e) Perform a recursive call going right `(x, y + 1)`

	* f) Pop off the last element from the `path` array

* 3) Call the helper method with initial parameters of `(0, 0)`

* 4) If `result` is `undefined` return `[-1, -1]`, otherwise return `result`


```javascript

// JavaScript Solution:
// DOWN, RIGHT solution
function ratPath(maze) {
  let result;
  let path = [];
  function findPath(x, y) {
    if(x >= maze.length || y >= maze[0].length || maze[x][y] === 1) {
      return;
    }
    path.push([x, y]);
    if(x === maze.length - 1 && y === maze[0].length - 1) {
      result = path.slice();
      return;
    }
    findPath(x + 1, y)
    findPath(x, y + 1)
    path.pop();
  }
  findPath(0, 0);
  return !result ? [-1, -1] : result;
}


let maze = [[0, 0, 0, 1],
		        [0, 1, 0, 1],
		        [0, 1, 0, 0],
		        [0, 0, 1, 0]]


console.log(ratPath(maze))
/*
[ [ 0, 0 ],
  [ 0, 1 ],
  [ 0, 2 ],
  [ 1, 2 ],
  [ 2, 2 ],
  [ 2, 3 ],
  [ 3, 3 ] ]
*/


// General Solution (UP, DOWN, LEFT, RIGHT)
function ratPath2(maze) {
  let result;
  let path = [];
  function findPath(x, y) {
    if(x >= maze.length || y >= maze[0].length || x < 0 || y < 0 || maze[x][y] === 1) {
      return;
    }
    path.push([x, y]);
    maze[x][y] = 1;

    if(x === maze.length - 1 && y === maze[0].length - 1) {
      if(result === undefined || path.length < result.length) {
        result = path.slice();
      }
    }
    findPath(x - 1, y)
    findPath(x + 1, y)
    findPath(x, y - 1)
    findPath(x, y + 1)
    path.pop();
    maze[x][y] = 0;
  }
  findPath(0, 0);
  return !result ? [-1, -1] : result;
}


let maze2 =[[0, 1, 0, 0, 0],
		        [0, 1, 0, 1, 0],
		        [0, 1, 0, 1, 0],
		        [0, 0, 0, 1, 0]]


console.log(ratPath2(maze2))
/*
[ [ 0, 0 ],
  [ 1, 0 ],
  [ 2, 0 ],
  [ 3, 0 ],
  [ 3, 1 ],
  [ 3, 2 ],
  [ 2, 2 ],
  [ 1, 2 ],
  [ 0, 2 ],
  [ 0, 3 ],
  [ 0, 4 ],
  [ 1, 4 ],
  [ 2, 4 ],
  [ 3, 4 ] ]
  */
```

# Notes

For more of a challenge, try the general solution where the rat can move in any direction.

# Resources
http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
