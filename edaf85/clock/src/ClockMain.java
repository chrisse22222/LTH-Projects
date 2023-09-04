import clock.AlarmClockEmulator;
import clock.io.ClockInput;
import clock.io.ClockInput.UserInput;
import clock.io.ClockOutput;

public class ClockMain {

    public static void main(String[] args){
        AlarmClockEmulator emulator = new AlarmClockEmulator();

        ClockInput in = emulator.getInput();
        ClockOutput out = emulator.getOutput();
        MonitorDesignPattern mdp = new MonitorDesignPattern(15, 2, 37, out);

        Thread t1 = new Thread(() -> {
            try{
                long t, diff;
                t = System.currentTimeMillis();
                while (true){
                    mdp.incrementTime();
                    t += 1000;
                    diff = t - System.currentTimeMillis();
                    if(diff > 0) Thread.sleep(diff);
                }
            } catch (InterruptedException e){
                throw new Error(e);
            }
        });

        Thread t2 = new Thread(() ->{
            try{
                while (true){
                    in.getSemaphore().acquire();
                    UserInput userInput = in.getUserInput();
                    int choice = userInput.getChoice();
                    int h = userInput.getHours();
                    int m = userInput.getMinutes();
                    int s = userInput.getSeconds();
                    if (choice == 1){
                        mdp.setTime(h, m, s);
                    } else if (choice == 2){
                        mdp.setAlarm(h, m, s);
                    } else{
                        mdp.activateAlarm();
                    }

                    //System.out.println("choice=" + choice + " h=" + h + " m=" + m + " s=" + s);
                }
            } catch (InterruptedException e){
                throw new Error(e);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                while (true){
                    mdp.checkAlarm();
                }
            } catch (InterruptedException e){
                throw new Error(e);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
