/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 60444
 */
public class UFclient {
        
    public UFclient() {
        
    }
    
    public int count(int n){
        //initial class UF_HWQUPC
        UF_HWQUPC u = new UF_HWQUPC(n,false);
        int count = 0;//the number of connection
        u.components();// the number of component in this array
        Random random = new Random();
        while(u.components()>1){
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            u.connect(x, y);
            count++;
            //System.out.println(n+","+u.components()+","+count);
        }
        return count;
    }
    
    public static void main(String[] args){
        System.out.println("Please enter n: ");
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while(!scanner.hasNextInt()){
            System.out.println("Please enter Integer! ");
            System.out.println("Please enter n: ");
            scanner = new Scanner(System.in);
        }
        n = scanner.nextInt();
        System.out.println("n: "+n);
        scanner.close();
        UFclient ufclient = new UFclient();
        int total =0;
        int time = 100;
        for(int i=0;i<time;i++){
            total += ufclient.count(n);
        }
        System.out.println("number of connection: "+total/time);
        System.out.println("1/2Nln(N) = "+0.5*n*Math.log(n));
/*
        for(int n=10000 ;n<=400000 ;n+=10000){
            int total =0;
            int time = 50;
            UFclient ufclient = new UFclient();
            for(int i=0;i<time;i++){
                total += ufclient.count(n);
            }
            System.out.println(n+","+total/time);
        } 
*/
    }
}
