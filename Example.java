
package com.ptcmanaged.FP;

import java.io.BufferedReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;

public class Example
{   
	private static final Logger LOG = LoggerFactory.getLogger(Example.class);
	
    public static void main ( String[] args )
    {
		System.out.println("In Main Function");
    	FPS Serialconnector = new FPS();
    	BufferedReader br;
        try
        {
        	System.setProperty("gnu.io.rxtx.SerialPorts","/dev/ttyAMA0");
        	br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Opening the serial port");
        	LOG.info("Opening the Serial Port");
        	if(Serialconnector.open("/dev/ttyAMA0"))
        	{
        		while(true)
        		{
        			System.out.print("\n\nFingerPrint Example Program\n1.LED ON\n2.LED OFF\n3.Finger Pressed\n4.GetEnrollCount\n5.Enroll New Fingerprint\n6.Identity\n");
        			System.out.print("Enter Input:");
        			
        			int input = Integer.parseInt(br.readLine());
        			
        			switch(input)
        			{
        				case 1:
        					Serialconnector.LEDON();
    					break;
        				case 2:
        					Serialconnector.LEDOFF();
    					break;
        				case 3:
        					System.out.println("Finger Pressed:" + Serialconnector.IsPressFinger());
        					break;
        				case 4:
        					System.out.println("Enroll Count:" + Serialconnector.GetEnrollCount());
        					break;
        				case 5:
        					System.out.println("*******EnrollMent*******\nEnter Fingerprint ID:");
        					int enrollid  = Integer.parseInt(br.readLine());
        					
        					Serialconnector.NewEnrollment(enrollid);
        					
        					Thread.sleep(2000);
        					
        					break;
        				case 6:
        					// Identify fingerprint test
        					System.out.println("Please place your finger");
        					Boolean result= false;
        					while(result==false)
        					{
        						Serialconnector.CaptureFinger(false);
        						int id = Serialconnector.Identify1_N();
        						if (id <200)
        						{
        							System.out.println("Verified ID:" + id);
        							result =true;
        						}
        						else
        						{
        							System.out.println("Finger not found");
        							result =false;
        						}
        					}
        					break;
    						default:
    							System.out.println("Invaild Input");
    						break;
        			}
        		}
        	}
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}