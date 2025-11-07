import processing.core.PApplet;
import java.util.ArrayList;

public class Population {
    private ArrayList<Person> persons;
    private int popImmunity;
    private int infectionDuration;

    public Population(ArrayList<Person> list, int immunity, int infectionDuration) {
        persons = list;
        popImmunity = immunity;
        this.infectionDuration = infectionDuration;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void infectAll() {
        for (int i = 0; i < persons.size(); i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                Person a = persons.get(i);
                Person b = persons.get(j);
                a.isInfectedBy(b);
                b.isInfectedBy(a);
            }
        }
    }

    public double getHealthyPercent() {
        double healthy = 0;
        for (Person p : persons) {
            if (p.getStatus() == 0) healthy++;
        }
        return healthy/persons.size();
    }

    public double getInfectedPercent() {
        double infected = 0;
        for (Person p : persons) {
            if (p.getStatus() == 1) infected++;
        }
        return infected/persons.size();
    }

    public double getCuredPercent() {
        double cured = 0;
        for (Person p : persons) {
            if (p.getStatus() == 2) cured++;
        }
        return cured /persons.size();
    }

    public double getDeadPercent() {
        double dead = 0;
        for (Person p : persons) {
            if (p.getStatus() == 3) dead++;
        }
        return dead /persons.size();
    }

        public void updateAll () {
            for (Person p : persons) {
                p.update();
                p.tickInfection();

                if (p.getStatus() == 1 && p.getCounter() >= infectionDuration) {
                    int chance = (int) (Math.random() * 100);
                    if (chance > p.getImmunity()) {
                        p.setStatus(3); // dead
                    } else {
                        p.setStatus(2); // cured
                    }
                    p.resetCounter();
                }
            }
        }

        public void displayAll (PApplet window){
            for (Person p : persons) {
                p.draw(window);
            }
        }
    }
