package wash.control;

import actor.ActorThread;
import wash.io.WashingIO;

public class TemperatureController extends ActorThread<WashingMessage> {

    // TODO: add attributes

    private WashingIO io;
    private double targetTemp;
    private boolean tempReached;
    private double dt = 10;
    ActorThread<WashingMessage> sender;
    public TemperatureController(WashingIO io) {
        this.io = io;
        // TODO
    }

    @Override
    public void run() {
        try {

            while (true) {
                // wait for up to a (simulated) minute for a WashingMessage
                WashingMessage m = receiveWithTimeout((long)(dt * 1000) / Settings.SPEEDUP);

                // if m is null, it means a minute passed and no message was received
                if (m != null) {
                    sender = m.getSender();
                    switch (m.getCommand()){
                        case WashingMessage.TEMP_SET:
                            targetTemp = m.getValue();
                            tempReached = false;
                            break;
                        case WashingMessage.TEMP_IDLE:
                            io.heat(false);
                            targetTemp = 0;
                            sender.send(new WashingMessage(this, WashingMessage.ACKNOWLEDGMENT));
                            break;
                    }

                    System.out.println("got " + m);
                }

                if(targetTemp != 0)
                    setTemp();
            }
        } catch (InterruptedException unexpected) {
            // we don't expect this thread to be interrupted,
            // so throw an error if it happens anyway
            throw new Error(unexpected);
        }
    }

    private void setTemp() {
        double lowerBound = targetTemp - 2 + (dt * 0.000238 * (io.getTemperature()-20)); //m_1
        double upperBound = targetTemp - (dt * 2000)/(io.getWaterLevel() * 4184); // mu

        if(io.getTemperature() < lowerBound) {
            io.heat(true);
        } else if (io.getTemperature() > upperBound){
            io.heat(false);
            if (!tempReached){
                tempReached = true;
                sender.send(new WashingMessage(this, WashingMessage.ACKNOWLEDGMENT));
            }
        }
    }
}
