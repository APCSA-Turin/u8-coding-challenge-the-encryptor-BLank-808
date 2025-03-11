package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int remainder=0;
        if(messageLen%rows !=0){
            remainder=rows-messageLen%rows;
        }
        if(messageLen==0){
            return 1;
        }
        return (messageLen+remainder)/rows;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] arr=new String [rows][determineColumns(message.length(),rows)];
        int indxpos=1;
        for (int row = 0; row < arr.length; row++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(indxpos<=message.length()){
                    arr[row][j]=message.substring(indxpos-1, indxpos);
                }else{
                    arr[row][j]="=";
                }
                indxpos++;
            }
        }
        return arr;
    }

    public static String encryptMessage(String message, int rows){
        String[][]arr=generateEncryptArray(message, rows);
        String encrypted="";
        for(int columb=arr[0].length-1; columb>=0; columb--){
            for (int row = 0; row < rows; row++) {
                encrypted+=arr[row][columb];
            }
        }
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][]arr=generateEncryptArray(encryptedMessage, determineColumns(encryptedMessage.length(), rows));
        String decrypted="";

        for(int columb=0; columb<=arr[0].length-1; columb++){
            for (int row =arr.length-1; row >=0; row--) {
                if(!arr[row][columb].equals("=")){
                decrypted+=arr[row][columb];
                }
            }
    }
        return decrypted;
    }


    public static void main(String[] args) {
        String message = "Hello, how are you?";
        int rows = 6;
        System.out.println();
        String[][] arr=Encryptor.generateEncryptArray(message, rows);
        message=Encryptor.encryptMessage("Hello, how are you?", 6);
        System.out.println(Encryptor.encryptMessage("Hello, how are you?", 6));
        for (String[] lStrings : arr) {
            for (String str : lStrings) {
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
        arr=generateEncryptArray(message,4);
        System.out.println();

        for (String[] lStrings : arr) {
            for (String str : lStrings) {
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(Encryptor.decryptMessage(Encryptor.encryptMessage("Hello, how are you?", 6),6));

    }
}