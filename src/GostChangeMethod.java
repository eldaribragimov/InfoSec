import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GostChangeMethod {

    int[][] array;

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    ArrayList<String> arrayList;

    public String diplacement(String value,int radix) {
       char[] a = value.toCharArray();
       char[] b = value.toCharArray();
       int c = value.length();
       for (int i = 0 ; i< a.length ; i++){
           int j = i - radix;
           if (j > -1){
               a[i] = b[j];
           }
           else {
               j = a.length + j;
               a[i] = b[j];
           }
       }
       String res = String.valueOf(a);
       return res;
    }

    public String xor(String value1 , String value2,int c){
        String res ="";
        int x;
        for (int i = 0 ; i<value1.length();i++){
            if (c>5) {
                x = Integer.parseInt(String.valueOf(value1.charAt(i))) + Integer.parseInt(String.valueOf(value2.charAt(i)));
                x = x % 2;
                res += x;
            }
            else {
                x = Integer.parseInt(String.valueOf(value1.charAt(i))) + Integer.parseInt(String.valueOf(value2.charAt(i)));
                x = x % 2;
                res += x;
            }
        }
        return res;
    }

    public void process(ArrayList<String> arrayListfromfile,ArrayList<String> arrayListkey,Main main) throws IOException {

        arrayList = new ArrayList<>();
        String c ="";
        int [][] a = new int[][]{{4, 10, 9, 2, 13, 8, 0, 14, 6, 11, 1, 12, 7, 15, 5, 3},{14,11,4,12,6,13,15,10,2,3,8,1,0,7,5,9},{5,8,1,13,10,3,4,2,14,15,12,7,6,0,9,11},{7,13,10,1,0,8,9,15,14,4,6,12,11,2,5,3},{6,12,7,1,5,15,13,8,4,10,9,14,0,3,11,2},{4,11,10,0,7,2,1,13,3,6,8,5,9,12,15,14},{13,11,4,1,3,15,5,9,0,10,14,7,6,8,2,12},{1,15,13,0,5,7,10,4,9,2,3,14,6,11,8,12}};
        setArray(a);

        for (int i = 0 ; i < arrayListfromfile.size() ; i++){
            if ((c.length()+1)%65!=0){
                c+=arrayListfromfile.get(i);
            }
            else{
                arrayList.add(c);
                c = "";
            }
        }
        arrayList = perform(arrayList,arrayListkey);
        write(arrayList,main);
    }


    public ArrayList<String> perform(ArrayList<String> arrayList,ArrayList<String> arrayListkey){
        String n1 = "";
        String n2 = "";
        String [] a;
        int k ;
        String res;
        long m = (long) Math.pow(2, 32);
        int[][] b = getArray();
        long v;
        for (int i = 0 ; i < arrayList.size(); i++) {
            n1 = arrayList.get(i).substring(0, 32);
            n2 = arrayList.get(i).substring(32);
            for (int c = 0 ; c < arrayListkey.size(); c++) {
                res = "";
                k = 0;
                a = new String[8];
                v = Long.parseLong(n1, 2)+Long.parseLong(arrayListkey.get(c), 2);
                v = v%m;
                for (int j = 0; j < a.length; j++) {
                    String l = Long.toBinaryString(v);
                    while (l.length()<32)
                        l = "0"+l;
                    a[j] = l.substring(k, k + 4);
                    k += 4;
                }
                for (int j = a.length - 1; j > -1; j--) {
                    String z = a[a.length - 1 - j];
                    int x = Integer.parseInt(z, 2);
                    z = Integer.toBinaryString(b[j][x]);
                    while (z.length() < 4) {
                        z = "0" + z;
                    }
                    res += z;
                }
                if (c>5){
                    res = diplacement(res, 11);
                    res = xor(res, n2,c);
                }
                else {
                    res = diplacement(res, 11);
                    res = xor(res, n2,c);
                }
                n2 = n1 ;
                n1  = res;
                arrayList.set(i,n1+n2);
            }
        }
        return arrayList ;
    }

    public void write(ArrayList<String> arrayList, Main main) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(main.getNewfile(),"rw");
        Object[] objects = arrayList.toArray();
        for (int i = 0 ; i < objects.length ; i++) {
            int k = 0;
            while (k<objects[i].toString().length()){
                int value = Integer.parseInt(objects[i].toString().substring(k,k+8),2);
                randomAccessFile.write(value);
                k+=8;
            }
        }
        randomAccessFile.close();
    }

}
