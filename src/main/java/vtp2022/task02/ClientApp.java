package vtp2022.task02;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Client App
 *
 */
public class ClientApp 
{
    public static void main( String[] args )
    {
        try
        (   Socket socket = new Socket("68.183.239.26", 80);   )
        {   
            System.out.println("Starting connection ... ");
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

            String request = ois.readUTF();

            // get the list of integers
            String[] integerList = request.split(" ")[1].split(",");

            // calculate average
            int sum = 0;
            float count = 0;
            for(int i=0; i<integerList.length; i++)
            {   sum += Integer.parseInt(integerList[i]);
                count++;                                  }
            float average = sum/count;
            System.out.println("Average is " + average);        // print check
            
            // 
            oos.writeUTF(request.split(" ")[0]);
            oos.writeUTF("Chen Luwei");
            oos.writeUTF("lulu@yahoo.com.sg");
            oos.writeFloat(average);
            oos.flush();

            
            Boolean correct = ois.readBoolean();
 
            if( correct )
            {   System.out.println("SUCCESS");}
            else
            {   String failure = ois.readUTF();
                System.out.println("FAILED. Error msg is :" + failure);        } 


        }
        catch(IOException e){   System.err.println();   }
    }
}
