import processing.core.PApplet;
import processing.javafx.PSurfaceFX;

public class Slider {
    private float x, y;
    private float height;
    private float radius;
    private float value;
    private boolean isDragged;

    public Slider(float x, float y, float value) {
        this.x = x; //make x = 580 for the first one
        this.y = y;
        height = 300;
        radius = 15;
        this.value = value;
        isDragged = false;

    }

    public float getValue() {
        return value;
    }

    public void draw(PApplet window) {
        window.stroke(0);
        window.strokeWeight(5);
        window.line(x, y, x, y+height); //ideal placement is (580, 20, 580, 20+300)

        //draw the circle to move slider
        float knobY = y + value * height;
        window.fill(200);
        window.stroke(0);
        window.ellipse(x, knobY, radius, radius);
        }


    public void mousePressed1(PApplet window) {
        float knobY = y + value * height;
    // check if mouse is within the radius of the ellipse
        float distance = window.dist(window.mouseX, window.mouseY, x, knobY);
        if (distance < radius) {
            isDragged =  true;
        }
    }

    public void mouseDragged1(PApplet window){
        if (isDragged){
            //clamp to mouse!!
            float newY = window.mouseY;

            if (newY < y) newY = y;
            if (newY > y + height) newY = y + height;

            value = (newY - y) / height;
        }
    }

    public void mouseReleased1(PApplet window){
        isDragged = false;
    }
}
