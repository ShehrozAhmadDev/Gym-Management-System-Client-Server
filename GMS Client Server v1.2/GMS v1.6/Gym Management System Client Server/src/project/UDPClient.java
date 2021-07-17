package project;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class UDPClient {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader inFromUser =    new BufferedReader(new InputStreamReader(System.in));
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int Port = 132;
        Scanner get = new Scanner(System.in);
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        int option=0,choice=0;
        boolean isMenu =true;
        String modifiedSentence;
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;
        do{
            while(isMenu){
                System.out.println("1.Equipment Menu | 2.Manage Members | 3.Equipment Usage | 0.Exit");
                System.out.println("Enter Number for function: ");
                choice = get.nextInt();
                if(choice<0 && choice>3)
                    continue;
                else
                    isMenu=false;
            }
            switch(choice){
                case 1:
                    //menu choice for equipment
                    do{
                        System.out.println("1.Add Equipment | 2.View Equipment | 3.Search Equipment | 0.Back");
                        System.out.println("Enter Number for function: ");
                        option = get.nextInt();
                    }while(option<0 && option>3);
                    // choice = option;
                    if(option==0){
                        isMenu = true;
                        continue;
                    }
                    break;
                case 2:
                    //member menu
                    do{
                        System.out.println("1.Add Member | 2.View Member | 3.Search Member | 0.Back");
                        System.out.println("Enter Number for function: ");
                        option = get.nextInt();
                    }while(option<0 && option>3);
                    if(option!=0)
                        option += 3;
                    else{
                        isMenu=true;
                        continue;
                    }
                    break;
                case 3:
                    //usage
                    do{
                        System.out.println("1.Add Usage | 2.View Usage | 3.Search Usage | 0.Back");
                        System.out.println("Enter Number for function: ");
                        option = get.nextInt();
                    }while(option<0 && option>3);
                    if(option!=0)
                        option += 6;
                    else{
                        isMenu=true;
                        continue;
                    }
                    break;
                case 0:
                    option = choice;
                    break;
            }

            //send chosen option no.
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outputStream);
            dataOut.writeInt(option);
            
            sendData = outputStream.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
            clientSocket.send(sendPacket);
            dataOut.flush();
            outputStream.flush();

            switch(option){
                case 1:
                    //input name,cost,manufacturer etc
                    System.out.println("Enter Name for Equipment: ");
                    String name = inFromUser.readLine();
                    System.out.println("Enter Manufacturer for Equipment: ");
                    String manufacturer = inFromUser.readLine();
                    int cost;
                    int id;

                    System.out.println("Enter Cost of Equipment: ");
                    cost = get.nextInt();

                    System.out.println("Enter Id of Equipment: ");
                    id = get.nextInt();

                    Equipment sampleEquipment = new Equipment(id, name,manufacturer,cost);
                    //streams open
                    ObjectOutputStream os = new ObjectOutputStream(outputStream);
                    os.writeObject(sampleEquipment);

                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    System.out.println("Message sent from client");
                    break;
                case 2:
                    //viewAll
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
                    ObjectInputStream is = new ObjectInputStream(in);
                    ArrayList<Equipment> equipments = (ArrayList<Equipment>) is.readObject();

                    System.out.println("ID\t Name\t Manufacturer\t Cost");
                    for(int i=0;i<equipments.size();i++)
                        System.out.println(equipments.get(i).toString()+"\n");
                    break;
                case 3:
                    //input search id
                    System.out.println("Enter Id of Equipment to Search: ");
                    id = get.nextInt();
                    
                    dataOut.writeInt(id);
                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    dataOut.flush();
                    outputStream.flush();
                    
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    in = new ByteArrayInputStream(receiveData); //put after receiving data
                    is = new ObjectInputStream(in);
                    try{
                        Equipment received = (Equipment) is.readObject();
                        System.out.println("ID\t Name\t Manufacturer\t Cost");
                        System.out.println(received.toString());
                    }catch(NullPointerException e){
                        System.out.println("No Equipment of given ID");
                    }
                    break;
                    
                //Member Menu
                case 4:
                    System.out.println("Enter Name: ");
                    name = inFromUser.readLine();
                    System.out.println("Enter Contact No. : ");
                    String contact = inFromUser.readLine();
                    int age;

                    System.out.println("Enter your age: ");
                    age = get.nextInt();

                    System.out.println("Enter the numeric Id you want: ");
                    id = get.nextInt();

                    Member member = new Member(id, name, age, contact);
                    //streams open
                    os = new ObjectOutputStream(outputStream);
                    os.writeObject(member);

                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    System.out.println("Message sent from client");
                    break;
                case 5:
                        //viewAll
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        clientSocket.receive(receivePacket);
                        in = new ByteArrayInputStream(receiveData); //put after receiving data
                    is = new ObjectInputStream(in);
                        ArrayList<Member> members = (ArrayList<Member>) is.readObject();

                        System.out.println("ID\t Name\t Age\t Contact");
                        for(int i=0;i<members.size();i++)
                        System.out.println(members.get(i).toString()+"\n"); 
                    break;
                case 6:
                    //input search id
                    System.out.println("Enter Id of Member to Search: ");
                    id = get.nextInt();

                    dataOut.writeInt(id);
                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    dataOut.flush();
                    outputStream.flush();

                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    in = new ByteArrayInputStream(receiveData); //put after receiving data
                    is = new ObjectInputStream(in);
                    try{
                        Member member2 = (Member) is.readObject();
                        System.out.println("ID\t Name\t Age\t Contact");
                        System.out.println(member2.toString());
                    }catch(NullPointerException e){
                        System.out.println("No Member of given ID");
                    }
                    break;
                //Usage Menu
                case 7:
                    int memberId;

                    System.out.println("Enter Member ID: ");
                    memberId = get.nextInt();

                    System.out.println("Enter the Equipment Id: ");
                    id = get.nextInt();

                    Usage usage = new Usage(id,memberId);
                    //streams open
                    os = new ObjectOutputStream(outputStream);
                    os.writeObject(usage);

                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    
                    break;
                case 8:
                    //viewAll
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    in = new ByteArrayInputStream(receiveData); //put after receiving data
                    is = new ObjectInputStream(in);
                    ArrayList<Usage> usages = (ArrayList<Usage>) is.readObject();

                    System.out.println("MemberID\t Name\t Age\t Contact\t EquipmentID\t Name\t Manufacturer\t Cost");
                    for(int i=0;i<usages.size();i++)
                    System.out.println(usages.get(i).toString()+"\n"); 
                    break;
                case 9:
                    //input search id
                    System.out.println("Enter Id of Member to Search: ");
                    id = get.nextInt();
                    System.out.println("Enter Id of Equipment to Search: ");
                    int Eqid = get.nextInt();
                    
                    dataOut.writeInt(id);
                    dataOut.writeInt(Eqid);
                    sendData = outputStream.toByteArray();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
                    clientSocket.send(sendPacket);
                    dataOut.flush();
                    outputStream.flush();

                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    in = new ByteArrayInputStream(receiveData); //put after receiving data
                    is = new ObjectInputStream(in);
                    try{
                        Usage usage2 = (Usage) is.readObject();
                        System.out.println("MemberID\t Name\t Age\t Contact\t EquipmentID\t Name\t Manufacturer\t Cost");
                        System.out.println(usage2.toString());
                    }catch(NullPointerException e){
                        System.out.println("No Usage of given IDs");
                    }
                    break;
                case 0:
                    System.out.println("Quitting");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            //receiving reply
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            modifiedSentence = new String(receiveData,0,receivePacket.getLength());
            System.out.print("From Server: "+modifiedSentence);
            System.out.println("\n");
        }while(choice!=0);
        clientSocket.close();
        get.close();
    }

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
        
}
