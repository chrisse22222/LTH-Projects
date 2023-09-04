package wash.control;
import wash.io.WashingIO;
import wash.simulation.WashingSimulator;

public class Wash {

    public static void main(String[] args) throws InterruptedException {
        WashingSimulator sim = new WashingSimulator(Settings.SPEEDUP);
        
        WashingIO io = sim.startSimulation();

        TemperatureController temp = new TemperatureController(io);
        WaterController water = new WaterController(io);
        SpinController spin = new SpinController(io);

        temp.start();
        water.start();
        spin.start();

        Thread t = null;

        while (true) {
            int n = io.awaitButton();
            System.out.println("user selected program " + n);

            // if the user presses buttons 1-3, start a washing program
            // if the user presses button 0, and a program has been started, stop it
            switch (n){
                case 1:
                    t = new WashingProgram1(io, temp, water, spin);
                    t.start();
                    break;
                case 2:
                    t = new WashingProgram2(io, temp, water, spin);
                    t.start();
                    break;
                case 3:
                    t = new WashingProgram3(io, temp, water, spin);
                    t.start();
                    break;
                default:
                    if (t != null) {
                        t.interrupt();
                    }
                    break;
            }
        }
    }
};
