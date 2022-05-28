package test;

import base.exception.EmptyError;

/**
 * @author shxl
 * @data 2022/5/28 12:32
 **/
public class ExptionTest {
    public static void main(String[] args) {

        try {
            int x = 1 / 0;
        } catch (Exception e) {
            throw new EmptyError();
        }

    }
}
