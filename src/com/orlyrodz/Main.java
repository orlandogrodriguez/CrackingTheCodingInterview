package com.orlyrodz;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        CrackingTheCodingInterview test = new CrackingTheCodingInterview();

        CrackingTheCodingInterview.QueueViaStack<Integer> queueviastack = new CrackingTheCodingInterview.QueueViaStack<>();

        queueviastack.enqueue(1);
        queueviastack.enqueue(2);
        queueviastack.enqueue(3);

        System.out.print(queueviastack.dequeue() + " ");
        System.out.print(queueviastack.dequeue() + " ");
        System.out.print(queueviastack.dequeue() + " ");

    }
}
