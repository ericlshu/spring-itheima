package com.eric;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-30 12:00
 */
public class TimerTaskApp {

    public static void main(String[] args)
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                System.out.println("timer task is running ......");
            }
        };
        timer.schedule(task, 0, 2000);
    }
}
