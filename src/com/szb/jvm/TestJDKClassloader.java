package com.szb.jvm;

import com.sun.crypto.provider.DESKeyFactory;

public class TestJDKClassloader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassloader.class.getClassLoader());
    }
}
