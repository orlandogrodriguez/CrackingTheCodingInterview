package com.orlyrodz;

public class OrlyBST {
    OrlyBST left;
    OrlyBST right;
    int data;
    public OrlyBST(int data) {
        this.data = data;
    }

    public boolean contains(int data) {
        if (this.data == data) {
            return true;
        } else {
            if (data < this.data) {
                if (this.left != null) {
                    return this.left.contains(data);
                } else {
                    return false;
                }
            } else {
                if (this.right != null) {
                    return this.right.contains(data);
                } else {
                    return false;
                }
            }
        }
    }

    public void insert(int n) {
        if (n < data) {
            if (left != null) {
                left.insert(n);
            } else {
                left = new OrlyBST(n);
            }
        } else {
            if (right != null) {
                right.insert(n);
            } else {
                right = new OrlyBST(n);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(" " + this.data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPreOrder() {
        System.out.println(" " + data + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.println(" " + data + " ");
    }
}