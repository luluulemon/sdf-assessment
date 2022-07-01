package vtp2022.task01;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {   
        String data = "tour_packages.csv";
        String template = "tour_packages.txt";

        if (args.length>0)
        {   data = args[0];
            template = args[1]; 
            readfile.read(data, template);
        }
        else 
        {    System.out.println("Please specify file sources"); }


    }
}
