package asm.userguide;

public interface Comparable extends Mesurable {

    public static final int less = -1;
    public static final int equal = 0;
    public static final int greater = 1;
    int compareTo(Object o);
}
