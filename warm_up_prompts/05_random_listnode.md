# Linked Lists - Probability

#### Prompt

Given the head node of a linked list with integer values, return a random value
from that linked list. Note that you must do this in linear runtime with only a
single pass through the linked list, and also in constant auxiliary space.

#### Examples:

```

Input:

(5) -> (3) -> (22) -> (4) -> (71)

Output:

If we were to run this one hundred times, we would expect there to be an almost
even distribution of twenty 5s, twenty 3s, twenty 22s, twenty 4s, and twenty 71s
returned.
```


#### Input:

```
class ListNode(val) {
    this.val = val;
    this.next = null;
}
```

#### Output
`node.value` = `integer`

#### Constraints:

**Time**: `O(N)`

**Space**: `O(1)`

Only **one** pass is allowed through the linked list

#### Hints:

How would you do this if you didn't have a constant auxiliary space constraint?

How would you do this if you were allowed a second pass through the linked list?

What would the answer be if you had a single-element linked list? Two elements
in your linked list?

#### Extra Credit

Use the ListNode class from your homework/target practice to create the linked
list from above. Can you prove that your solution is evenly random?

#### Solution

YOUR WORK HERE
