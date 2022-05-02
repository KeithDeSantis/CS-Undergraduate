import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(4545);
        Socket s = ss.accept();

        System.out.println("Minecraft server has connected...\n");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String flag = bf.readline();
        System.out.println("Flag: " + flag);

    }
}