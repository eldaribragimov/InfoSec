import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FileProvider {

    public byte[] readfile(String file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] array = fileInputStream.readAllBytes();
        fileInputStream.close();
        return array;
    }

    public void writefile(String file,ArrayList<Long> arrayList,Main main) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int k = main.getK();
        for (int i = 0 ; i < arrayList.size() -1 ; i++){
            writefull(fileOutputStream,arrayList.get(i));
        }
        if (k!=0)
            writenotfull(fileOutputStream,arrayList.get(arrayList.size()-1),k);
        else
            writefull(fileOutputStream,arrayList.get(arrayList.size()-1));
        fileOutputStream.close();
    }

    public void writefull(FileOutputStream fileOutputStream,Long value) throws IOException {
        byte[] bytes = ByteBuffer.allocate(Byte.SIZE).putLong(value).array();
        fileOutputStream.write(bytes);
    }

    public void writenotfull(FileOutputStream fileOutputStream,Long value,int k) throws IOException {
        byte[] bytes = ByteBuffer.allocate(Byte.SIZE).putLong(value).array();
        for (int i = 0 ; i < k ; i++){
            fileOutputStream.write(bytes[i]);
        }
    }


}
