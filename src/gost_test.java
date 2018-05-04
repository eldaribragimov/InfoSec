import com.oracle.tools.packager.IOUtils;
import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;

public class gost_test {

    public static void main(String[] args) {
        String[] bytes = {"73","65","74","79","62","20","32","33","3D","68","74","67","6E","65","6C","20","2C","65","67","61","73","73","65","6D","20","73","69","20","73","69","68","54"};
        //byte[] key = {115, 61,44 ,32 ,101 ,104 ,101 ,115 ,116 ,116 ,103 ,105 ,121 ,103 ,97 ,32 ,98 ,110 ,115 ,115 ,32 ,101, 115 ,105 ,50 ,108, 101, 104, 51, 32, 109 ,84 };
        byte[] key = new byte[32];
        for (int i = 0; i < bytes.length ; i++){
            key[i] = Byte.parseByte(bytes[i],16);
        }
        /*
        byte[] data = new byte[32];
        byte[] init = new byte[32];
        String[] f = {"E7","86","04","19","0D","2A","56","2D","8D","34","58","99","00","FF","0E","28","52","03","EB","C8","5D","9B","CF","FD","42","AB","BC","CE","32","BC","0B","1B"};
        for (int i = 0; i < f.length ; i++){
            data[i] = ByteBuffer.allocate(4).putInt(Integer.parseUnsignedInt(f[i],16)).array()[3];
        }
        GoshHash goshHash = new GoshHash();
        byte[] res = goshHash.shifting_transformation(init,key,data);
        goshHash.print(res);
        */

        //MessageDigest digest = MessageDigest.getInstance(GostConsts.GOST_DIGEST_ALGORITHM_NAME, CommonConstants.TRUSTED_JAVA_PROVIDER);
        //byte[] result = digest.digest(key);
        //LOGGER.info("hash: " + new String(Hex.encodeHex(result, true)));
    }

}
