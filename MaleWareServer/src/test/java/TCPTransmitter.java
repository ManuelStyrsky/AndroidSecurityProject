import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class TCPTransmitter {
    private final static String tcpMessage = "Hallo die ist ein Test von Paula!^^";
    private final static String propertiesFilename = "configTest.properties";
    private String hostIP;
    private int port;



    public static void main(String[] args) {
        TCPTransmitter tcpTransmitter = new TCPTransmitter();
    }

    public TCPTransmitter(){
        try {
            readProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            transmitData(tcpMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void transmitData(String data) throws IOException {
        Socket socket = new Socket(hostIP, port);
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream, true);

        printStream.println(data);

        System.out.println("data is send!");
        socket.close();
    }

    private void readProperties() throws Exception {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(propertiesFilename);
            properties.load(inputStream);
            port = Integer.parseInt(properties.getProperty("port"));
            hostIP = properties.getProperty("serverIP");
        } catch (FileNotFoundException e) {
            throw new Exception("Could not find properties file!");
        } catch (IOException e) {
            throw new Exception("Could not read properties file!");
        }
    }


}
