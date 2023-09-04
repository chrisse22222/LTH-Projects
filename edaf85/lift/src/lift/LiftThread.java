package lift;

public class LiftThread extends Thread{

    private MonitorLift m;
    private LiftView view;
    private int currentFloor = 0;
    public LiftThread(MonitorLift m, LiftView view){
        this.m = m;
        this.view = view;
    }

    public void run(){
        try{
            while (true){
                if (m.openDoors()){
                    view.openDoors(currentFloor);
                    m.closeDoors();
                    view.closeDoors();
                }

                int nextFloor = m.NextFloor();
                view.moveLift(currentFloor, nextFloor);
                currentFloor = nextFloor;
            }
        } catch (Exception e){
            throw new Error(e);
        }
    }
}
