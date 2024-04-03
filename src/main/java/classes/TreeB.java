package classes;

import java.util.Arrays;

public class TreeB {
    public int grades, maxValues;
    public int[] values;
    public TreeB[] children;

    public TreeB(int grades) {
        this.grades = grades;
        this.maxValues = grades - 1;
        this.values = new int[this.maxValues];
        this.children = new TreeB[grades];
    }

    public TreeB find(int key) {
        int i = 0;

        for (i = 0; i < this.maxValues; i++) {
            if (key < this.values[i] || this.values[i] == 0) {
                break;
            }
            if (key == this.values[i]) {
                return this;
            }
        }
        if (this.isLeave() || this.children[i] == null) {
            return null;
        } else {

            return this.children[i].find(key);
        }

    }

    public void insert(int value) {

        if (this.isFull() && this.isLeave()) {
            TreeB newTree = new TreeB(this.grades);
            newTree.children[0] = this;
            newTree.splitChild(0);

            this.values = newTree.values;
            this.children = newTree.children;

            this.insert(value);
            return;
        }
        int i = 0;

        while (i < (this.maxValues - 1) && this.values[i] < value && this.values[i] != 0) {

            i++;
        }
   

        if (this.isLeave()) {

            this.pushValue(value, i);
        } else {
            TreeB selectecChild = this.children[i];
            if (selectecChild.isFull()) {
                this.splitChild(i);
                if (value > this.values[i]) {
                    i++;
                }
            }
            this.children[i].insert(value);
        }
    }

    public boolean isLeave() {
        return this.lengthChild() == 0;
    }

    public boolean isFull() {
        return this.lengthValues() >= maxValues;
    }

    private int lengthValues() {
        int count = 0;
        for (int el : this.values)
            if (el != 0)
                ++count;
        return count;
    }

    private int lengthChild() {
        int count = 0;
        for (TreeB el : this.children)
            if (el != null)
                ++count;
        return count;
    }

    private void pushValue(int value, int index) {
        int newIndex = 0;
        int[] oldValues = this.values.clone();
        TreeB[] oldChild = this.children.clone();
        for (int i = 0; i < (this.values.length); i++) {

            if (i == index) {
                this.values[i] = value;
                this.children[i + 1] = null;
            } else {
                this.values[i] = oldValues[newIndex];
                this.children[i + 1] = oldChild[newIndex + 1];
                newIndex++;
            }
        }
    }

    private void pushChild(TreeB child, int index) {
        int newIndex = 0;
        TreeB[] oldChild = this.children.clone();

        for (int i = 0; i < (this.children.length - 1); i++) {
            if (i == index) {
                this.children[i] = child;
            } else {
                this.children[i] = oldChild[newIndex];
                newIndex++;
            }
        }
    }

    public void splitChild(int childPosition) {
        TreeB child = this.children[childPosition];
        TreeB left = new TreeB(this.grades);
        TreeB rigth = new TreeB(this.grades);
        int midPosition = (int) Math.ceil(this.maxValues / 2);

        int midValue = child.values[midPosition];

        left.values = Arrays.copyOf(Arrays.copyOfRange(child.values, 0, midPosition), this.maxValues);
        rigth.values = Arrays.copyOf(Arrays.copyOfRange(child.values, (midPosition + 1), this.maxValues),
                this.maxValues);

        left.children = Arrays.copyOf(Arrays.copyOfRange(child.children, 0, midPosition + 1), this.grades);
        rigth.children = Arrays.copyOf(Arrays.copyOfRange(child.children, (midPosition + 2), this.grades), this.grades);

        this.pushValue(midValue, (childPosition));
        this.children[childPosition] = left;
        this.pushChild(rigth, childPosition + 1);

    }

    public void display() {
        this.display(0);
    }

    private void display(int level) {

        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        System.out.println("Valores:" + Arrays.toString(this.values));

        for (int i = 0; i < this.children.length; i++) {

            if (this.children[i] != null) {
                for (int ii = 0; ii < level; ii++) {
                    System.out.print("-");
                }
                System.out.println("hijo #" + (i + 1));
                this.children[i].display(level + 1);
            }
        }

    }
}
