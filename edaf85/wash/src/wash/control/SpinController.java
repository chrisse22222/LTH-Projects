package wash.control;

import actor.ActorThread;
import wash.io.WashingIO;

public class SpinController extends ActorThread<WashingMessage> {

    private WashingIO io;
    private int lastSpinMode;
    private boolean alternate;

    public SpinController(WashingIO io) {
        this.io = io;
        lastSpinMode = WashingIO.SPIN_RIGHT;
    }

    @Override
    public void run() {
        try {

            while (true) {
                // wait for up to a (simulated) minute for a WashingMessage
                WashingMessage m = receiveWithTimeout(60000 / Settings.SPEEDUP);

                // if m is null, it means a minute passed and no message was received
                if (m != null) {

                    switch (m.getCommand()){
                        case WashingMessage.SPIN_SLOW:
                            alternate = true;
                            break;

                        case WashingMessage.SPIN_FAST:
                            alternate = false;
                            io.setSpinMode(WashingIO.SPIN_FAST);
                            io.drain(true);   // lagligt??????
                            break;

                        case WashingMessage.SPIN_OFF:
                            alternate = false;
                            io.setSpinMode(WashingIO.SPIN_IDLE);
                            ActorThread<WashingMessage> t = m.getSender();
                            t.send(new WashingMessage(this, WashingMessage.ACKNOWLEDGMENT));
                            break;
                    }

                    System.out.println("got " + m);
                }

                if(alternate) {
                    if(lastSpinMode != WashingIO.SPIN_LEFT)
                        lastSpinMode = WashingIO.SPIN_LEFT;
                    else
                        lastSpinMode = WashingIO.SPIN_RIGHT;

                    io.setSpinMode(lastSpinMode);
                }
            }
        } catch (InterruptedException unexpected) {
            // we don't expect this thread to be interrupted,
            // so throw an error if it happens anyway
            throw new Error(unexpected);
        }
    }
}

