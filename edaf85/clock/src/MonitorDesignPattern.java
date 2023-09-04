import clock.io.ClockOutput;

import java.util.concurrent.Semaphore;

public class MonitorDesignPattern {
    private int h, m, s;
    private int hA, mA, sA;
    private final int secondsAlarm = 20;
    private int alarmCounter;
    private ClockOutput out;
    private Semaphore mex, avail;
    private boolean soundAlarm, notActive;
    public MonitorDesignPattern(int h, int m, int s, ClockOutput out){
        this.h = h;
        this.m = m;
        this.s = s;
        this.out = out;
        soundAlarm = false;
        notActive = false;
        alarmCounter = 0;
        mex = new Semaphore(1);
        avail = new Semaphore(0);
    }

    public void incrementTime() throws InterruptedException{
        mex.acquire();

        if (notActive && s == sA && m == mA && h == hA){
            soundAlarm = true;
            notActive = false;
        }

        s++;
        m += s/60;
        h += m/60;

        s = s%60;
        m = m%60;
        h = h%24;
        out.displayTime(h, m, s);

        if (soundAlarm){
            avail.release();
        }

        mex.release();
    }

    public void setTime(int h, int m, int s) throws InterruptedException{
        mex.acquire();
        this.s = s;
        this.m = m;
        this.h = h;
        mex.release();
    }

    public void setAlarm(int h, int m, int s) throws InterruptedException{
        mex.acquire();
        hA = h;
        mA = m;
        sA = s;
        mex.release();
    }

    public void activateAlarm() throws InterruptedException{
        mex.acquire();
        out.setAlarmIndicator(true);
        notActive = true;
        mex.release();
    }

    public void checkAlarm() throws InterruptedException{
        avail.acquire();
        out.alarm();
        alarmCounter++;
        if (alarmCounter >= secondsAlarm){
            alarmCounter = 0;
            soundAlarm = false;
            out.setAlarmIndicator(false);
        }
    }
}
