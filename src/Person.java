import processing.core.PApplet;

public class Person{
    private float x, y;
    private float xSpeed, ySpeed;
    private float size;
    private int status;// 0= healthy, 1 = infected, 2 = cured, 3 = dead
    private int counter;
    private int immunity;
    int infectionTime;
    int timeToDeath;


    public Person(int status) {//remember to have method in population to change immunity
        this.status = status;
        x = (float) (Math.random() * 790 + 5);
        y = (float) (Math.random() * (800 - 200 - 10) + 5);
        xSpeed = (float)(Math.random() * 3 - 1.5);
        ySpeed = (float)(Math.random() * 3 - 1.5);
        size = 7;
        counter = 0;
        if (status == 0) immunity = (int)(Math.random() * 50);
        else if (status == 2) immunity = 50;
        else immunity = 0;
        infectionTime = 0;
        if (status == 1) {
            timeToDeath = (int)(Math.random() * 400 + 300); // 300–700

        }

    }

    public Person() {
        x = (float) (Math.random() * 790 + 5);
        y = (float) (Math.random() * 790 + 5);
        xSpeed = (float)(Math.random() * 3 - 1.5);
        ySpeed = (float)(Math.random() * 3 - 1.5);
        size = 7;
        status = 0;
        counter = 0;
        immunity = (int)(Math.random() * 50);
        infectionTime = 0;
    }

    public int getStatus() {
        return status;
    }

    public int getImmunity() {
        return immunity;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getCounter() {
        return counter;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public void tickInfection() {
        if (status == 1) counter++;
    }

    public void resetCounter() {
        counter = 0;
    }

    public void boostImmunityAfterRecovery() {
        immunity *= 2;  // double immunity after being cured
    }


    public boolean isCollidingWith(Person other) {
        float dist = PApplet.dist(this.x, this.y, other.x, other.y);
        return dist < this.size + other.size;
    }

    public boolean isInfectedBy(Person other) {
        if ((this.status == 0 || this.status == 2) && other.status == 1 && isCollidingWith(other)) {
            int chance = (int) (Math.random() * 100);
            if (chance >= immunity) {
                this.status = 1;
                infectionTime = 0;
                timeToDeath = (int)(Math.random() * 400 + 300); // 300–700
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (status == 3) return;

        x = x + xSpeed;
        y = y + ySpeed;
        if (x < 0) {
            x = 0;
            xSpeed = -xSpeed;
        }

        if (x > 600 - size/2) {
            x = 600 - size/2;
            xSpeed = -xSpeed;
        }
        if (y - size / 2 < 0 || y + size / 2 > 800 - 200) ySpeed = -ySpeed;

        if (status == 1) {
            infectionTime++;

            // Check if infection duration is over
            if (infectionTime >= timeToDeath) {  // infectionDuration can be a class field or passed from Population
                int deathChance = 50; // 50% die, adjust as needed
                if (Math.random() * 100 < deathChance) {
                    status = 3; // dead
                } else {
                    status = 2; // cured
                    boostImmunityAfterRecovery(); // double immunity for reinfection
                }
            }
        }

    }

        public void draw (PApplet window){
            if (status == 0) window.fill(0, 255, 0); //healthy
            else if (status == 1) window.fill(255, 0, 0); // infected
            else if (status == 2) window.fill(0, 0, 255); // cured
            else window.fill(128); //dead
            window.ellipse(x, y, size, size);
        }

    }
