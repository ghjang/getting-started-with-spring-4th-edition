import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("에코 서버가 8888 포트에서 시작되었습니다.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다: " + clientSocket.getInetAddress());

                try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("클라이언트로부터 수신: " + inputLine);
                        out.println(inputLine); // 에코 응답

                        if (inputLine.equals("quit")) {
                            break;
                        }
                    }
                }

                clientSocket.close();
                System.out.println("클라이언트 연결이 종료되었습니다.");
            }
        } catch (IOException e) {
            System.out.println("서버 에러 발생: " + e.getMessage());
        }
    }
}
