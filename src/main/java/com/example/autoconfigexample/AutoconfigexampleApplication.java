package com.example.autoconfigexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


@SpringBootApplication
@Slf4j
public class AutoconfigexampleApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AutoconfigexampleApplication.class, args);

        try (ServerSocket serverSocket = new ServerSocket(11111);
             Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            log.info("서버 대기중....");
            while (true) {
                String msg = br.readLine();
                if (msg.equalsIgnoreCase("Quit"))
                    break;
                log.info("클라이언트에서 보냄:[ " + msg + " ]");
                out.println(msg);
                out.flush();
            }
            log.info("서버종료..");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
