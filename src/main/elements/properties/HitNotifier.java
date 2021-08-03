package main.elements.properties;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * an assistance interface that connects listener to the wanted object.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl listener.
     */
    void removeHitListener(HitListener hl);
}
