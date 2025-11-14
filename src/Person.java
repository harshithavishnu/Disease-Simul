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
    private int behavior; //0 = shy, 1 = nomral, 2 = social
    private float speedMultiplier; // speeed
    private float infectionMultiplier;



    public Person(int status, int behaviour) {//remember to have method in population to change immunity
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
        this.behavior = behavior;
        if (behavior == 0) { speedMultiplier = 0.5f; infectionMultiplier = 0.5f; }
        else if (behavior == 1) { speedMultiplier = 1.0f; infectionMultiplier = 1.0f; }
        else { speedMultiplier = 1.5f; infectionMultiplier = 1.5f; }
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


    public void updateBehavior(float socialActivity) {
        float rand = (float)Math.random();
        if (rand < socialActivity * 0.33) {
            behavior = 0;
            speedMultiplier = 0.5f;
            infectionMultiplier = 0.5f;
        } else if (rand < socialActivity * 0.66) {
            behavior = 1;
            speedMultiplier = 1.0f;
            infectionMultiplier = 1.0f;
        } else {
            behavior = 2;
            speedMultiplier = 1.5f;
            infectionMultiplier = 1.5f;
        }
    }

    public boolean isCollidingWith(Person other) {
        float dist = PApplet.dist(this.x, this.y, other.x, other.y);
        return dist < this.size + other.size;
    }

    public boolean isInfectedBy(Person other) {
        if ((status == 0 || status == 2) && other.status == 1 && isCollidingWith(other)) {
            int chance = (int)(Math.random() * 100);
            if (chance > immunity / infectionMultiplier && chance < 80 * infectionMultiplier) {
                status = 1;
                infectionTime = 0;
                timeToDeath = (int)(Math.random() * 400 + 300);
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (status == 3) return;

        x += xSpeed * speedMultiplier;
        y += ySpeed * speedMultiplier;

        // Horizontal bounds (left edge = 0, right edge = 600 because 200px panel)
        if (x < 0) {
            x = 0;
            xSpeed = -xSpeed;
        }
        if (x > 600 - size/2) {
            x = 600 - size/2;
            xSpeed = -xSpeed;
        }

        // Vertical bounds (top = 0, bottom = 600 because 200px panel at bottom)
        if (y - size / 2 < 0) {
            y = size / 2;
            ySpeed = -ySpeed;
        }
        if (y + size / 2 > 600) {  // previously 800 - 200
            y = 600 - size / 2;
            ySpeed = -ySpeed;
        }

        if (status == 1) {
            infectionTime++;
            if (infectionTime >= timeToDeath) {
                int deathChance = 50;
                if (Math.random() * 100 < deathChance) {
                    status = 3;
                } else {
                    status = 2;
                    boostImmunityAfterRecovery();
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
