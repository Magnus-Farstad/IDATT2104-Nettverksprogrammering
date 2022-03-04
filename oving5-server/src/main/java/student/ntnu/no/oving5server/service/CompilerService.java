package student.ntnu.no.oving5server.service;

import java.io.*;

import org.springframework.stereotype.Service;
import student.ntnu.no.oving5server.model.SourceCode;

import java.io.BufferedWriter;

@Service
public class CompilerService {
    private String message = "";

    public String runCode(SourceCode sourceCode) throws IOException {
        System.out.println("runCode() called");

        File file = new File("D:\\Skole\\Semester_4\\Nettverksprogrammering\\Ovinger\\IDATT2104-Nettverksprogrammering\\oving5-server\\compile\\main.cpp");
        StringBuilder output = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            Process process2 = Runtime.getRuntime().exec("docker run java-docker");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            String s = null;
            while((s = stdInput.readLine()) != null){
                output.append(s + "\n");
            }

            int exitValue = process2.waitFor();
            if (exitValue == 0) {
                System.out.println("Success");
                System.out.println(output);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return output.toString();
    }

    public void doCompile(SourceCode sourceCode) {
        System.out.println("doCompile() called");
        File file = new File("D:\\Skole\\Semester_4\\Nettverksprogrammering\\Ovinger\\IDATT2104-Nettverksprogrammering\\oving5-server\\compile\\main.cpp");

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.append(sourceCode.getSourceCode());

            Process process = Runtime.getRuntime().exec("docker build --tag java-docker .");
            System.out.println(process.waitFor());

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
