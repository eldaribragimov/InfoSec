import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FileProvider {

    public byte[] readfileencode(String file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] array = fileInputStream.readAllBytes();
        fileInputStream.close();
        if (array.length%8!=0)
            System.out.println("Error with file length\nSome data will lose");
        return array;
    }

    public byte[] readfiledecode(String file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] array = fileInputStream.readAllBytes();
        fileInputStream.close();
        return array;
    }

    public void writefile(String file,ArrayList<Long> arrayList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0 ; i < arrayList.size(); i++){
            write(fileOutputStream,arrayList.get(i));
        }
        fileOutputStream.close();
    }

    public void write(FileOutputStream fileOutputStream,Long value) throws IOException {
        byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
        fileOutputStream.write(bytes);
    }


}
