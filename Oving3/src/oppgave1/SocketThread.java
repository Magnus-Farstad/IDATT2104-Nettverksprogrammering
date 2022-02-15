package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class SocketThread extends Thread {
    Socket forbindelse;

    public SocketThread(Socket forbindelse) {
        this.forbindelse = forbindelse;
    }

    @Override
    public void run() {
        try {
            /* Åpner strømmer for kommunikasjon med klientprogrammet */
            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            BufferedReader leseren = new BufferedReader(leseforbindelse);
            PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

            /* Sender innledning til klienten */
            skriveren.println("Hei, du har kontakt med tjenersiden!");
            System.out.println("Klient med tråd-ID: " + this.getId() + " har koblet seg til");
            skriveren.println("Skriv et regnestykke, så skal jeg regne det ut!");

            /* Mottar data fra klienten */
            String enLinje = leseren.readLine();  // mottar en linje med tekst

            while (enLinje != null) {  // forbindelsen p� klientsiden er lukket
                StringTokenizer st = new StringTokenizer(enLinje); //splitter opp teksten

                if(enLinje.matches("[a-zA-Z]+")) {
                    skriveren.println("Kan ikke ha bokstav");
                }else{
                    int result = 0;
                    int firstOperand = Integer.parseInt(st.nextToken());
                    String operator = st.nextToken();
                    int secondOperand = Integer.parseInt(st.nextToken());
                    if (operator.equals("+")) {
                        result = firstOperand + secondOperand;
                        System.out.println("Klient med tråd-ID: " + this.getId() + " skrev: " + enLinje);
                        skriveren.println("Svaret er: " + result);  // sender svar til klienten
                    } else if(operator.equals("-")) {
                        result = firstOperand - secondOperand;
                        System.out.println("Klient med tråd-ID: " + this.getId() + " skrev: " + enLinje);
                        skriveren.println("Svaret er: " + result);  // sender svar til klienten
                    }else{
                        skriveren.println("Svaret er ugyldig");
                    }
                }
                enLinje = leseren.readLine();
            }
            /* Lukker forbindelsen */
            leseren.close();
            skriveren.close();
            forbindelse.close();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}