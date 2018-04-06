import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.ArrayList;

public class KeyProvider {

    public ArrayList<String> keyprovide(String key){
        String sha256hex = DigestUtils.sha256Hex(key);
        sha256hex = new BigInteger(sha256hex,16).toString(2);
        while (sha256hex.length()<256){
            sha256hex = "0"+sha256hex;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        while (sha256hex.length()>0){
            arrayList.add(sha256hex.substring(0,32));
            sha256hex = sha256hex.substring(32);
        }
        int k = 0;
        while (k<16){
            arrayList.add(arrayList.get(k%8));
            k++;
        }
        k = 7;
        while (k>-1){
            arrayList.add(arrayList.get(k));
            k--;
        }
        return arrayList;
    }

}
