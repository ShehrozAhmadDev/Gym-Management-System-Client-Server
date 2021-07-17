package project;
import java.net.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.DataInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

class UDPServer {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(232);
        byte[] receiveData = new byte[1024];
        byte[] sendData  = new byte[1024];
        System.out.println("SERVER HAS STARTED: ");

        while(true)
        {
            int choice;
            String newSentence = "";
            DatagramPacket sendPacket;
            DatagramPacket receivePacket;
            do{
                //receive chosen option
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                ByteArrayInputStream in = new ByteArrayInputStream(receiveData);
                DataInputStream dataIn = new DataInputStream(in);
                choice = dataIn.readInt();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                switch(choice){
                    case 1:
                        //receive equipment data
                        System.out.println("Add Equipment Function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();

                        ObjectInputStream is = new ObjectInputStream(in);
                        Equipment receivedObj = (Equipment) is.readObject();
                        try{
                            Exception e=receivedObj.addEquipment();
                            if (e==null){
                                newSentence = "Equipment Added";
                            }
                            else {
                                newSentence="Equipment already exists with this id\n"+e;
                            }
                        }
                        catch (Exception e1){
                            System.out.println(e1);
                            newSentence=e1+"";
                        }
                        break;
                    case 2:
                        //call viewAll func
                        System.out.println("View Function");
                        ArrayList<Equipment> all = Equipment.viewAll();

                        //streams open
                        ObjectOutputStream os = new ObjectOutputStream(outputStream);
                        os.writeObject(all);
                
                        sendData = outputStream.toByteArray();
                        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                        newSentence = "View all Equipment sent from server";

                        break;
                    case 3:
                        //call search by id
                        System.out.println("Search function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();


                        dataIn = new DataInputStream(in);
                        int id = dataIn.readInt();
                        try {
                            Equipment ans = Equipment.searchEquipment(id);
                            
                            //streams open
                            os = new ObjectOutputStream(outputStream);
                            os.writeObject(ans);

                            sendData = outputStream.toByteArray();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                            serverSocket.send(sendPacket);
                            if(ans.getName()!=null)
                                newSentence="Equipment of ID"+id+" sent";
                        }catch (NullPointerException e){
                            System.out.println(e);
                            newSentence="No Equipment Found of certain ID\n";
                        }
                        break;
                    //Member Menu
                    case 4:
                        System.out.println("Add Member Function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();


                        is = new ObjectInputStream(in);
                        Member member = (Member) is.readObject();
                        try{
                            Exception e=member.addMember();
                            if (e==null){
                                newSentence = "Member Added";
                            }
                            else {
                                newSentence="Member already exists with this id\n"+e;
                            }
                        }
                        catch (Exception e1){
                            System.out.println(e1);
                            newSentence=e1+"";
                        }
                        break;
                    case 5:
                        //call viewAll func
                        System.out.println("View Members Function");
                        ArrayList<Member> allMembers = Member.viewAll();

                        //streams open
                        os = new ObjectOutputStream(outputStream);
                        os.writeObject(allMembers);
                
                        sendData = outputStream.toByteArray();
                        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                        newSentence = "View all Members sent from server";

                        break;
                    case 6:
                        //call search by id
                        System.out.println("Search Member function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();


                        dataIn = new DataInputStream(in);
                        id = dataIn.readInt();
                        try {
                            Member search = Member.searchMember(id);
                            //streams open
                            
                            os = new ObjectOutputStream(outputStream);
                            os.writeObject(search);

                            sendData = outputStream.toByteArray();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                            serverSocket.send(sendPacket);
                            if(search.getName()!=null)
                                newSentence="Member of ID"+id+" sent";
                        }catch (NullPointerException e){
                            System.out.println(e);
                            newSentence="No Member Found of certain ID\n";
                        }
                        break;
                    //Usage Menu
                    case 7:
                        System.out.println("Add Usage Function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();


                        is = new ObjectInputStream(in);
                        Usage add = (Usage) is.readObject();
                        try{
                            Exception e=add.addUsage();
                            if (e==null){
                                newSentence = "Usage Added";
                            }
                            else {
                                newSentence="Usage already exists with this id\n"+e;
                            }
                        }
                        catch (Exception e1){
                            System.out.println(e1);
                            newSentence=e1+"";
                        }
                        break;
                    case 8:
                        //call viewAll func
                        System.out.println("View Members Function");
                        ArrayList<Usage> allUsage = Usage.viewAllUsage();

                        //streams open
                        os = new ObjectOutputStream(outputStream);
                        os.writeObject(allUsage);
                
                        sendData = outputStream.toByteArray();
                        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                        newSentence = "All Usage sent from server";
                        
                        break;
                    case 9:
                        //call search by id
                        System.out.println("Search Usage function");
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        IPAddress = receivePacket.getAddress();
                        port = receivePacket.getPort();


                        dataIn = new DataInputStream(in);
                        id = dataIn.readInt();
                        int eqId =  dataIn.readInt();
                        try {
                            Usage searchUse = Usage.searchUsage(eqId, id);
                            //streams open
                            
                            os = new ObjectOutputStream(outputStream);
                            os.writeObject(searchUse);

                            sendData = outputStream.toByteArray();
                            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                            serverSocket.send(sendPacket);
                            if(searchUse.getMember().getName() != null && searchUse.getEquipment().getName() != null)
                                newSentence="Use of Member ID "+id+" and Equipment ID "+ eqId +" sent";
                        }catch (NullPointerException e){
                            System.out.println(e);
                            newSentence="No usage Found of certain ID\n";
                        }
                        break;
                    case 0:
                        newSentence = "Exitted";
                        break;
                    default:
                        newSentence = "Invalid Choice";
                        //invalid no.
                }
                sendData = newSentence.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                serverSocket.send(sendPacket);
            }while(choice != 0);
            System.out.println("Quitted");
        }
    }

}




