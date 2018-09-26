package com.orlyrodz;

import java.util.HashMap;

public class OrlyTrie {

    private Node root;

    private class Node {
        private HashMap<Character, Node> children;
        private String content;
        private boolean isWord;

        private HashMap<Character, Node> getChildren() {
            return children;
        }

        private void setEndOfWord(boolean b) {
            isWord = b;
        }
    }

    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new Node());
        }
        current.setEndOfWord(true);
    }
}
