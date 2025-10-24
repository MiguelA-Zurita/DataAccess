package org.example;

public class p2 {
    public static void main(String[] args) {
        System.out.println("Hola, sóc el procés p2 amb PID: " + ProcessHandle.current().pid());
        System.out.println("PID del meu pare: " + ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(-1L));
    }
}
