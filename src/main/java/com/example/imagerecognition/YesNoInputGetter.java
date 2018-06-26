package com.example.imagerecognition;

import java.util.Scanner;

public class YesNoInputGetter {

    private YesNoInputGetter() {
    }

    public static String get() {
        while (true) {
            System.out.println("YesかNoを入力してください。");
            Scanner scan = new Scanner(System.in);
            String answer = scan.next().toLowerCase();
            if (answer.equals("yes") || answer.equals("no")) return answer;

            System.out.println();
        }
    }
}

