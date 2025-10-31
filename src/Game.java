import processing.core.PApplet;

public class Game extends PApplet {
    Person bob1, bob2, bob3;

    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        Person bob = new Person(0);
        Person bob2 = new Person(1);
        Person bob3 = new Person(2);
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(0);    // paint screen white
        fill(0,255,0);
        bob1.update();
        bob2.update();
        bob3.update();
        bob1.draw(this);
        bob2.draw(this);
        bob3.draw(this);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
