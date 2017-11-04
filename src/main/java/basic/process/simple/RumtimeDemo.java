package basic.process.simple;

        import java.io.IOException;
        import java.util.Scanner;

public class RumtimeDemo {
    public static void main(String[] args){
        String cmd = "cmd "+"/c "+"ipconfig/all";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            Scanner sc = new Scanner(process.getInputStream(),"GBK");
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
