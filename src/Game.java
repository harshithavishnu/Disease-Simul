import processing.core.PApplet;
import java.util.ArrayList;

public class Game extends PApplet {
    Population population;

    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        frameRate(30);
        ArrayList<Person> people = new ArrayList<>();
        int totalPeople = 40;
        int initiallyInfected = 20;

        for (int i = 0; i < totalPeople; i++) {
            if (i < initiallyInfected) {
                people.add(new Person(1)); // infected
            } else {
                people.add(new Person(0)); // healthy
            }
        }
        population = new Population(people, 40, 80);
    }

    public void draw() {
        background(0);    // paint screen white
        population.infectAll();
        population.updateAll();
        population.displayAll(this);

    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
