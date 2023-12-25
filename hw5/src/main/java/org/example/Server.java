package org.example;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

  public static final int PORT = 8181;
  private static long clientIdCounter = 1L;
  private static final Map<Long, SocketWrapper> clients = new HashMap<>();


  public static void main(String[] args) throws IOException {

    try (ServerSocket server = new ServerSocket(PORT)) {
      System.out.println("Сервер запущен на порту " + PORT);

      while (true) {
        final Socket client = server.accept();
        final long clientId = clientIdCounter++;


        SocketWrapper wrapper = new SocketWrapper(clientId, client);
        System.out.println("Подключился новый клиент[ Id: " +clientId + " IP: " + wrapper + "]");
        clients.put(clientId, wrapper);

        new Thread(() -> {
          try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
            output.println("Подключение успешно. Ваш ID: " + wrapper.getId() + ". Список всех клиентов: " + clients);

            while (true) {
              String clientInput = input.nextLine();
              if (Objects.equals("q", clientInput)) {

                clients.remove(clientId);
                System.out.println("Клиент[ Id: " +clientId + " IP: " + wrapper + "] отключился");
                clients.values().forEach(it -> it.getOutput().println("Клиент[" + clientId + "] отключился"));
                break;
              }

              // формат сообщения: 1@ message

              // использую TreeMap для разделения id и сообщения, и получения метода firstKey() для удобства.

              TreeMap<Long, String>  data = getData(clientInput);

              long destinationId = data.firstKey();
              String message = data.get(destinationId);

              if (destinationId != 0) {

                // если destinationId в сети
                // сообщение отсылается по адресату

                if (clients.containsKey(destinationId)) {

                  SocketWrapper destination = clients.get(destinationId);
                  destination.getOutput().println("@" + clientId + " wrote You: " + message);
                }

                // если destinationId не в сети
                // ответ сервера, что такого destinationId нет в сети
                else {
                  clients.values().stream()
                          .filter(it -> it.getId()==clientId)
                          .forEach(it -> it.getOutput().println("Клиент[ Id: " + destinationId + "] не в сети"));
                }
              }
              // если нет destinationId
              // посылает сообщение всем адресам на сервере, кроме себя
              else {
                clients.values().stream()
                        .filter(it -> it.getId()!=clientId)
                        .forEach(it -> it.getOutput().println("@" + clientId + " wrote to all: " + message));
              }
            }
          }
        }).start();
      }
    }
  }

  /**
   *
   * @param clientInput - String value of Client input into console
   * @return - TreeMap with key = Long, value = String:
   *                  key - destination id:
   *                  if not 0 - destinationId to be sent the message
   *                  if 0 - message is sent to all ids
   *
   *                  value: message to be sent
   */
  private static TreeMap<Long, String> getData(String clientInput) {
    TreeMap<Long, String> data =new TreeMap<>();

    clientInput = clientInput.trim();

    if (!clientInput.startsWith("@")){
      data.put(0L,clientInput);
      return data;

    } else {

      int firstSpace = clientInput.indexOf(" ");
      String destinationIdString = (clientInput.split(" ")[0]);

      String message = clientInput.substring(firstSpace).trim();

      try {

        Long key = Long.parseLong(destinationIdString.substring(1));
        data.put(key, message);

      } catch (NumberFormatException e){
        data.put(0L,clientInput);
        return data;
      }
    }

    return data;
  }

}



