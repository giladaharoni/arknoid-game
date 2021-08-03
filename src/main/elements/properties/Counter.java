package main.elements.properties;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * the counter class.
 * @author gilad Aharoni
 *id:318781127
 */
public class Counter {
    private int value;

    /**
     * Main Constructor.
     */
    public Counter() {
        value = 0;
    }
    /**
     * add number to current count.
     * @param number the value to add.
     */
    public void increase(int number) {
        value = value + number;
    }

    /**
     * subtract number from current count.
     * @param number the value to subtract.
     */
    public void decrease(int number) {
        value = value - number;
    }

    /**
     * get current count.
     * @return current count.
     */
    public int getValue() {
        return value;
    }
}
