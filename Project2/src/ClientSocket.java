import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Enter a project description, author and version here.
 *
 * @author Ian Balaguera
 * @version 9.11.18
 */

public class ClientSocket {

    public static void main(String[] args) throws IOException {
        String host = "";
        int port = 8001;

        System.out.println("Connecting to apcs.roxburylatin.org on port " + port + "...");
        Socket connect = new Socket("apcs.roxburylatin.org", port);
        System.out.println("Connection acquired.");
        System.out.println("");

        PrintWriter writer = new PrintWriter(connect.getOutputStream());

        Scanner incoming = new Scanner(connect.getInputStream());

        System.out.println("Creating and sending a message to the server...");
        String message = "hello, socket!";

        writer.println(message);
        System.out.println("CLIENT: " + message);
        System.out.println();

        writer.flush();

        System.out.println("Waiting for server response...");
        System.out.println(incoming.nextLine());

        connect.close();
        writer.close();
        incoming.close();

        System.out.println("Done.");
    }


}