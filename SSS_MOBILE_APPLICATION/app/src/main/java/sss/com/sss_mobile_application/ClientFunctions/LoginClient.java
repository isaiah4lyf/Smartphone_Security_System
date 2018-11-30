package sss.com.sss_mobile_application.ClientFunctions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LoginClient {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public LoginClient()
    {
        IPAddress address = new IPAddress();
        String host = address.getIPAddress();
        String port = address.getPort();

        try
        {
            clientSocket = new Socket(host, Integer.parseInt(port));
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
        }
        catch (Exception ex) { }
    }
    public String DoThework(String User_Name,String Password)
    {
        sendCommand("LOGIN");
        //sendCommand(User_Name);
        //sendCommand(Password);
        return readResponse();
    }

    protected void sendCommand(String message)
    {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readResponse()
    {
        String response  = "";
        try
        {
            response = in.readUTF();
        }
        catch (Exception ex)
        {
            response = ex.toString();
        }
        return response;
    }
}
