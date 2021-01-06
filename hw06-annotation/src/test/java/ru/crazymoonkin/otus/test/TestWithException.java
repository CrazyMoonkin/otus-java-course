package ru.crazymoonkin.otus.test;

import ru.crazymoonkin.otus.annotation.After;
import ru.crazymoonkin.otus.annotation.Before;
import ru.crazymoonkin.otus.annotation.Test;

public class TestWithException {

    @Before
    public void before() {
        System.out.println("run before method");
    }

    @Test
    public void test1() {
        System.out.println("run test1 method");
    }

    @Test
    public void test2() {
        System.out.println("run test2 before throw exception");
        throw new RuntimeException("Test exception");
    }

    @Test
    public void test3() {
        System.out.println("run test3 method");
    }

    @After
    public void after() {
        System.out.println("run after method");
    }
}
