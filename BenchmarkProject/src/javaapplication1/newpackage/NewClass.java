/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.newpackage;

/**
 *
 * @author Marius
 */
public class NewClass {
    public static void main(String[] args){
                String b, c, d;
                Home h = new Home();
                int a;
                //a = h.getReadWriteMethod();
                b = h.getNumberOfTests();
                c = h.getFileSize();
                d = h.getBlockSize();
                System.out.println("\n" + b + "\n" + c + "\n" + d);
    }
}
