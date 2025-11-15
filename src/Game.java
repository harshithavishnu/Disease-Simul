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

        Slider popSlider     = new Slider(640, 50, 0.3f); // population size
        Slider immunitySlider = new Slider(760, 50, 0.5f); // avg immunity
        Slider socialSlider = new Slider(700, 50, 0.5f); // vertical slider

        sliders.add(popSlider);
        sliders.add(immunitySlider);
        sliders.add(socialSlider);

        int totalPeople = 70;
        int initiallyInfected = 20;

        population = new Population(people,  40);

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

        int newBehavior = getSocialActivitySliderValue();
        for (Person p : people) {
            p.setBehavior(newBehavior);
        }


        fill(0);
        textSize(17);
        textAlign(LEFT);
        text("Population: " + (int)getPopulationSliderValue() + " people", 603, 430);
        text("Avg Immunity: " + (int)getImmunitySliderValue() + "%", 603, 460);
        text("Sociability: " + (int)getSocialActivitySliderValue(), 603, 490);
        textSize(15);
        text("    0: Least Social", 603, 510);
        text("    1: Normal", 603, 530);
        text("    2: Outgoing", 603, 550);


        textSize(18);
        text("Pop Size", 610, 30);
        text("Immunity", 715, 30);
        text("Sociability", 660, 380);


        int totalPeople = getPopulationSliderValue();
        int initiallyInfected = totalPeople / 4;
        int avgPopImmunity = getImmunitySliderValue();

        if (people.size() != totalPeople) {
            people.clear();
            for (int i = 0; i < totalPeople; i++) {
                Person p;
                if (i < initiallyInfected) {
                    p = new Person(1, getSocialActivitySliderValue());
                    p.infectionTime = 0;
                    p.timeToDeath = (int)(Math.random() * 200 + 200);
                } else {
                    p = new Person(0, getSocialActivitySliderValue());
                }
                p.setImmunity((int)(Math.random() * avgPopImmunity));
                people.add(p);
            }
            population = new Population(people, avgPopImmunity);
        }else {
            for (Person p : people) {
                p.update();
                p.draw(this);
            }
        }

        population.updateAll();
        population.displayAll(this);

        stroke(255);
        line(0, 600, width, 600);
        noStroke();
        chart.update(population);
        chart.draw(this);

        population.infectAll();

    }

    public int getPopulationSliderValue() {
        Slider s = sliders.get(0);
        return (int) (s.getValue()*190+10);
    }

    public int getImmunitySliderValue() {
        Slider s = sliders.get(1);
        return (int)(s.getValue()*100);
    }

    public int getSocialActivitySliderValue() {
        float val = sliders.get(2).getValue(); // 0–1
        if (val < 0.33f) return 0;   // shy
        else if (val < 0.66f) return 1; // normal
        else return 2;                 // social
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
            }
        } else if (state == 1) {
            for (Slider s : sliders) s.mousePressed1(this);
        }
    }

    public void mouseDragged() {
        if (state == 1) {
            for (Slider s : sliders) s.mouseDragged1(this);
        }
    }

    public void mouseReleased() {
        if (state == 1) {
            for (Slider s : sliders) s.mouseReleased1(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}


