package oppgave2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static final String IP = "localhost";
    static final int PORT = 1250;

    private Socket clientSocket;
    private PrintWriter writer;
    private static BufferedReader reader;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connection started");
    }

    public String sendMessage(String msg) throws IOException {
        writer.println(msg);
        return reader.readLine();
    }

    private void readLines() throws IOException {
        String n = reader.readLine();
        while (!n.isEmpty()) {
            System.out.println(reader.readLine());
            n = reader.readLine();
        }
    }

    public void stopConnection() throws IOException {
        reader.close();
        writer.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startConnection(IP, PORT);
        client.readLines();

        Scanner scanner = new Scanner(System.in);

        String input = "";
        while (!input.equals("q")) {
            System.out.print("Enter an equation, q to quit: ");
            input = scanner.nextLine();
            String response = client.sendMessage(input);
            System.out.println(response);
        }

        client.stopConnection();
    }

}
