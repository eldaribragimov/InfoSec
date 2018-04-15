import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DataProvider {

    public byte[] read(){
        String s = "12345678"; // блок 64 бит
        return s.getBytes();
    }


    public byte[] handlier(ArrayList<Long> arrayList){
        byte[] bytearray = new byte[ 8 * arrayList.size() ];
        for (int i = 0 ; i < arrayList.size() ; i+=8) {
            byte[] bytes = ByteBuffer.allocate(Byte.SIZE).putLong(arrayList.get(i)).array();
            for (int j = 0 ; j < bytes.length ; j++){
                bytearray[j] = bytes[j];
            }
        }
        return bytearray;
    }


}
