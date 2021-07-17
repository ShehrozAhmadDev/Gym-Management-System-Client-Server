package project;
// import java.util.*;
import java.io.*;
import java.net.*;
class UDPClient {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader inFromUser =    new BufferedReader(new InputStreamReader(System.in));
        InetAddress IPAddress = InetAddress.getByName("localhost");

        for(int i=1;i<=3;i++) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            System.out.println("Enter username: ");

            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receiveData,0,receivePacket.getLength());

            if(modifiedSentence.contains("Connection Successfull")){
                System.out.println("FROM SERVER:" + modifiedSentence);
                //menu, cases and sending packets to server
                do{
                    do{
                        System.out.println("1.Add Equipment | 2.View Equipment | 3.Search Equipment | 0.Exit");
                        System.out.println("Enter Number for function: ");
                        sentence = inFromUser.readLine();
                    }while(Integer.parseInt(sentence)<0 && Integer.parseInt(sentence)>3);

                    //send chosen option no.
                    sendData = sentence.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                    clientSocket.send(sendPacket);
                    //use sentence.contains("1") if switch doesn't work
                    switch(sentence){
                        case "1":
                            //input name,cost,manufacturer etc
                            System.out.println("Enter Name for Equipment: ");
                            String name = inFromUser.readLine();
                            System.out.println("Enter Manufacturer for Equipment: ");
                            String manufacturer = inFromUser.readLine();
                            System.out.println("Enter Cost of Equipment: ");
                            String cost ;
                            String id;
                            do{
                                cost = inFromUser.readLine();
                            }while(!isNumber(cost)); //retake inputs if given value isn't a number

                            do{
                                System.out.println("Enter Id of Equipment: ");
                                id = inFromUser.readLine();
                            }while(!isNumber(id)); //retake inputs if given value isn't a number

                            sendData = name.getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                            clientSocket.send(sendPacket);

                            sendData = manufacturer.getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                            clientSocket.send(sendPacket);

                            sendData = String.valueOf(cost).getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                            clientSocket.send(sendPacket);
//                            System.out.println(sendData);

                            sendData = String.valueOf(id).getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                            clientSocket.send(sendPacket);
//                            System.out.println(sendData);
                            //receive confirmation that equipment added
                            break;
                        case "2":
                            //viewAll
                            System.out.println("ID\t Name\t Manufacturer\t Cost");
                            break;
                        case "3":
                            //input search id
                            System.out.println("Enter Id of Equipment to Search: ");
                            id = inFromUser.readLine();
                            sendData = String.valueOf(id).getBytes();

                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 132);
                            clientSocket.send(sendPacket);
                            System.out.println("ID\t Name\t Manufacturer\t Cost");

                            //get equipment of ID if exists
                            break;
                        case "0":
                            System.out.println("Quitting");
                            System.exit(-1);
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    modifiedSentence = new String(receiveData,0,receivePacket.getLength());
                    System.out.print(modifiedSentence);
                    System.out.println("\n");
                }while(!sentence.equals("0"));
                break;
            }
            else{
                System.out.println("Wrong Credentials! Try Again");
                System.out.println("Turn: "+i);
                System.out.println("Total Turns: 3");
            }
        }
        clientSocket.close();
    }
    public static boolean isNumber(String value){
        value = value.trim();
        char[] ch = value.toCharArray();
        for(int i=0;i<ch.length;i++)
            if(!Character.isDigit(ch[i]))
                return false;
        return true;
    }
}
