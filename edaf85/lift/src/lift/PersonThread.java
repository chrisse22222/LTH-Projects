package lift;

import java.util.Random;

public class PersonThread extends Thread{

    private Random random;
    private LiftView view;
    private MonitorLift m;
    public PersonThread(LiftView view, MonitorLift m){
        random = new Random();
        this.view = view;
        this.m = m;
    }

    public void run(){
        try{
            while (true){
                sleep(random.nextInt(46)*1000);
                Passenger pass = view.createPassenger();
                pass.begin();

                int startFloor = pass.getStartFloor();
                int targetFloor = pass.getDestinationFloor();
                int dir = targetFloor > startFloor ? 1 : -1;  // positive if the person is supposed to go up, not currently used
                m.waitForElevator(startFloor);
                pass.enterLift();
                m.enteredElevator(targetFloor);
                m.waitForFloor(targetFloor);
                pass.exitLift();
                m.departedElevator();
                pass.end();
            }

        } catch (Exception e){
            throw new Error(e);
        }
    }
}
