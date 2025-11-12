import processing.core.PApplet;

    public class DynamicStackedBarChart extends PApplet {

        // Data structure: a 2D array where each row represents a bar
        // data[i][0] is the stable bottom value, data[i][1] is the dynamic top value
        float[][] data = {
                {50, 20}, // Bar 1: bottom 50, top 20
                {30, 40}, // Bar 2: bottom 30, top 40
                {60, 10}, // Bar 3: bottom 60, top 10
                {40, 30}  // Bar 4: bottom 40, top 30
        };
        int numBars;
        float barWidth;
        float graphHeight = 200; // Total height for the graph area

        public void settings() {
            size(400, 300);
        }

        public void setup() {
            numBars = data.length;
            barWidth = (float) width / numBars;
            rectMode(CORNER); // Default, but good to be explicit
            noStroke(); // Remove rectangle outlines for a cleaner look
        }

        public void draw() {
            background(255); // Clear the background in each frame

            // Update the dynamic data (e.g., based on time or a random function)
            updateData();

            // Draw the bars
            for (int i = 0; i < numBars; i++) {
                float x = i * barWidth;
                float bottomHeight = data[i][0];
                float topHeight = data[i][1];
                float totalHeight = bottomHeight + topHeight;

                // Map totalHeight to screen coordinates (invert Y axis)
                float totalY = height - totalHeight;

                // Draw the bottom stable segment
                fill(100, 150, 250); // Blueish color
                rect(x, height - bottomHeight, barWidth, bottomHeight);

                // Draw the top dynamic segment
                fill(250, 100, 100); // Redish color
                rect(x, totalY, barWidth, topHeight);
            }
        }

        void updateData() {
            // Example dynamic change: use a sine wave to change the top segment height
            // This will make the graph "change" over time
            for (int i = 0; i < numBars; i++) {
                // Change the second value based on current time (millis)
                float dynamicChange = sin(millis() / 1000.0f + i) * 15 + 25;
                data[i][1] = dynamicChange;
            }
        }

        public static void main(String[] args) {
            PApplet.main("DynamicStackedBarChart");
        }
    }

