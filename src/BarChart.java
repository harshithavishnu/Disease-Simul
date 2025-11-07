import processing.core.PApplet;

import java.util.ArrayList;

public class BarChart extends PApplet{
    private float x, y;
    private float width, height;
    private double healthyPercent = 0, infectedPercent = 0, curedPercent = 0, deadPercent = 0;

    public BarChart(){
        x = 15;
        y = 400;
        width = 20;
        height = 200;
    }

    public double getDeadPercent() {
        return deadPercent;
    }

    public double getCuredPercent() {
        return curedPercent;
    }

    public double getInfectedPercent() {
        return infectedPercent;
    }

    public double getHealthyPercent() {
        return healthyPercent;
    }

    public void update(Population people){
        int healthy = 0, infected = 0, cured = 0, dead = 0;
        healthyPercent = people.getHealthyPercent();
        infectedPercent = people.getInfectedPercent();
        curedPercent = people.getCuredPercent();
        deadPercent = people.getDeadPercent();

//        for (Person p: humans){
//            if (p.getStatus() == 0) healthy++;
//            if (p.getStatus() == 1) infected++;
//            if (p.getStatus() == 2) cured++;
//            else dead++;
//        }
//        double noOfPeople = healthy + infected + cured + dead;
//        healthyPercent = healthy/noOfPeople;
//        infectedPercent = infected/noOfPeople;
//        curedPercent = cured/noOfPeople;
//        deadPercent = dead/noOfPeople;
    }

    public void draw(PApplet window, double percent){
        rect( width /2 - 100,  height /2, width, height* (float)(percent));
        fill(255);
    }
}
