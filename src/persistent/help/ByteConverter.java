package persistent.help;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteConverter {

	/** http://stackoverflow.com/questions/3736058/java-object-to-byte-and-byte-to-object-converter-for-tokyo-cabinet */

public  byte[] serialize(Object obj) throws IOException {
  
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {	
    	ObjectOutputStream os = new ObjectOutputStream(out);
    	os.writeObject(obj);
    } catch (IOException e){
    	
    } catch (java.lang.NullPointerException n){
    	System.out.println("n" + n.getMessage());
    } catch (Exception e){
    	System.out.println("bella " + e.getMessage());
    }
    finally {
    	
    }
    return out.toByteArray();
}
public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);; 
    try {
	
    } catch (Exception e){
    	
    }
    
    finally {
    	
    }
    
    return is.readObject();
    
}

public BufferedImage Byte2image(byte [] foto){
	
	return new BufferedImage(0,0,BufferedImage.TYPE_3BYTE_BGR);
}


}
