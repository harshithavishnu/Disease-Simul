import processing.core.PApplet;

public class BarChart{
    private float width, height;
    private double healthyPercent = 0, infectedPercent = 0, curedPercent = 0, deadPercent = 0;

    public BarChart(){
        width = 20;
        height = 200;
    }


    public void update(Population people){
        healthyPercent = people.getHealthyPercent();
        infectedPercent = people.getInfectedPercent();
        curedPercent = people.getCuredPercent();
        deadPercent = people.getDeadPercent();

    }

    public void draw(PApplet window) {
        float barWidth = 100;
        float barSpacing = 30;
        float startX = 50;
        float baseY = 780;
        float maxHeight = 180;
        window.fill(255);           // white
        window.noStroke();          //no border
        window.rect(0, 600, window.width, 200);

        // Healthy
        window.fill(0, 255, 0);
        window.rect(startX, baseY - (float)(healthyPercent * maxHeight), barWidth, (float)(healthyPercent * maxHeight));

        // Infected
        window.fill(255, 0, 0);
        window.rect(startX + barWidth + barSpacing, baseY - (float)(infectedPercent * maxHeight), barWidth, (float)(infectedPercent * maxHeight));

        // Cured
        window.fill(0, 0, 255);
        window.rect(startX + 2 * (barWidth + barSpacing), baseY - (float)(curedPercent * maxHeight), barWidth, (float)(curedPercent * maxHeight));

        // Dead
        window.fill(128);
        window.rect(startX + 3 * (barWidth + barSpacing), baseY - (float)(deadPercent * maxHeight), barWidth, (float)(deadPercent * maxHeight));

        // Labels
        window.fill(0);
        window.textAlign(PApplet.CENTER);
        window.textSize(16);
        window.text("Healthy", startX + barWidth / 2, baseY + 15);
        window.text("Infected", startX + barWidth * 1.5f + barSpacing, baseY + 15);
        window.text("Cured", startX + barWidth * 2.5f + 2 * barSpacing, baseY + 15);
        window.text("Dead", startX + barWidth * 3.5f + 3 * barSpacing, baseY + 15);
    }

    }
