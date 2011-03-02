package game;

/*import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
*/

public class UploadScore {

	    public static void doScore(long scoreTimeStart) {
	        
	        /*InputStream in = null;   
	        OutputStream out = null;
	        String user = JOptionPane.showInputDialog("Enter Your Student ID for score upload:");
	        //String whereAt = "nowhere";
	        
	        String urlname = "http://www.creative-interaction.com/cgi-bin/record.cgi?";
	        urlname += "game=LLC";
	        urlname += "&time=" + scoreTimeStart;
	        urlname += "&score=" + scoreTimeStart;
	        urlname += "&user=" + user;
	        
	        try {
	            // Check the arguments

	            
	            // Set up the streams

	          //  whereAt="before arg fetch";
	            //urlname = args[0];
	          //  whereAt="before URL fetch";
	            URL url = new URL(urlname);   // Create the URL
	            URLConnection uc = url.openConnection();
	            uc.setDefaultUseCaches(false);
	            uc.setUseCaches(false);
	            uc.setRequestProperty("Cache-Control","max-age=0,no-cache");
	            uc.setRequestProperty("Pragma","no-cache");
	            //uc.setRequestProperty("Cache-Control","no-cache");

	           // whereAt="before OpenStream ";
	            //in = url.openStream();        // Open a stream to it
	            in = uc.getInputStream();
//	            whereAt="before System.out ";
	            out = System.out;
	            
	            // Now copy bytes from the URL to the output stream
	            byte[] buffer = new byte[4096];
	            int bytes_read;
//	            whereAt="before Reading";
	            
	            while((bytes_read = in.read(buffer)) != -1)
	                out.write(buffer, 0, bytes_read);
//	            whereAt = "after reading";
	        }
	        // On exceptions, print error message and usage message.
	        catch (Exception e) {
	        	//e.printStackTrace();
	            //System.err.println("Exception for "+urlname+" @ "+whereAt+" = "+e);
	        }
	        finally {  // Always close the streams, no matter what.
	            try { in.close();  out.close(); } catch (Exception e) {}
	        }
	    }
*/
	    }
}
