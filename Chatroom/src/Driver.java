import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        String host = "";
        int port = 2500;

        ServerSocket incoming = new ServerSocket(port);

        System.out.println("Connecting to " + host + " on port " + port + "...");
        Socket connect = new Socket("apcs.roxburylatin.org", port);
        System.out.println("Connection acquired.\n");

        PrintWriter send = new PrintWriter(connect.getOutputStream());
        Scanner recieve = new Scanner(connect.getInputStream());

        Scanner consoleIn = new Scanner(System.in);

        System.out.println("DELTA: ");
        String msg = consoleIn.nextLine();

        send.println(msg);
        send.flush();
    }
}
