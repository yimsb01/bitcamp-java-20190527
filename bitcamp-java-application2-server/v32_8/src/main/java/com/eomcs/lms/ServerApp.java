// v32_8 : 회원/수업/게시물 요청을 처리하는 코드를 별도의 클래스로 분리한다.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    try(ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");
      
      try (Socket clientSocket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(
              clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(
              clientSocket.getOutputStream())) {
        
        System.out.println("클라이언트와 연결되었음.");
        
        // 다른 메서드가 사용할 수 있도록 입출력 스트림을 스태틱 변수에 저장한다.
        ServerApp.in = in;
        ServerApp.out = out;
        
        BoardServlet boardServlet = new BoardServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);

        loop:
        while (true) {
          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();
          System.out.println(command + "요청 처리중...");
          
          if (command.startsWith("/board/")) {
            boardServlet.service(command);
            out.flush();
            continue;
          }
          
          if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);
            out.flush();
            continue;
          }
          
          if (command.startsWith("/member/")) {
            memberServlet.service(command);
            out.flush();
            continue;
          }
          
          // 명령어에 따라 처리한다.
          switch (command) {
          case "quit":
            out.writeUTF("ok");
            break loop;
          default:
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush(); // 소켓을 통해서 데이터를 전송할 떄는 네부에 버퍼를 가지고 있기 때문에
                       // 언제나 flush()를 써서 잔여데이터를 비우지 않으면 대기상태로 들어간다.
          System.out.println("클라이언트에게 응답 완료!");
        } // loop:
        
        // 클라이언트에게 데이터를 잘 받았다고 응답한다.
        out.writeUTF("ok");
        out.flush(); // PrintWriter에 출력한 데이터는 버퍼에 있다. 버퍼에 있는 데이터를 강제로 출력하라!
        System.out.println("클라이언트로 데이터를 보냈음.");
        
      } 
      System.out.println("클라이언트와 연결을 끊었음.");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }

}
