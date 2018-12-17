package com.saayman.advent2018.day8;

import java.util.ArrayList;
import java.util.List;

public class NodeTree {

    public Node root;

    public int sumMetaEntries() {
        int rootSum = root.sumMetaEntries();
        for(Node child: root.children) {
            rootSum = rootSum + sumSubTree(child);
        }
        return rootSum;
    }

    private int sumSubTree(Node child) {
        if(child.children.isEmpty()) {
            return child.sumMetaEntries();
        } else {
            int sum = child.sumMetaEntries();
            for(Node subChild: child.children) {
                sum = sum + sumSubTree(subChild);
            }
            return sum;
        }
    }


    public class Node {

        public int value() {
            if (totalChildren == 0) {
                return valueNoChildren();
            } else {
                return valueWhenChildrenExist();
            }
        }

        public int valueWhenChildrenExist() {
            int sum = 0;
            for (Integer metaData : metaEntries) {
                if (invalidChildIndex(metaData)) {
                    continue;
                } else {
                    sum += children.get(metaData - 1).value();
                }
            }
            return sum;
        }

        public boolean invalidChildIndex(Integer metaData) {
            return metaData < 1 || metaData > totalChildren;
        }

        public int valueNoChildren() {
            int sum = 0;
            for (Integer metaData : metaEntries) {
                sum += metaData;
            }
            return sum;
        }

        public Node(Node parent, List<Integer> input, int currentPosition) {
            //Root
            children = new ArrayList<>();
            metaEntries = new ArrayList<>();

            if (parent != null) {
                parent.children.add(this);
            }
            totalChildren = input.get(currentPosition);
            totalMetaEntries = input.get(currentPosition+1);
        }

        @Override
        public String toString() {
            return "Node{" + "\n" +
                    "totalChildren=" + numChildren() +  "\n" +
                    "totalMetaEntries=" + numMetaEntries() +  "\n" +
                    "children=" + children +
                    ", metaEntries=" + metaEntries +  "\n" +
                    '}';
        }

        public int numMetaEntries() {
            return totalMetaEntries;
        }

        public int numChildren() {
            return totalChildren;
        }

        int totalChildren;
        int totalMetaEntries;
        List<Node> children;
        List<Integer> metaEntries;

        public int sumMetaEntries() {
            return metaEntries.stream().mapToInt(Integer::intValue).sum();
        }

        public int buildChildren(List<Integer> input, int currentPosition) {
            currentPosition += 2;
            for(int childCounter = 0; childCounter < this.totalChildren; childCounter++) {
                Node child = new Node(this, input, currentPosition);
                currentPosition = child.buildChildren(input, currentPosition);
            }
            for (int i = 0; i < totalMetaEntries; i++) {
                int m = input.get(currentPosition++);
                metaEntries.add(m);
            }
            return currentPosition;
        }

    }

    public NodeTree(String input) {
        List<Integer> integerList = Utils.parse(input);
        //Enclosing Node - metaData is always at the end.
        root = new Node(null, integerList, 0);
        root.buildChildren(integerList, 0);
    }

    @Override
    public String toString() {
        return "NodeTree{" +
                "root=" + root +
                '}';
    }
}
