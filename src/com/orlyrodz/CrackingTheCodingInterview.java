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
}




