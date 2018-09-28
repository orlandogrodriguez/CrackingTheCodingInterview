package com.orlyrodz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CrackingTheCodingInterview {
    /**
     * Problem 1.1 - Is Unique
     *
     * Implement an algorithm to determine if a string has all unique
     * characters. What if you cannot use additional data structures?
     * @param str
     * @return
     */
    public boolean isUnique(String str) {
        if (str.length() == 0)
            return false;
        HashSet<Character> charSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (charSet.contains(c)) {
                return false;
            } else {
                charSet.add(c);
            }
        }
        return true;
    }

    /**
     * Problem 1.2 - Check Permutation
     *
     * Given two strings, write a method to decide if one is a permutation of
     * the other.
     * @param str1
     * @param str2
     * @return
     */
    public boolean checkPermutation(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        if (str1.length() == 0 || str2.length() == 0)
            return false;
        HashMap<Character, Integer> charMap1 = new HashMap<>();
        HashMap<Character, Integer> charMap2 = new HashMap<>();
        for (char c : str1.toCharArray()) {
            if (charMap1.containsKey(c)) {
                charMap1.put(c, charMap1.get(c) + 1);
            } else {
                charMap1.put(c, 1);
            }
        }
        for (char c : str2.toCharArray()) {
            if (charMap2.containsKey(c)) {
                charMap2.put(c, charMap2.get(c) + 1);
            } else {
                charMap2.put(c, 1);
            }
        }
        for (char c : charMap1.keySet()) {
            if (charMap1.get(c) != charMap2.get(c))
                return false;
        }
        return true;
    }

    /**
     * Problem 1.3 - URLify
     *
     * Write a method to replace all spaces in a string with '%20'. You may
     * assume that the string has sufficient space at the end to hold the
     * additional characters, and that you are given the "true" length of the
     * string. Use a character array to perform the operation in place.
     * @param str
     * @param len
     * @return
     */
    public char[] URLify(String str, int len) {
        int inputLen = str.length();
        char[] charArr = new char[inputLen];
        int numSpaces = (inputLen - len) / 2;
        if (str.length() == 0)
            throw new IllegalArgumentException();
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (str.charAt(i) == ' ') {
                charArr[j] = '%';
                charArr[j + 1] = '2';
                charArr[j + 2] = '0';
                j += 2;
            } else {
                charArr[j] = str.charAt(i);
            }
        }
        return charArr;
    }

    /**
     * Problem 1.4 - Palindrome Permutation
     *
     * Given a string, write a function to check if it is a permutation of a
     * palindrome. A palindrome is a word or phrase that is the same forwards
     * and backwards. A permutation is a rearrangement of letters. The
     * palindrome does not need to be limited to just dictionary words.
     * @param str
     * @return
     */
    public boolean permutationIsPalindrome(String str) {
        if (str.length() == 0)
            return false;
        if (str.length() == 1)
            return true;
        HashMap<Character, Integer> strMap = new HashMap<>();
        int charCount = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ')
                continue;
            if (strMap.containsKey(c)) {
                strMap.put(c, strMap.get(c) + 1);
            } else {
                strMap.put(c, 1);
            }
            charCount++;
        }
        boolean isEven = charCount % 2 == 0;
        boolean foundOddNumberOfCharacters = false;
        for (int v : strMap.values()) {
            if (isEven) {
                if (v % 2 != 0)
                    return false;
            } else {
                if (v % 2 != 0) {
                    if (foundOddNumberOfCharacters) {
                        return false;
                    } else {
                        foundOddNumberOfCharacters = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Problem 1.5 - One Away
     *
     * There are three types of edits that can be performed on strings: insert
     * a character, remove a character, or replace a character. Given two
     * strings, write a function to check if they are one edit away
     * @param str1
     * @param str2
     * @return
     */
    public boolean oneAway(String str1, String str2) {
        if (Math.abs(str1.length() - str2.length()) > 1)
            return false;
        if (str1.equals(str2))
            return true;
        int issueIndex1 = -1;
        int issueIndex2 = -1;
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                issueIndex1 = i;
                break;
            }
        }
        if (issueIndex1 == -1)
            return true;
        for (int j = 0; j < Math.min(str1.length(), str2.length()); j++) {
            if (str1.charAt(str1.length() - 1 - j) != str2.charAt(str2.length() - 1 - j)) {
                if (issueIndex1 == str1.length() - 1 - j || issueIndex1 == str2.length() - 1 - j) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return issueIndex2 == -1;
    }

    /**
     * Problem 1.6 - String Compression
     *
     * Implement a method to perform basic string compression using the counts
     * of repeated characters. For example, the string aabcccccaa would become
     * a2b1c5a3. If the "compressed" string would not become smaller than the
     * original string, your method should return the original string. You can
     * assume the string has only uppercase and lowercase letters (a - z).
     * @param str
     * @return
     */
    public String stringCompression(String str) {
        char curChar = str.charAt(0);
        int count = 0;
        StringBuilder sb = new StringBuilder("");
        if (str.length() == 0)
            return "";
        for (char c : str.toCharArray()) {
            if (c == curChar) {
                count++;
            } else {
                sb.append(curChar);
                sb.append(count);
                curChar = c;
                count = 1;
            }
        }
        sb.append(curChar);
        sb.append(count);
        return sb.length() > str.length() ? str : sb.toString();
    }

    /**
     * Problem 1.7 - Rotate Matrix
     *
     * Given an image represented by an NxN matrix, where each pixel in the
     * image is 4 bytes, write a method to rotate the image by 90 degrees. Can
     * you do this in place?
     * @param matrix
     * @param n
     * @return
     */
    public int[][] rotateMatrix(int[][] matrix, int n) {
        // swap upper right triangle with lower left triangle using the xor swap trick
        // reverse all rows
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[j][i] ^ matrix[i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                matrix[i][j] = matrix[i][j] ^ matrix[i][n - j - 1];
                matrix[i][n - j - 1] = matrix[i][n - j - 1] ^ matrix[i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[i][n - j - 1];
            }
        }
        return matrix;
    }

    /**
     * Problem 1.8 - Zero Matrix
     *
     * Write an algorithm such that if an element in an MxN matrix is 0, its
     * entire row and column are set to 0.
     * @param matrix
     * @return
     */
    public int[][] zeroMatrix(int[][] matrix) {
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (zeroRows.contains(i)) {
                    matrix[i][j] = 0;
                }
                if (zeroCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * Problem 1.9 - String Rotation
     *
     * Given two strings, s1 and s2, write code to check if s2 is a rotation of
     * s1 using only one call to java's String.contains() method.
     * @param s1
     * @param s2
     * @return
     */
    public boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        s1 = s1.concat(s1);
        if (s1.contains(s2))
            return true;
        return false;
    }

    /**
     * Problem 2.1a - Remove Duplicates
     *
     * Write code to remove duplicates from an unsorted Linked List.
     * @param root
     */
    public void removeDuplicates(Node root) {
        HashSet<Object> set = new HashSet<>();
        Node curNode = root.next;
        Node prevNode = root;
        while (curNode != null) {
            if (set.contains(curNode.data)) {
                if (curNode.hasNext()) {
                    prevNode.next = curNode.next;
                    curNode = curNode.next;
                } else {
                    prevNode.next = null;
                    return;
                }
            } else {
                set.add(curNode.data);
            }
            prevNode = prevNode.next;
            curNode = curNode.next;
        }
    }

    /**
     * Problem 2.1b - Remove Duplicates, No Buffer
     *
     * Write code to remove duplicates from an unsorted Linked List without
     * using a temporary buffer.
     * @param root
     */
    public void removeDuplicatesNoBuffer(Node root) {
        if (root.next == null || root == null)
            return;
        Node curNode = root;
        while (curNode != null) {
            Node runner = curNode.next;
            Node runnerPrev = curNode;
            while (runner != null) {
                if (curNode.data == runner.data) {
                    runnerPrev.next = runner.next;
                } else {
                    runnerPrev = runner;
                }
                runner = runner.next;
            }
            curNode = curNode.next;
        }
    }

    /**
     * Problem 2.2 - Kth To Last
     *
     * Implement an algorithm to find the kth to last element of a singly
     * linked list.
     * @param root
     * @param k
     * @return
     */
    public Node kthToLast(Node root, int k) {
        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.next;
        }
        for (int i = 0; i < k; i++) {
            if (stack.isEmpty()) {
                System.out.println("k is larger than the list.");
                throw new IllegalStateException();
            }
            stack.pop();
        }
        if (stack.isEmpty()) {
            System.out.println("k is larger than the list.");
            throw new IllegalStateException();
        }
        return stack.pop();
    }

    /**
     * Problem 2.3 - Delete Middle Node
     *
     * Implement an algorithm to delete a node in the middle of a singly linked
     * list, given only access to that node.
     * @param m
     */
    public void deleteMiddleNode(Node m) {
        if (m == null || m.next == null)
            return;
        Node next = m.next;
        m.data = next.data;
        m.next = next.next;
    }

    /**
     * Problem 2.4 - Partition
     *
     * Write code to partition a linked list around a value x, such that all
     * nodes less than x come before all nodes greater than or equal to x. If x
     * is contained within the list, the values of x only need to be after the
     * elements less than x.
     * @param root
     * @param x
     */
    public void partition(Node root, int x) {
        // TODO: Solve this problem.
    }

    /**
     * Problem 2.5 - Sum Lists
     *
     * You have two numbers represented by a linked list, where each node
     * contains a single digit. The digits are stored in reverse order, such
     * that the 1s digit is at the head of the list. Write a function that adds
     * the two numbers and returns the sum as a linked list.
     * @param list1
     * @param list2
     * @return
     */
    public int sumLists(Node list1, Node list2) {
        int num1 = listToInt(list1);
        int num2 = listToInt(list2);
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        return num1 + num2;
    }
    private int listToInt(Node list) {
        int result = 0;
        int place = 1;
        while (list != null) {
            result += (Integer) list.data * place;
            place *= 10;
            list = list.next;
        }
        return result;
    }

    /**
     * Problem 2.6 - Palindrome
     *
     * Implement a function to check if a linked list is a palindrome.
     * @param list
     * @return
     */
    public boolean isLinkedListPalindrome(Node list) {
        if (list == null)
            return false;
        Stack<Node> stack = new Stack<>();
        Node curNode = list;
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.next;
        }
        Node curRev = stack.pop();
        while (!stack.isEmpty()) {
            if (curRev.data == list.data) {
                list = list.next;
                curRev = stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Problem 2.7 - Intersection
     *
     * Given two (singly) linked lists, determine if the two lists intersect.
     * Return the intersecting node. Note that the intersection is defined
     * based on reference, not value. That is, if the kth node on the first
     * linked list is the exact same node (by reference) as the jth node of the
     * second linked list, then they are intersecting.
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int length1 = getLength(headA);
        int length2 = getLength(headB);
        if (length1 == 0 || length2 == 0)
            return null;
        int difference = Math.abs(length1 - length2);

        if (length1 > length2) {
            while (difference > 0) {
                headA = headA.next;
                difference--;
            }
        } else {
            while (difference > 0) {
                headB = headB.next;
                difference--;
            }
        }
        while (headA != null || headB != null) {
            if (headA == headB)
                return headA;
            if (headA.next == null) {
                if (headB.next == null) {
                    return null;
                } else {
                    return null;
                }
            } else {
                if (headB.next == null) {
                    return null;
                } else {
                    headA = headA.next;
                    headB = headB.next;
                }
            }
        }
        return null;
    }
    public int getLength(ListNode list) {
        int length = 0;
        while (list != null) {
            length++;
            list = list.next;
        }
        return length;
    }

    /**
     * Problem 2.8 - Loop Detection
     *
     * Given a circular linked list, implement an algorithm that returns the
     * node at the beginning of the loop.
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // Edge cases
        if (head == null) return null;
        if (head.next == null) return null;
        if (head.next == head) return head;
        if (head.next.next != null && head.next.next == head) return head;

        // Check existence of loop
        ListNode curNode = head;
        ListNode runner = hasLoop(head);
        if (runner == null) return null;
        while (runner != curNode) {
            curNode = curNode.next;
            runner = runner.next;
        }
        return curNode;
    }

    /**
     * This is a helper method for detectCycle(). It returns the node where a
     * loop was detected. Note: This is not where the loop starts, but rather
     * where in the iteration process was a presence of a loop determined.
     * @param head The start of the list.
     * @return Node where a loop was detected or null if no loop was detected.
     */
    public ListNode hasLoop(ListNode head) {
        ListNode curNode = head.next;
        ListNode runner = head.next.next;
        while (runner != null) {
            if (curNode == runner)
                break;
            if (runner.next == null || runner.next.next == null)
                return null;
            curNode = curNode.next;
            runner = runner.next.next;
        }
        return runner;
    }

    /**
     * Problem 3.1 - Three In One
     *
     * Describe how you could use a single array to implement three stacks.
     * @param <T>
     */
    class TripleStackArray<T> {
        int[] tripleStack;
        int capacity;
        int size1, size2, size3;    // Current size of each stack.
        int stack1, stack2, stack3;                // Start index for each stack.
        public TripleStackArray(int capacity) {
            this.capacity = capacity;
            size1 = 0;
            size2 = 0;
            size3 = 0;
            stack1 = 0;
            stack2 = 1;
            stack3 = 2;
        }

        public void pushOne(int data) {
            if (!isFull()) {
                if (size1 == 0) {
                    rightShiftTwo();
                } else {
                    rightShiftOne();
                }
                tripleStack[stack1] = data;
            } else {
                throw new IllegalStateException("Stack is full.");
            }
        }

        public void pushTwo(int data) {
            if (!isFull()) {
                if (size2 == 0) {
                    rightShiftThree();
                } else {
                    rightShiftTwo();
                    stack2--;
                }
                tripleStack[stack2] = data;
            } else {
                throw new IllegalStateException("Stack is full.");
            }
        }

        public void pushThree(int data) {
            if (!isFull()) {
                if (size3 == 0) {
                    tripleStack[stack3] = data;
                } else {
                    rightShiftThree();
                    stack3--;
                }
                tripleStack[stack3] = data;
            } else {
                throw new IllegalStateException("Stack is full.");
            }
        }

        public int popOne() {
            if (size1 == 0) throw new IllegalStateException("Stack 1 is empty.");
            int result = tripleStack[0];
            leftShiftOne();
            size1--;
            return result;
        }

        public int popTwo() {
            if (size2 == 0) throw new IllegalStateException("Stack 2 is empty.");
            int result = tripleStack[stack2];
            leftShiftTwo();
            size2--;
            return result;
        }

        public int popThree() {
            if (size3 == 0) throw new IllegalStateException("Stack 3 is empty.");
            int result = tripleStack[stack3];
            leftShiftThree();
            size3--;
            return result;
        }

        private boolean isFull() {
            return size1 + size2 + size3 >= capacity;
        }

        private boolean isEmpty() {
            return size1 + size2 + size3 == 0;
        }

        private void rightShiftOne() {
            if (isFull()) throw new IllegalStateException("Stack is full.");
            rightShiftTwo();
            if (size1 == 0) return;
            int[] stackOne = new int[size1];
            int j = 0;
            for (int i = stack1; i < stack1 + size1; i++) {
                stackOne[j] = tripleStack[i];
                j++;
            }
            j = 0;
            for (int i = stack1 + 1; i < stack1 + 1 + size1; i++) {
                tripleStack[i] = stackOne[j];
            }
        }

        private void rightShiftTwo() {
            if (isFull()) throw new IllegalStateException("Stack is full.");
            rightShiftThree();
            if (size2 == 0) return;
            int[] stackTwo = new int[size2];
            int j = 0;
            for (int i = stack2; i < stack2 + size2; i++) {
                stackTwo[j] = tripleStack[i];
                j++;
            }
            j = 0;
            for (int i = stack2 + 1; i < stack2 + 1 + size2; i++) {
                tripleStack[i] = stackTwo[j];
            }
            stack2++;
        }

        private void rightShiftThree() {
            if (isFull()) throw new IllegalStateException("Stack is full.");
            if (size3 == 0) return;
            int[] stackThree = new int[size3];
            int j = 0;
            for (int i = stack3; i < stack3 + size3; i++) {
                stackThree[j] = tripleStack[i];
                j++;
            }
            j = 0;
            for (int i = stack3 + 1; i < stack3 + 1 + size3; i++) {
                tripleStack[i] = stackThree[j];
            }
            stack3++;
        }

        private void leftShiftOne() {
            if (size1 == 0) throw new IllegalStateException("Stack 1 is empty.");
            for (int i = 1; i < size1; i++) {
                tripleStack[i - 1] = tripleStack[i];
            }
            leftShiftTwo();
        }

        private void leftShiftTwo() {
            if (stack2 == 0) {
                throw new IllegalStateException("Stack starts at index" +
                        "0. Can't shift left!");
            }
            if (size2 == 0) return;
            for (int i = stack2 + 1; i < size2; i++) {
                tripleStack[i - 1] = tripleStack[i];
            }
            leftShiftThree();
        }

        private void leftShiftThree() {
            if (stack3 == 0 || stack3 == stack2)
                throw new IllegalStateException("Stack starts at index" +
                        " 0. Can't shift left!");
            if (stack3 == stack2)
                throw new IllegalStateException("Stack 3 and stack 2 start" +
                        " at the same index. Can't shift left!");
            if (size3 == 0) return;
            for (int i = stack3 + 1; i < size3; i++) {
                tripleStack[i - 1] = tripleStack[i];
            }
        }
    }

    /**
     * Problem 3.2 - Stack Min
     *
     * How would you design a stack which, in addition to push and pop, has a
     * function min which returns the minimum element? Push, pop, and min
     * should all operate in O(1) time.
     */
    static class MinStack {
        Node head;
        Node minStack;

        void push(int data) {
            if (head == null) {
                head = new Node(data);
                minStack = new Node(data);
                return;
            }
            Node n = new Node(data);
            n.next = head;
            head = n;
            if (minStack != null) {
                int currentMin = minStack.data;
                if (data < currentMin) {
                    Node m = new Node(data);
                    m.next = minStack;
                    minStack = m;
                } else {
                    Node m = new Node(currentMin);
                    m.next = minStack;
                    minStack = m;
                }
            } else {
                minStack = new Node(data);
            }
        }

        int pop() {
            if (head == null) throw new IllegalStateException("Empty stack");
            int result = head.data;
            head = head.next;
            minStack = minStack.next;
            return result;
        }

        int min() {
            return minStack.data;
        }

        class Node {
            int data;
            Node next;
            Node(int data) {
                this.data = data;
                next = null;
            }
        }
    }

    /**
     * Problem 3.3 - Stack of Plates
     *
     * Status: TODO
     *
     * Imagine a (literal) stack of plates. If the stack gets too high, it
     * might topple. Therefore, in real life, we would likely start a new stack
     * when the previous stack exceeds some threshold. Implement a data
     * structure StackOfStacks that mimics this. StackOfStacks should be
     * composed of several stacks and should create a new stack once the
     * previous one exceeds capacity. StackOfStacks.push() and
     * StackOfStacks.pop() should behave identically to a single stack (that
     * is, pop() should return the same values as it would if there were just a
     * single stack). Implement a function popAt(int index) which performs a
     * pop operation on a specific sub-stack.
     */
    class StackOfStacks {

        Set<Stack> stackOfStacks;
        int stackCapacity;

        StackOfStacks(int stackCapacity) {
            this.stackCapacity = stackCapacity;
        }

        void push(StackNode n) {
            for (Stack stack : stackOfStacks) {

            }
        }

        private class StackNode<E> {
            E data;
            StackNode next;
            StackNode(E data) {
                this.data = data;
                this.next = null;
            }
        }

        private class Stack<T> {
            StackNode head;

            void push(StackNode n) {
                if (head == null) {
                    head = n;
                    return;
                }
                n.next = head;
                head = n;
            }

            StackNode pop() {
                StackNode result = new StackNode(head.data);
                head = head.next;
                return result;
            }
        }

    }

    /**
     * Problem 3.4 - Queue Via Stack
     *
     * Implement a QueueViaStack class which implements a queue using two
     * stacks.
     *
     * @param <T>
     */
    static class QueueViaStack<T> {

        Stack<T> front = new Stack<>();
        Stack<T> back = new Stack<>();

        void enqueue(T data) {
            front.push(data);
        }

        T dequeue() {
            if (back.isEmpty()) {
                while (!front.isEmpty()) {
                    back.push(front.pop());
                }
            }
            return back.pop();
        }
    }



}

class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
    }

    public boolean hasNext() {
        return next != null;
    }
}

class LinkedList {
    Node root;
    public LinkedList(Node root) {
        this.root = root;
    }

    public void add(Node n) {
        Node curNode = root;
        while (curNode.hasNext()) {
            curNode = curNode.next;
        }
        curNode.next = n;
    }

    public void toConsole() {
        Node curNode = root;
        System.out.print("[");
        while (curNode != null) {
            if (curNode.hasNext()) {
                System.out.print(curNode.data + " -> ");
            } else {
                System.out.print(curNode.data);
            }
            curNode = curNode.next;
        }
        System.out.println("]");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int[] arr) {
        val = arr[0];
        int[] arr2 = new int[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            add(arr[i]);
        }
    }
    void add(int i) {
        if (next == null) {
            next = new ListNode(i);
            return;
        }
        ListNode curNode = next;
        while (true) {
            if (curNode.next != null) {
                curNode = curNode.next;
            } else {
                break;
            }
        }
        curNode.next = new ListNode(i);
    }
    void createLoopStartingAt(int i) {
        ListNode curNode = next;
        while (true) {
            if (curNode.next != null) {
                curNode = curNode.next;
            } else {
                break;
            }
        }
        ListNode loopNode = next;
        while (i > 1) {
            loopNode = loopNode.next;
            i--;
        }
        curNode.next = loopNode;
    }

    void toConsole() {
        ListNode curNode = this;
        int timeOut = 100;
        while (timeOut > 0) {
            if (curNode.next != null) {
                System.out.print(curNode.val + " -> ");
            } else {
                System.out.println(curNode.val);
                break;
            }
            curNode = curNode.next;
            timeOut--;
        }
        if (timeOut == 0) {
            System.out.println(" ... ... ... ");
            System.out.println("A loop may have been detected.");
        }
    }
}




