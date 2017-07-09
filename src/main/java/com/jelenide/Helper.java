package com.jelenide;

/**
 * Created by Alex on 7/9/2017.
 */
public class Helper {
  public static void pause(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
