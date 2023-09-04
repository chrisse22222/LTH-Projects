
import lift.*;

import java.util.ArrayList;

public class OnePersonRidesLift {

    public static void main(String[] args) {

        LiftView view = new LiftView();
        MonitorLift m = new MonitorLift(view);
        LiftThread lift = new LiftThread(m, view);
        ArrayList<PersonThread> p = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            p.add(new PersonThread(view, m));
            p.get(i).start();
        }

        lift.start();
    }
}
