import processing.core.PApplet;
import java.util.ArrayList;

public class Population {
    private ArrayList<Person> persons;
    private int popImmunity;

    public Population(ArrayList<Person> list, int immunity) {
        persons = list;
        popImmunity = immunity;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void infectAll() {
        for (Person a : persons) {
            if (a.getStatus() != 1) continue; // only infected can infect
            int infectedThisFrame = 0;
            for (Person b : persons) {
                if (infectedThisFrame >= 1) break; // only infect one person per frame
                if (b.getStatus() == 0 && a.isCollidingWith(b)) {
                    if (b.isInfectedBy(a)) infectedThisFrame++;
                }
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
            }
        }

        public void displayAll (PApplet window){
            for (Person p : persons) {
                p.draw(window);
            }
        }
    }
