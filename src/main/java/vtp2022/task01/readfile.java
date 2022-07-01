package vtp2022.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class readfile {
    
    
    public static void read(String data, String template){
        try{
        InputStream is = new FileInputStream(data);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        String[] headers = br.readLine().split(",");    // get headers for data

        String line;
        List<String> templateData = new LinkedList<>();
        while (null!=(line = br.readLine()))
        {   templateData.add(line);    }     // add non-headers data to list                   
          
        //System.out.println(templateData.size());    // print check

        br.close();
        isr.close();
        is.close();
 
        // add underscore to headers to replace template
        String[] modifiedHeaders = new String[headers.length];
        for(int i=0;i<headers.length;i++)
        {   modifiedHeaders[i] = "__" + headers[i] + "__" ; }


        //System.out.println(Arrays.toString(modifiedHeaders));   // print Check

            // loop each datapoint through template
            for(String eachData:templateData)
            {   
                System.out.println("\n");
                InputStream is2 = new FileInputStream(template);
                InputStreamReader isr2 = new InputStreamReader(is2);
                BufferedReader br2 = new BufferedReader(isr2);
                while(null!=(line=br2.readLine()))
                {   
                    for(int i=0;i<headers.length; i++)
                    {   if (line.contains(modifiedHeaders[i])   )
                        {   String target = modifiedHeaders[i];
                            String replacement = eachData.split(",")[i];
                            
                            // new line "\n" is not displaying properly. Manually split the strings
                            if (replacement.contains("\\n"))
                            {   
                                int cutindex = replacement.indexOf("\\");
                                String concatAdd = replacement.substring(0,cutindex)+ "\n";
                                String remaining = replacement.substring(cutindex+2);
                                while (remaining.indexOf("\\") != -1)
                                {   
                                    cutindex = remaining.indexOf("\\");
                                    concatAdd += remaining.substring(0,cutindex) + "\n" ;
                                    remaining = remaining.substring(cutindex+2);
                                }
                            concatAdd += remaining;
                            replacement = concatAdd;
                            }

                            line = line.replace(target, replacement);                  
                        }                  
                    }
                    System.out.println(line);               
                }
                br2.close();
            }

        }
        catch(IOException e)
        {   System.err.println(e);  }
    }
}
