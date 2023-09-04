package wash.control;

import actor.ActorThread;
import wash.io.WashingIO;

public class WaterController extends ActorThread<WashingMessage> {

    // TODO: add attributes

    private double targetLevel = -1;
    private WashingIO io;
    private double dt = 1;
    private boolean filling;
    ActorThread<WashingMessage> sender;
    public WaterController(WashingIO io) {
        this.io = io;
    }

    @Override
    public void run() {
        try {

            while (true) {
                // wait for up to a (simulated) minute for a WashingMessage
                WashingMessage m = receiveWithTimeout((long)(dt * 1000) / Settings.SPEEDUP);

                // if m is null, it means a minute passed and no message was received
                if (m != null) {

                    switch (m.getCommand()){
                        case WashingMessage.WATER_DRAIN:
                            targetLevel = 0;
                            filling = false;
                            break;
                        case WashingMessage.WATER_FILL:
                            targetLevel = m.getValue();
                            filling = true;
                            break;
                        case WashingMessage.WATER_IDLE:
                            targetLevel = -1;
                            io.fill(false);
                            io.drain(false);
                            break;
                    }

                    sender = m.getSender();
                    System.out.println("got " + m);
                }

                if(targetLevel != -1) {
                    setLevel();
                }

            }
        } catch (InterruptedException unexpected) {
            // we don't expect this thread to be interrupted,
            // so throw an error if it happens anyway
            throw new Error(unexpected);
        }
    }

    private void setLevel() {
        if(filling) {
            double nextLevel = io.getWaterLevel() + dt * 0.1d;
            if (nextLevel < targetLevel){
                io.fill(true);
            } else{
                sender.send(new WashingMessage(this, WashingMessage.ACKNOWLEDGMENT));
                io.fill(false);
                targetLevel = -1;
            }
        }
        else {
            double w_o = io.getWaterLevel() - dt * 0.2d;
            if(w_o > targetLevel) {
                io.drain(true);
            } else {
                sender.send(new WashingMessage(this, WashingMessage.ACKNOWLEDGMENT));
                io.drain(false); // sluta inte :(
                targetLevel = -1;
            }
        }
    }
}
