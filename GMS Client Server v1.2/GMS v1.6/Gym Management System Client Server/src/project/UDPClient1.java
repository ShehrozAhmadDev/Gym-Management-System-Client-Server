package project;
// import java.util.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

class UDPClient1 {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader inFromUser =    new BufferedReader(new InputStreamReader(System.in));
        
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int Port = 132;
        //obj send
        // Equipment sampleEquipment = new Equipment(1, "Bijoy", "Kerala",123);
        // //streams open
        // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // ObjectOutputStream os = new ObjectOutputStream(outputStream);
        // os.writeObject(sampleEquipment);

        // byte[] data = outputStream.toByteArray();
        // DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
        // clientSocket.send(sendPacket);
        // System.out.println("Message sent from client");

        //to send ints
        // DataOutputStream dataOut = new DataOutputStream(outputStream);
        // dataOut.writeInt(1);
        // dataOut.close(); // or dataOut.flush()
        // final byte[] bytes = outputStream.toByteArray();
        
//        for(int i=1;i<=3;i++) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
//            System.out.println("Enter username: ");

//            String sentence = "Initialize Connection";
//            sendData = sentence.getBytes();
//            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
//            clientSocket.send(sendPacket);
//            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//            clientSocket.receive(receivePacket);
//            String modifiedSentence = new String(receiveData,0,receivePacket.getLength());
//            if(modifiedSentence.contains("Connection Successfull")){
//                System.out.println("FROM SERVER: " + modifiedSentence);
                //menu, cases and sending packets to server
                String sentence;
                String modifiedSentence;
                DatagramPacket sendPacket;
                DatagramPacket receivePacket;
                do{
                    do{
                        System.out.println("1.Add Equipment | 2.View Equipment | 3.Search Equipment | 0.Exit");
                        System.out.println("Enter Number for function: ");
                        sentence = inFromUser.readLine();
                    }while(Integer.parseInt(sentence)<0 && Integer.parseInt(sentence)>3);

                    //send chosen option no.
                    // sendData = sentence.getBytes();
                     
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    DataOutputStream dataOut = new DataOutputStream(outputStream);
                    dataOut.writeInt(Integer.parseInt(sentence));
                    // dataOut.close(); // or 
                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    dataOut.flush();
                    outputStream.flush();

                    switch(sentence){
                        case "1":
                            //input name,cost,manufacturer etc
                            System.out.println("Enter Name for Equipment: ");
                            String name = inFromUser.readLine();
                            System.out.println("Enter Manufacturer for Equipment: ");
                            String manufacturer = inFromUser.readLine();
                            String cost;
                            String id;

                            do{
                                System.out.println("Enter Cost of Equipment: ");
                                cost = inFromUser.readLine();
                            }while(!isNumber(cost)); //retake inputs if given value isn't a number

                            do{
                                System.out.println("Enter Id of Equipment: ");
                                id = inFromUser.readLine();
                            }while(!isNumber(id)); //retake inputs if given value isn't a number

                            Equipment sampleEquipment = new Equipment(Integer.parseInt(id), name,manufacturer,Integer.parseInt(cost));
                            //streams open
                            ObjectOutputStream os = new ObjectOutputStream(outputStream);
                            os.writeObject(sampleEquipment);

                            sendData = outputStream.toByteArray();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                            clientSocket.send(sendPacket);
                            System.out.println("Message sent from client");

//                             sendData = name.getBytes();
//                             sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
//                             clientSocket.send(sendPacket);

//                             sendData = manufacturer.getBytes();
//                             sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
//                             clientSocket.send(sendPacket);

//                             sendData = String.valueOf(cost).getBytes();
//                             sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
//                             clientSocket.send(sendPacket);
// //                            System.out.println(sendData);

//                             sendData = String.valueOf(id).getBytes();
//                             sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
//                             clientSocket.send(sendPacket);
//                            System.out.println(sendData);
                            //receive confirmation that equipment added
                            break;
                        case "2":
                            //viewAll
                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            clientSocket.receive(receivePacket);
                            ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
                            ObjectInputStream is = new ObjectInputStream(in);
                            ArrayList<Equipment> receivedObj = (ArrayList<Equipment>) is.readObject();

                            System.out.println("ID\t Name\t Manufacturer\t Cost");
                            System.out.println(equipmentString(receivedObj));

                            break;
                        case "3":
                            //input search id
                            System.out.println("Enter Id of Equipment to Search: ");
                            id = inFromUser.readLine();
                            // sendData = String.valueOf(id).getBytes();

                            dataOut.writeInt(Integer.parseInt(id));
                            sendData = outputStream.toByteArray();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                            clientSocket.send(sendPacket);
                            dataOut.flush();
                            outputStream.flush();
                            // sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                            // clientSocket.send(sendPacket);

                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            clientSocket.receive(receivePacket);
                            in = new ByteArrayInputStream(receiveData); //put after receiving data
                            is = new ObjectInputStream(in);
                            Equipment received = (Equipment) is.readObject();
                            System.out.println("ID\t Name\t Manufacturer\t Cost");
                            System.out.println(received.getId()+"\t"+received.getName()+"\t"+received.getManufacturer()+"\t"+received.getCost());
                            //get equipment of ID if exists
                            break;
                        case "0":
                            System.out.println("Quitting");
                            System.exit(-1);

                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                    //receiving reply
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    modifiedSentence = new String(receiveData,0,receivePacket.getLength());
                    System.out.print(modifiedSentence);
                    System.out.println("\n");
                }while(!sentence.equals("0"));
//                break;
//            }
//            else{
//                System.out.println("Wrong Credentials! Try Again");
//                System.out.println("Turn: "+i);
//                System.out.println("Total Turns: 3");
//            }
//        }
        clientSocket.close();
    }

    public static String equipmentString(ArrayList<Equipment> all){
        String ans = "";
        for(int i=0;i<all.size();i++){
            ans += "\n"+all.get(i).getId()+"\t"+all.get(i).getName()+"\t"+all.get(i).getManufacturer()+"\t"+all.get(i).getCost();
        }
        return ans;
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
