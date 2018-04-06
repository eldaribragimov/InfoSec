import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileProvider {

    RandomAccessFile randomAccessFile;
    ArrayList<String> arrayList;

    public ArrayList fileprovide(String file) throws IOException {
        randomAccessFile = new RandomAccessFile(file,"r");
        arrayList = new ArrayList<>();
        int i;
        while ((i=randomAccessFile.read())!=-1){
            String res = String.valueOf(Integer.toBinaryString(i));
            while (res.length()-8!=0){
                res = "0"+res;
            }
            arrayList.add(res);
        }
        randomAccessFile.close();
        return arrayList;
    }


}
