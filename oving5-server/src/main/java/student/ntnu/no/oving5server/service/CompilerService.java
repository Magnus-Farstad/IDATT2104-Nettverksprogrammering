package student.ntnu.no.oving5server.service;

import java.io.*;

import org.springframework.stereotype.Service;
import student.ntnu.no.oving5server.model.SourceCode;

import java.io.BufferedWriter;

@Service
public class CompilerService {

//    public static void main(String[] args) {
//        System.out.println("Hello World!!!");
//    }

    public String doCompile(SourceCode sourceCode) throws IOException {

        System.out.println("doCompile() called");
        File file = new File("/Users/Farstad/OneDrive/Skole/Andre_aar/Semester_4/Nettverksprogrammering/oving5-server/compile/main.cpp");
        StringBuilder output = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.append(sourceCode.getSourceCode());
            //sourceCode.setOutput("Output fra server!!");

            Runtime.getRuntime().exec("docker build --tag java-docker .");
            Process process2 = Runtime.getRuntime().exec("docker run java-docker");


            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            String s = null;
            while((s = stdInput.readLine()) != null){
                output.append(s + "\n");
            }

            int exitValue = process2.waitFor();
            if (exitValue == 0) {
                System.out.println(exitValue);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return output.toString();
    }

    public void resetCompilation(SourceCode sourceCode) throws IOException {
        File file = new File("/Users/Farstad/OneDrive/Skole/Andre_aar/Semester_4/Nettverksprogrammering/oving5-server/compile/main.cpp");
        StringBuilder output = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.append(sourceCode.getSourceCode());
            Runtime.getRuntime().exec("docker build --tag java-docker .");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
