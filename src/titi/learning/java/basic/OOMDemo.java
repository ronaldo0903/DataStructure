package titi.learning.java.basic;

import java.util.ArrayList;
import java.util.List;

public class OOMDemo {
    public static class BigObj {
        private byte[] bigBytes = new byte[1024 * 1024 * 50];
    }
    public static void main(String[] args) {
        List<BigObj> list = new ArrayList<>();
        while (true) {
            list.add(new BigObj());
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
