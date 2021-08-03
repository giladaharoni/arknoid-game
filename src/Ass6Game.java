/*
name: Gilad Aharoni
Id: 318781127
 */

import biuoop.GUI;
import main.elements.levels.LevelInformation;
import main.elements.levels.Level1;
import main.elements.levels.Level2;
import main.elements.levels.Level3;
import main.elements.levels.Level4;
import main.elements.levels.GameFlow;
import main.elements.visual.elements.animation.AnimationRunner;

import java.util.LinkedList;
import java.util.List;

/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * the main class to run the final game.
 */
public class Ass6Game {

    /**
     * generates the list of levels.
     * @return levels list.
     */
    private static List<LevelInformation> levels() {
        List<LevelInformation> levels = new LinkedList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        return levels;
    }

    /***
     * parsing the args string array to the accurate list without the
     * ignorable strings.
     * @param args command line args.
     * @return list of integers.
     */
    private static List<Integer> parser(String[] args) {
        List<Integer> integers = new LinkedList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                integers.add(1);
            }
            if (args[i].equals("2")) {
                integers.add(2);
            }
            if (args[i].equals("3")) {
                integers.add(3);
            }
            if (args[i].equals("4")) {
                integers.add(4);
            }
        }
        return integers;
    }


    /**
     * the main method that initializes and run the game.
     * @param args contain the levels order or empty
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        List<LevelInformation> levelList = levels();
        List<LevelInformation> selected = new LinkedList<>();
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(),
                gui.getDrawSurface());
        if (args.length == 0) {
            gameFlow.runLevels(levelList);
        } else {
            List<Integer> integers = parser(args);
            for (int integer:integers) {
                selected.add(levelList.get(integer - 1));
            }
            if (!selected.isEmpty()) {
                gameFlow.runLevels(selected);
            } else {
                gameFlow.runLevels(levelList);
            }
        }
        gui.close();
    }
}
