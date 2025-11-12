import processing.core.PApplet;
import java.util.ArrayList;

public class Game extends PApplet {
    Population population;
    int state = 0;
    ArrayList<Person> people = new ArrayList<>();
    BarChart chart = new BarChart();
    ArrayList<Slider> sliders = new ArrayList<>();

    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        frameRate(30);
        int totalPeople = 70;
        int initiallyInfected = 20;

        for (int i = 0; i < totalPeople; i++) {
            if (i < initiallyInfected) {
                Person p = new Person(1);
                p.infectionTime = 0;
                p.timeToDeath = (int) (Math.random() * 200 + 200); // 200–400 frames
                people.add(p);// infected ppl
            } else {
                people.add(new Person(0)); // healthy ppl
            }
        }
        population = new Population(people,  40);

        for (int i = 0; i < 3; i++) {
            Slider s = new Slider(580 + 600 * i, 20);
            sliders.add(s);
        }
    }

    public void draw() {
        background(0);    // paint screen white

        if (state == 0) {
            drawStartScreen();
        } else if (state == 1) {
            drawSimulation();
            population.infectAll();
            population.updateAll();
            population.displayAll(this);
        }
    }

    public void drawStartScreen() {
        background(30);

        for (int i = 0; i < 100; i++) {
            float px = random(width);
            float py = random(height);
            fill(255, 100);
            noStroke();
            ellipse(px, py, 5, 5);
        }

        textAlign(CENTER);
        textSize(50);
        fill(0);
        text("Disease Simulation", width/2 + 5, height/2 - 95);
        fill(255);
        text("Disease Simulation", (float) width /2, (float) height /2 - 100);


        // draw start button
        fill(0, 150, 255);
        rect((float) width /2 - 100, (float) height /2, 200, 80, 20);
        fill(255);
        textSize(32);
        text("Start", (float) width /2, (float) height /2 + 50);
    }

    public void drawSimulation() {
        background(0);
        for (Person p : people) {
            p.update();
            p.draw(this);
        }
        stroke(255);
        line(0, 600, width, 600);  // separates simulation and chart area
        noStroke();
        chart.update(population);
        chart.draw(this);
    }

    public void mousePressed() {
        if (state == 0) {
            // check if user clicked inside start button
            if (mouseX > width / 2 - 100 && mouseX < width / 2 + 100 &&
                    mouseY > height / 2 && mouseY < height / 2 + 80) {
                state = 1; // start simulation
                fill(0, 200, 255);
            } else {
                fill(0, 150, 255);
            }
            rect(width / 2 - 100, height / 2, 200, 80, 20);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}


