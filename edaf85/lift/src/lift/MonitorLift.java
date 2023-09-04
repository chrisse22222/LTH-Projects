package lift;

public class MonitorLift {
    private int floor; // the floor the lift is currently on
    private int entering, exiting; // Total number of persons entering or exiting elevator
    private boolean moving; // true if the lift is moving, false if standing still with doors open
    private int direction; // +1 if lift is going up, ‐1 if going down
    private int[] waitEntry; // number of passengers waiting to enter the lift at the various floors
    private int[] waitExit; // number of passengers (in lift) waiting to leave at the various floors
    private int load; // number of passengers currently in the lift
    private LiftView view;

    public MonitorLift(LiftView view){
        floor = load = 0;
        moving = true;
        direction = 1;
        this.view = view;
        waitEntry = new int[view.NBR_FLOORS];
        waitExit = new int[view.NBR_FLOORS];

        for (int i = 0; i < waitEntry.length; i++) {
            waitEntry[i] = 0;
            waitExit[i] = 0;
        }
    }

    public synchronized int NextFloor() throws InterruptedException {
        while (!passengersWaiting() && load != view.MAX_PASSENGERS){
            wait();
        }

        boolean changeDir = checkFloors();
        if (floor == 0 && !changeDir){
            direction = 1;
        } else if (floor == view.NBR_FLOORS - 1 && !changeDir){
            direction = -1;
        }

        return direction > 0 ? ++floor : --floor;
    }

    private synchronized boolean checkFloors(){
        if (direction > 0 && floor != view.NBR_FLOORS - 1){
            for (int i = floor; i < view.NBR_FLOORS; ++i) {
                if (waitEntry[i] > 0 && load != view.MAX_PASSENGERS||
                        waitExit[i] > 0){
                    return false;
                }
            }
        } else{
            for (int i = floor; i >= 0; --i) {
                if (waitEntry[i] > 0 && load != view.MAX_PASSENGERS||
                        waitExit[i] > 0){
                    return false;
                }
            }
        }

        direction *= -1;
        return true;
    }

    public synchronized boolean openDoors() {
        if (waitEntry[floor] > 0  && load < view.MAX_PASSENGERS ||
                waitExit[floor] > 0){
            return true;
        }

        return false;
    }

    public synchronized void closeDoors() throws InterruptedException {
        moving = false;
        notifyAll();
        while (waitEntry[floor] > 0  && load < view.MAX_PASSENGERS ||
                waitExit[floor] > 0){
            wait();
        }

        moving = true;
    }

    public synchronized void waitForFloor(int targetFloor) throws InterruptedException {
        while (floor != targetFloor || moving){
            wait();
        }

        exiting++;
    }

    public synchronized void enteredElevator(int targetFloor){
        waitEntry[floor]--;
        waitExit[targetFloor]++;
        load++;
        entering--;
        notifyAll();
    }

    public synchronized void departedElevator(){
        waitExit[floor]--;
        load--;
        exiting--;
        notifyAll();
    }

    public synchronized void waitForElevator(int currentFloor) throws InterruptedException {
        waitEntry[currentFloor]++;
        notifyAll();
        while (entering + load - exiting == view.MAX_PASSENGERS ||
                currentFloor != floor ||
                moving){
            wait();
        }

        entering++;
    }

    private synchronized boolean passengersWaiting(){
        for (int i = 0; i < view.NBR_FLOORS; i++) {
            if (waitExit[i] > 0 || waitEntry[i] > 0) return true;
        }

        return false;
    }
}
