package webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

public class ConnectionHandler implements Runnable
{
    private Socket socket;

    private ConnectionHandler(Socket toHandle)
    {
        this.socket = toHandle;
    }

    /**
     * Handle the incoming connection. This method is called by the JVM when passing an
     * instance of the connection handler class to a Thread.
     */
    public void run()
    {
        try
        {
            ArrayList<String> headers = new ArrayList<String>();
            // Set up a reader that can conveniently read our incoming bytes as lines of text.
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            do
            {
                line = reader.readLine();
                headers.add(line);
            } while (!line.isEmpty());
for (String l : headers)
{
    System.out.println(l);
}
            Request request = new Request(headers);

            // Create a respons and send it.
            
            // Set up a writer that can write text to our binary output stream.
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String status = "HTTP/1.0 200 OK";
            String[] header = {
                "Content-Type: text/html; charset=UTF-8"
            };
            String body = "Thank you for connecting!";

            String result = String.join("\r\n", status, String.join("\r\n", header), "", body);

            writer.write(result);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            // Send 500.
        } finally {
            try
            {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Send 500?
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
            // A server socket opens a port on the local computer (in this program port 8080).
            // The computer now listens to connections that are made using the TCP/IP protocol.
            ServerSocket socket = new ServerSocket(8080);
            System.out.println("Application started. Listening at localhost:8080");

            // Start an infinite loop. This pattern is common for applications that run indefinitely
            // and react on system events (e.g. connection established). Inside the loop, we handle
            // the connection with our application logic. 
            while(true)
            {
                // Wait for someone to connect. This call is blocking; i.e. our program is halted
                // until someone connects to localhost:8080. A socker is a connection (a virtual
                // telephone line) between two endpoints - the client (browser) and the server (this).
                Socket newConnection = socket.accept();

                // We want to process our incoming call. Furthermore, we want to support multiple
                // connections. Therefore, we handle the processing on a background thread. Java
                // threads take a class that implements the Runnable interface as a constructor
                // parameter. Upon starting the thread, the run() method is called by the JVM.
                // As our handling is in a background thread, we can accept new connections on the
                // main thread (in the next iteration of the loop).
                // Starting the thread is so-called fire and forget. The main thread starts a second
                // thread and forgets about its existence. We recieve no feedback on whether the
                // connection was handled gracefully.
                Thread t = new Thread(new ConnectionHandler(newConnection));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
