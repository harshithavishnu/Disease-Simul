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

        Slider popSlider     = new Slider(650, 50, 0.3f); // population size
        Slider immunitySlider = new Slider(750, 50, 0.5f); // avg immunity

        sliders.add(popSlider);
        sliders.add(immunitySlider);
    }

    public void draw() {
        background(0);

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
        fill(255);
        noStroke();
        rect(600, 0, 200, 600);

        for (Slider s : sliders) {
            s.draw(this);
        }

        fill(0);
        textSize(18);
        textAlign(LEFT);
        text("Population: " + (int)getPopulationSliderValue() + " people", 605, 430);
        text("Avg Immunity: " + (int)getImmunitySliderValue() + "%", 605, 460);

        textSize(18);
        text("Pop Size", 610, 30);
        text("Immunity", 705, 30);


        int totalPeople = getPopulationSliderValue();
        int initiallyInfected = totalPeople / 4;
        int avgPopImmunity = getImmunitySliderValue();

        if (people.size() != totalPeople) {
            people.clear();
            for (int i = 0; i < totalPeople; i++) {
                Person p;
                if (i < initiallyInfected) {
                    p = new Person(1);
                    p.infectionTime = 0;
                    p.timeToDeath = (int)(Math.random() * 200 + 200);
                } else {
                    p = new Person(0);
                }
                p.setImmunity((int)(Math.random() * avgPopImmunity));
                people.add(p);
            }
            population = new Population(people, avgPopImmunity);
        }


        for (Person p : people) {
            p.update();
            p.draw(this);
        }
        stroke(255);
        line(0, 600, width, 600);
        noStroke();
        chart.update(population);
        chart.draw(this);

    }

    public int getPopulationSliderValue() {
        Slider s = sliders.get(0);
        return (int) (s.getValue()*190+10);
    }

    public int getImmunitySliderValue() {
        Slider s = sliders.get(1);
        return (int)(s.getValue()*100);
    }

    public void mousePressed() {
        if (state == 0) {
            if (mouseX > width/2 - 100 && mouseX < width/2 + 100 &&
                    mouseY > height/2 && mouseY < height/2 + 80) {
                state = 1;

                people.clear();

                int totalPeople = getPopulationSliderValue();
                int initiallyInfected = totalPeople / 4;

                int avgPopImmunity = getImmunitySliderValue();

                for (int i = 0; i < totalPeople; i++) {

                    Person p;

                    if (i < initiallyInfected) {
                        p = new Person(1);
                        p.infectionTime = 0;
                        p.timeToDeath = (int)(Math.random() * 200 + 200);
                    } else {
                        p = new Person(0);
                    }

                    p.setImmunity((int)(Math.random() * avgPopImmunity));

                    people.add(p);
                }

                population = new Population(people, avgPopImmunity);

            }
        } else if (state == 1) {
            for (Slider s : sliders) s.mousePressed(this);
        }
    }

    public void mouseDragged() {
        if (state == 1) {
            for (Slider s : sliders) s.mouseDragged(this);
        }
    }

    public void mouseReleased() {
        if (state == 1) {
            for (Slider s : sliders) s.mouseReleased(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}


