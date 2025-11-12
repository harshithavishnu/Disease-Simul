import processing.core.PApplet;
import processing.javafx.PSurfaceFX;

public class Slider {
    private float x, y;
    private float width, height;
    private float radius;
    private boolean isClicked;
    private boolean isDragged;

    public Slider(float x, float y) {
        this.x = x; //make x = 580 for the first one
        this.y = y; // make y = 20 for all
        height = 300;
        radius = 15;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void draw(PApplet window) {
        window.stroke(0);
        window.strokeWeight(5);
        window.line(x, y, x, y+height); //ideal placement is (580, 20, 580, 20+300)

        //draw the circle to move slider
        window.ellipse(x, y, radius, radius);
        }


    public void mousePressed(PApplet window) {
    // check if mouse is within the radius of the ellipse
        float distance = (float) Math.sqrt(Math.pow((window.mouseX - x), 2) + Math.pow((window.mouseY-  y), 2));
        if (distance <= radius) {
            isClicked =  true;
        }
    }

    public void mouseDragged(PApplet window){
        if (isClicked && window.mouseY != window.pmouseY){

        }
    }

    public void mouseReleased(PApplet window){
        if ()

    }
}
