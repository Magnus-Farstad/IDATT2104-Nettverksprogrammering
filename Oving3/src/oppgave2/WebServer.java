package oppgave2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    static final int PORT = 1250;

    private ServerSocket serverSocket;
    private Socket connection;
    private PrintWriter writer;
    private BufferedReader reader;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Before accept");
        connection = serverSocket.accept();
        System.out.println("After accept");
        writer = new PrintWriter(connection.getOutputStream());
        writer.println("Hei du er tilkoblet");
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println("Før http header");

        writer.println("HTTP/1.0 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println("");
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<h1>You are connected</h1>");
        writer.println("<h3>Your headers, as requested:</h3>");
        writer.println("<ul>");
        System.out.println("midt i http header");

        readLines();

        writer.println("</ul>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        System.out.println("Etter i http header");
    }

    private void readLines() throws IOException {
        String n = reader.readLine();
        while (!n.isEmpty()) {
            writer.println("<li>" + n + "</li>");
            n = reader.readLine();
        }
    }

    public void stop() throws IOException {
        reader.close();
        writer.close();
        connection.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer();
        System.out.println("New webserver");
        server.start(PORT);
        System.out.println("server started");
        server.stop();
    }

}