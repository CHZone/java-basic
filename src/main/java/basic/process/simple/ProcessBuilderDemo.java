package basic.process.simple;

import java.io.IOException;
import java.util.Scanner;

public class ProcessBuilderDemo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig/all");
        try {
            Process process = pb.start();
            Scanner sc = new Scanner(process.getInputStream(), "GBK");
            String tmp;
            while (sc.hasNextLine()) {
                tmp = sc.nextLine();

                System.out.println(tmp);
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
