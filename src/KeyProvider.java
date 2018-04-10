import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.ArrayList;

public class KeyProvider {

    public int[] keyprovideencode(String key){

        String sha256hex = DigestUtils.sha256Hex(key); // пропускаем ключ через хэш-функцию

        int k = 0;
        int[] array = new int[32];

        for (int i = 0 ; i < 8 ; i++) {
            array[i] = Integer.parseUnsignedInt(sha256hex.substring(k, k + 8), 16);
            k += 8;
        }
        k = 0;

        for (int i = 8 ; i < array.length ; i++){
            if (i<24) {
                array[i] = array[k];
            }
            else {
                array[i] = array[23%k];
            }
            k++;
        }
        return array;
    }

    public int[] keyprovidedecode(String key){

        String sha256hex = DigestUtils.sha256Hex(key);
        int k = 0;
        int[] array = new int[32];
        for (int i = 0 ; i < 8 ; i++) {
            array[i] = Integer.parseUnsignedInt(sha256hex.substring(k, k + 8), 16);
            k += 8;
        }
        k = 7;

        for (int i = 8 ; i < array.length ; i++){
            array[i] = array[k];
            k--;
            if (k < 0)
                k = 7;
        }
        return array;
    }

}
