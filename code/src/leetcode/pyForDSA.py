# type: ignore
# ruff: noqa

# %%
# ArrayList / Stack can be implemented using the inbuilt list, i.e., []

arr = []
arr.append(1)
arr.append(2)
arr.extend([4, 5, 6]) # add elements from another list.
print(arr) # [1, 2, 3, 4, 5, 6]
print(arr.pop()) # 6

# O(n) time taken for deleting from any other index.
print(arr.pop(1))  # 2

print("iterate over the list")
for element in arr:
    print(element)

print("iterate over the list with index")
for idx, element in enumerate(arr):
    print(f"Element at index {idx} is {element}")

# %%

# Tuples
# Tuples are immutable lists. They are faster than lists and can be used as keys in dictionaries. They can also be used to hash an ordered set of objects.

t = (1, 2, 3)
t2 = (1, 2, 3)
print(hash(t) == hash(t2))
print(t[0]) # 1
t = t + (4, 5, 6) # new tuple created
print(t) # (1, 2, 3, 4, 5, 6)

# %%

# (Double ended or single ended) Queue can be implemented using deque

from collections import deque

q = deque()
q.append(1)
q.append(2)
q.appendleft(3)
q.appendleft(4)
print(q) # [4 3 1 2]
print(q.pop())  # 2
print(q.popleft())  # 4

# %%
# Sets

s1 = set() # empty set
s1.add(1)
s1.update([2, 2, 3])
print(s1) # {1, 2, 3}

s2 = {1, 3, 4}
print(s1.intersection(s2)) # {1, 3}
print(s1.union(s2)) # {1, 2, 3, 4}
print(s1.difference(s2)) # {2}
print(s1.symmetric_difference(s2)) # {2, 4}
# %%
# Dictionaries

d1 = {}
d1["foo"] = 1
d1["bar"] = {1, 2, 3}
d1["hello"] = "world"
print(d1) # { "foo": 1, "bar": {1, 2, 3}, "hello": "world" }
print(d1["foo"]) # 1
# get Or Default value
print(d1.get("non-existent-key", 10)) # 10
# remove an entry from dict and get the value
print(d1.pop("foo")) # 1
# copy values from another dict, overridding if key exists
d1.update({"foo": 2, "bar": 3})
print(d1)  # { "foo": 2, "bar": 3, "hello": "world" }

# defaultdict is used for adding a factory function to the dict. Useful in various cases for shorter code.
from collections import defaultdict

d2 = defaultdict(list) # default value is empty list
# No need to write:
# if "foo" not in d2:
#     d2["foo"] = []

d2["foo"].append(1)
print(d2) # { "foo": [1] }

# 2d defaultdict
d3 = defaultdict(lambda: defaultdict(list))
d3["foo"]["bar"].append(1)
print(d3) # { "foo": { "bar": [1] } }

# iterate over keys and values
for key, value in d1.items():
    print(f"value of {key} is {value}")

print("Keys: ", d1.keys())
print("Values: ", d1.values())

# %%
# priority queue (heaps)
# by default, heapq is a min heap. To use max heap, multiply the values by -1 and then insert.

import heapq
hp = []
heapq.heappush(hp, 3)
heapq.heappush(hp, 1)
heapq.heappush(hp, 2)
print(hp[0]) # 1
heapq.heappop(hp)
print(hp[0]) # 2

arr = [3,6,4,1,9,5]
heapq.heapify(arr) # convert list to heap
print(arr[0]) # 1

# %%

# Counter
# Counter is a dictionary subclass that counts hashable objects. It is a collection where elements are stored as dictionary keys and their counts are stored as dictionary values.

from collections import Counter

c = Counter("helloo")
print(c) # Counter({'l': 2, 'h': 1, 'e': 1, 'o': 2})
print(c["l"]) # 2
print(c["z"]) # 0 default value is 0.
print(c.get("y", 10)) # 10
print(c.most_common(2)) # [('l', 2), ('o', 2)]
# %%
# classes with ordering.
# ordering is required when we want to use the class objects in heaps, we want to sort, etc.

# total_ordering is a decorator that fills in missing ordering methods.
from functools import total_ordering

# In Python, there is no way to hide the fields of a class (using access modifiers like private, protected, public in Java).
# Instead, we use a convention to use _ before the field name to indicate that it is private.
# This is just a convention and does not actually make the field private.
# We can still access the field using object._field_name .

@total_ordering
class Student:
    """
    Class to represent an item with a value.
    We just need to define __lt__ (less than) and __eq__ (equal) methods.
    The decorator will fill in the rest of the ordering methods. 
    """

    # static values in a class are defined as class variables in python.
    # this is like static String PersonType = "Student" in Java.
    PersonType = "Student"

    # static methods are defined using the @staticmethod decorator.
    @staticmethod
    def getPersonType():
        """
        static method to get the person type.
        """
        return Student.PersonType

    def __init__(self, age, marks, incomeUSD):
        """
        init is the constructor in python.
        self is like this in Java. Unlike Java, we need to pass self as the first argument to all methods.
        """
        self.age = age
        self.marks = marks
        self.incomeUSD = incomeUSD

    def __lt__(self, other):
        """
            Same code in java:

            static int compare(Person a, Person b) {
                if (a.marks != b.marks) return b.marks - a.marks;
                if (a.incomeUSD != b.incomeUSD) return a.incomeUSD - b.incomeUSD;
                return b.age - a.age;
            }
        """
        if self.marks != other.marks:
            return other.marks - self.marks
        if self.incomeUSD != other.incomeUSD:
            return self.incomeUSD - other.incomeUSD
        return other.age - self.age

    def __eq__(self, other):
        # all(<iterator>) returns true if all the values in the iterator are true.
        # any(<iterator>) returns true if any of the values in the iterator are true.
        return all([
            self.age == other.age,
            self.marks == other.marks,
            self.incomeUSD == other.incomeUSD
        ])

    def __repr__(self):
        """
            Same code in java:

            @Override
            public String toString() {
                return String.format("marks = %d%%, income = %d USD, age = %d yrs", this.marks, this.incomeUSD, this.age);
            }
        """
        return f"[[marks = {self.marks}%, income = {self.incomeUSD} USD, age = {self.age} yrs]]"

    # We need to implment __str__ to use print on the object
    # We need to implement __repr__ to use the object in f-strings
    # Both can be implemented using the same code in most cases.
    def __str__(self) -> str:
        return self.__repr__()

    # We can also implement __hash__ if we want to use the object as a key in a dict.
    def __hash__(self) -> int:
        return hash((self.age, self.marks, self.incomeUSD))

students = [
    Student(18, 90, 5000),
    Student(18, 90, 4000),
    Student(20, 90, 4000),
    Student(20, 88, 4000)
]

# Student must be hashable to be used as a key in a dict.
indices = { student: idx for idx, student in enumerate(students) }
print("Indices: ")
print(indices)

print("Sorted students: ")
sorted_students = sorted(students)
# We will cover map in next block.
print("\n".join(map(str, sorted_students)))

# %%

# Lambdas
# Lambdas are anonymous functions that can be used to create inline functions.
# Useful for creating small functions you dont want to define a function separately.
# They can also be assigned to variables.

# Takes x and y as input and returns x + y
add = lambda x, y: x + y
print(add(1, 2)) # 3

# %%

# map, filter, reduce
# map applies a function to all the items in an input_list.

items = [1, 2, 3, 4, 5]
# You are mapping each item in the `items` list (x) to (x**2).
# (x**2) is simply x raised to the power of 2. Math.pow(x, 2) in Java.
squared = list(map(lambda x: x**2, items))
print(squared) # [1, 4, 9, 16, 25]

# filter creates a list of elements for which a function returns true.
# In this case, we are filtering out all the odd numbers.
filtered = list(filter(lambda x: x % 2 == 0, items))
print(filtered) # [2, 4]

# reduce is a really useful function for performing some computation on a list and returning the result.
# It applies a rolling computation to sequential pairs of values in a list.
from functools import reduce
product_of_all_values = reduce((lambda acc, next: acc * next), items)
print(product_of_all_values)  # 1*2*3*4*5 = 120

# %%

# permutations, combinations
# permutations are all possible orderings of a list of items.
# combinations are all possible combinations of a list of items.

from itertools import permutations, combinations, product

import itertools
a = [12,2,3]

items = [1, 2, 3]
# all possible orderings of 1, 2, 3
perm = list(permutations(items))
print(perm) # [(1, 2, 3), (1, 3, 2), (2, 1, 3), (2, 3, 1), (3, 1, 2), (3, 2, 1)]

# all possible combinations of 2 items from 1, 2, 3
comb = list(combinations(items, 2))
print(comb) # [(1, 2), (1, 3), (2, 3)]

from math import comb, perm

print(perm(5, 3)) # 60
print(comb(5, 3)) # 10

# %%

# binary tree
# We can use a class to represent a binary tree node.
# We can use recursion to traverse the tree.

class TreeNode:
    """
    We can use default values for arguments in the constructor.
    """
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def inorder(self):
        """
        Inorder traversal of the tree.
        Recursively visit the deepest left first.
        Then gradually move back to the parent and then to the right.
        """
        if self.left is not None:
            self.left.inorder()
        print(self.val)
        if self.right is not None:
            self.right.inorder()

    """
    PreOrder and PostOrder can be similarly defined. 
    """

# Create nodes
node3 = TreeNode(3)
node7 = TreeNode(7)
node15 = TreeNode(15)
# We can name which argument we are passing by using the argument name=val syntax.
node5 = TreeNode(5, left=node3, right=node7)
node20 = TreeNode(20, left=node15)
root = TreeNode(10, left=node5, right=node20)
root.inorder()
# %%

# In python, there is no overflow hassle.
x = 2**100 * 3**30 + 5**80
print(x)  # 82718061255563765504322320678487125758426365677405523649
# %%

# Dynamic Programming in Python
# Top-down recursion which follows the DP pattern (Optimal Substructure + Overlapping Subproblems) can be memoized using @cache decorator.

from functools import cache

@cache
def fib(n):
    if n <= 1:
        return n
    return fib(n-1) + fib(n-2)

print(fib(10))  # 55

# %%
# Sample DP question - Coin Change
# Given a set of coin denominations, find the minimum number of coins required to make a certain amount.

from functools import cache

class Solution:

    def __init__(self, coins) -> None:
        self.coins = coins
    
    def coinChange(self, amount: int) -> int:
        @cache
        def dp(remaining):
            if remaining == 0:
                return 0
            if remaining < 0:
                return float('inf')
            return min(1 + dp(remaining - coin) for coin in self.coins)
        
        result = dp(amount)
        return -1 if result == float('inf') else result

sol = Solution([1, 2, 5])
print(sol.coinChange(11))  # 3
# %%
# Python does not have TreeMap and TreeSet equivalent (ordered collections; height balanced trees implemented using Red-Black Trees).
# There is a 3rd party library called sortedcontainers which provides SortedDict and SortedSet.
# In leetcode, this library is pre-installed. So, you can import it in leetcode.
# There might be some online judges where this is not installed.


# !pip install sortedcontainers
from sortedcontainers import SortedDict, SortedSet

sd = SortedDict()
sd[3] = 1
sd[1] = 2
sd[2] = 3

print(sd)  # SortedDict({1: 2, 2: 3, 3: 1})

ss = SortedSet([3, 1, 2, 10, 7, 8, 9])
print(ss)  # SortedSet([1, 2, 3])
print(ss[0]) # 1
print(ss[-1]) # 10

ss.remove(10)
print(ss[-1]) # 9

# Multiset (Ordered) can be implemented using SortedDict with key as element and value as count.
class SortedMultiSet:
    def __init__(self):
        self.sd = SortedDict()

    def add(self, val):
        self.sd[val] = self.sd.get(val, 0) + 1

    def remove(self, val):
        if val in self.sd:
            self.sd[val] -= 1
            if self.sd[val] == 0:
                del self.sd[val]

    def __repr__(self):
        return str(list(self.sd.items()))

sms = SortedMultiSet()
sms.add(1)
sms.add(1)
sms.add(1)
sms.add(2)
sms.remove(1)
print(sms)  # [1, 1, 2]

# %%
