package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class SocketThread extends Thread{
    Socket forbindelse;
    public SocketThread(Socket forbindelse) {
        this.forbindelse = forbindelse;
    }

    @Override
    public void run() {
        System.out.println("Tråd-ID: " + this.getId() + " har koblet til tjeneren");

        try {
            /* Åpner strømmer for kommunikasjon med klientprogrammet */
            InputStreamReader leseforbindelse = new InputStreamReader(this.forbindelse.getInputStream());
            BufferedReader leseren = new BufferedReader(leseforbindelse);
            PrintWriter skriveren = new PrintWriter(this.forbindelse.getOutputStream(), true);

            /* Sender innledning til klienten */
            skriveren.println("Hei, du har kontakt med tjenersiden! Thread id: " + this.getId());
            skriveren.println("Skriv et regnestykke, så skal jeg regne det ut!");

            /* Mottar data fra klienten */
            String enLinje = leseren.readLine();  // mottar en linje med tekst
            while (enLinje != null) {  // forbindelsen p� klientsiden er lukket
                StringTokenizer st = new StringTokenizer(enLinje); //splitter opp teksten
            /*
            if(enLinje.contains("[a-zA-Z]+")){
                skriveren.println("Du kan ikke skrive inn bokstaver!");
            }*/

                int result = 0;
                int firstOperand = Integer.parseInt(st.nextToken());
                String operand = st.nextToken();
                int secondOperand = Integer.parseInt(st.nextToken());
                if(operand.equals("+")){
                    result = firstOperand + secondOperand;
                }else{
                    result = firstOperand - secondOperand;
                }
                skriveren.println();
                System.out.println("Klient med tråd-ID " + this.getId() + " skrev: " + enLinje);
                skriveren.println("Svaret er: " + result);  // sender svar til klienten
                enLinje = leseren.readLine();
            }

            /* Lukker forbindelsen */
            leseren.close();
            skriveren.close();
            this.forbindelse.close();

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
