import processing.core.PApplet;
import java.util.ArrayList;

public class Population extends PApplet {
    private ArrayList<Person> persons;
    private int popImmunity;




    public void setPopImmunity(int immunity){
        for (int i = 0; i < persons.size(); i++) {
            Person a = persons.get(i);
            if (a.getStatus() == 0){
                a.setImmunity(immunity);
            }
            if (a.getStatus() == 2){
                a.setImmunity(immunity*2);
            }
            else{
                a.setImmunity(0);
            }
        }

    }
}
