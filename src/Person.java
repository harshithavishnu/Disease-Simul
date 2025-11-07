import processing.core.PApplet;

public class Person extends PApplet{
    private float x;
    private float y;
    private float xSpeed, ySpeed;
    private float size;
    private int status;// 0= healthy, 1 = infected, 2 = cured, 3 = dead
    private int counter;
    private int immunity;
    private int gray = color(128, 128, 128);
    private int green = color(0,255,0);
    private int red = color(255,0,0);
    private int blue = color(0, 0, 255);

    public Person(int status) {//remember to have method in population to change immunity
        x = (float)(Math.random()*790+5);
        y = (float)(Math.random()*790+5);
        xSpeed = 3;
        ySpeed = 3;
        size = 7;
        this.status = status;
        counter = 0;
        if(status == 0) {
            immunity = 25;
            fill(green);
        }
        if (status == 2){
            immunity = 50;
            fill(blue);
        }
        if (status == 1){
            immunity = 0;
            fill(red);
        }

        else{
            immunity = 0;
            fill(gray);
        }
    }

    public Person(){
        x = (float)(Math.random()*790+5);
        y = (float)(Math.random()*790+5);
        xSpeed = 3;
        ySpeed = 3;
        size = 7;
        status = 0;
        counter = 0;
        immunity = 25;
        fill(green);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if(status == 0) {
            this.setImmunity(50);
        }
        if (status == 2){
            this.setImmunity(75);
        }
        else{
            this.setImmunity(0);
        }
    }


    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public boolean isCollidingWith(Person other) {
        float combinedRad = this.size + other.size;
        float Dist = dist(this.x, this.y, other.x, other.y);
        return (Dist < combinedRad);
    }

    public boolean isInfectedBy(Person other){
        if ((this.isCollidingWith(other)) && (other.getStatus()==1)){
            int probability = (int) (Math.random()*100);
            if (probability > immunity){
                this.setStatus(1);
                return true;
            }
        }
        return false;
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
        if (this.getStatus() == 1 && counter==10) {
            int chanceOfDeath = (int) (Math.random() * 100);
            if (chanceOfDeath > immunity) {
                this.setStatus(3);
            }
        }
        if (this.getStatus() == 1 && counter < 10){
            fill(red);
            counter++;
        }
        if (this.getStatus()==2){
            fill(blue);
        }
        if (this.getStatus()==3){
            fill(gray);
            xSpeed = 0;
            ySpeed = 0;
        }
    }

    public void draw(PApplet window){
        window.ellipse(x, y, size, size);
    }

}
