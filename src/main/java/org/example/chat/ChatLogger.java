package org.example.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class ChatLogger {

    public static String load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/log.txt"))){
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    public static void save(String str) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("src/main/resources/log.txt",false))){
            System.out.println(str);
            bufferedWriter.write(str);
            bufferedWriter.flush();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
