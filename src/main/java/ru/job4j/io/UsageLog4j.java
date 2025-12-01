package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 34;
        short square = 1500;
        int car = 20000000;
        long people = 7000000000L;
        float height = 180.2F;
        double distance = 200000.34;
        boolean permission = true;
        char symbol = 'c';
        String name = "Egor Moroshkin";

        LOG.debug("square : {}, car : {}, people : {}, height : {}, distance : {}, permission : {}, symbol : {},"
                + " name : {}, age : {} ", square, car, people, height, distance, permission, symbol, name, age);
    }
}