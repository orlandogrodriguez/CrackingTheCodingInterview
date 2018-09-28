package com.orlyrodz;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        CrackingTheCodingInterview test = new CrackingTheCodingInterview();

        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new AnimalShelter.Dog("Enzo"));
        shelter.enqueue(new AnimalShelter.Dog("Ginger"));
        shelter.enqueue(new AnimalShelter.Cat("Kiwi"));
        shelter.enqueue(new AnimalShelter.Cat("Giovi"));
        shelter.enqueue(new AnimalShelter.Dog("Sami"));
        shelter.enqueue(new AnimalShelter.Dog("Lucy"));


        System.out.println(shelter.dequeueCat().name);
        System.out.println(shelter.dequeueDog().name);
        System.out.println(shelter.dequeueDog().name);
        System.out.println(shelter.dequeueAny().name);
        System.out.println(shelter.dequeueDog().name);
        System.out.println(shelter.dequeueCat().name);
    }
}
