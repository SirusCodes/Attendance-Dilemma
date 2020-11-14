package processes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main
{
    public static void main(String[] args)
    {
        String line = "";
        String splitBy = ",";
        try
        {

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\chirag\\Desktop\\attendance.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] Student = line.split(splitBy);
                System.out.println("Student [ Name=" +Student [0] + ", Roll no="+Student[1]+", Date="+Student[2]+"]");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}