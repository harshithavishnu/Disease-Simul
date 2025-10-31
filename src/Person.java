import processing.core.PApplet;

public class Person extends PApplet{
    private float x;
    private float y;
    private float xSpeed, ySpeed;
    private float size;
    private int status;// 0= healthy, 1 = infected, 2 = cured, 3 = dead
    private int counter;
    private int immunity;

    public Person(int status) {//remember to have method in population to change immunity
        x = (float)(Math.random()*790+5);
        y = (float)(Math.random()*790+5);
        xSpeed = 20;
        ySpeed = 20;
        size = 10;
        this.status = status;
        counter = 0;
        immunity = 50;
    }

    public Person(){
        x = (float)(Math.random()*790+5);
        y = (float)(Math.random()*790+5);
        xSpeed = 20;
        ySpeed = 20;
        size = 10;
        status = 0;
        counter = 0;
        immunity = 50;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isCollidingWithInfected(Person other) {
        if (this.getStatus() == 0 || this.getStatus() == 2) {
            return other.getStatus() == 1;
        }
        return false;
    }

    public void isInfectedBy(Person other){
        if (this.isCollidingWithInfected(other)){
            int probability = (int) (Math.random()*100);
            if (probability > immunity){
                this.setStatus(1);
            }
        }
    }

    public void isDead(){
        if(this.getStatus()==1 && counter == 10){
            int chanceOfDeath = (int) (Math.random()*100);
            if (chanceOfDeath > immunity){
                this.setStatus(3);
        }
            else {
                this.setStatus(2);
            }
        }
    }

    public void update() {
        x = x + xSpeed;
        y = y + ySpeed;
        if (x < 0) xSpeed = -xSpeed;
        if (x > 800) xSpeed = -xSpeed;
        if (y < 0)ySpeed = -ySpeed;
        if (y > 800) ySpeed = -ySpeed;
        counter++;
    }

    public void draw(PApplet window){
        window.ellipse(x, y, size, size);
    }

}
