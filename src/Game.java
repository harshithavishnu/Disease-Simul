import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Person> people = new ArrayList<>();

    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        frameRate(10);
        for (int i = 0; i < 4; i++) {
            Person bob = new Person(i);
            people.add(bob);
        }

    }

    /***
     * Draws each frame to the screen. Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(0);    // paint screen white

        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < people.size(); j++) {
                Person b = people.get(i);
                Person c = people.get(i);
                b.isInfectedBy(c);
                b.update();
                b.draw(this);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
