import java.math.BigInteger;
import java.util.ArrayList;

public class GoshHash {

    public byte[] xor(byte[] a , byte[] b){
        int n;
        if (a.length>b.length)
            n = a.length;
        else n = b.length;
        byte[] res = new byte[n];
        for (int i = 0 ; (i < a.length) && (i < b.length) ; i++){
            res[i] = (byte) (a[i] ^ b[i]);
        }
        return res;
    }

    public byte[] reverse(byte[] bytes){
        byte[] buf = bytes.clone();

        for (int i = bytes.length - 1 ; i > -1 ; i--){
            bytes[i] = buf[bytes.length - 1-i];
        }
        return bytes;
    }

    public void process(byte[] data){

        data = reverse(data).clone();

        byte[] initvector = new byte[32];
        byte[] L = new byte[32];
        byte[] sum = new byte[32];

        ArrayList<byte[]> arrayList = new ArrayList<>();
        int n = data.length / 32;
        if (data.length%32!=0)
            n++;
        int k = 32 - data.length%32 ;
        //byte[] a = new byte[32];
        int m = 0;
        int size = data.length;

        for (int i = 0 ; i < n  ; i++){
            byte[] a = new byte[32];
            for (int j = 0 ; (j<32)&&(j<size) ; j++){
                a[j] = data[j+m];
            }
            m+=32;
            size-=32;
            arrayList.add(a);
        }
        byte[] last = ZeroAddToArrayfunction(arrayList.get(arrayList.size()-1),k);
        arrayList.set(arrayList.size()-1,last);
        ArrayList<byte[]> keys;
        byte[] h = initvector.clone();
        for (int i = 0 ; i < arrayList.size() ; i++){
            keys = key_generate(h,arrayList.get(i));
            print(keys.get(0));
            System.out.println("\n");
            print(keys.get(1));
            System.out.println("\n");
            print(keys.get(2));
            System.out.println("\n");
            print(keys.get(3));
            System.out.println("\n");
            System.out.println("Hash\n");
            byte[] hash = gosh_Hash(keys,h);
            print(hash);
            System.out.println("\n");
            h = shifting_transformation(h,arrayList.get(i),hash);
            System.out.println('\n');
            print(h);
            BigInteger bigIntegerL = new BigInteger(L);
            bigIntegerL = bigIntegerL.add(BigInteger.valueOf(256)).mod(BigInteger.valueOf((long)Math.pow(2,256)));
            L = bigIntegerL.toByteArray().clone();
            BigInteger bigIntegerSum = new BigInteger(sum);
            bigIntegerSum = bigIntegerSum.add(new BigInteger(arrayList.get(i)));
            sum = bigIntegerSum.toByteArray().clone();
        }

    }

    public byte[] psi(byte[] a){
        byte[] y1 = new byte[2];
        byte[] y2 = new byte[2];
        byte[] y3 = new byte[2];
        byte[] y4 = new byte[2];
        byte[] y13 = new byte[2];
        byte[] y16 = new byte[2];

        for (int i = 0 ; i < 2 ; i++){
            y1[i] = a[i];
        }

        for (int i = 0 ; i < 2 ; i++){
            y2[i] = a[i+2];
        }

        for (int i = 0 ; i < 2 ; i++){
            y3[i] = a[i+4];
        }

        for (int i = 0 ; i < 2 ; i++){
            y4[i] = a[i+6];
        }

        for (int i = 0 ; i < 2 ; i++){
            y13[i] = a[i+24];
        }

        for (int i = 0 ; i < 2 ; i++){
            y16[i] = a[i+30];
        }

        byte[] buf;

        buf = xor(y1,y2).clone();
        buf = xor(buf,y3).clone();
        buf = xor(buf,y4).clone();
        buf = xor(buf,y13).clone();
        buf = xor(buf,y16).clone();
        byte[] res = new byte[32];

        for (int i = 0 ; i < 2 ; i++){
            res[i] = buf[i];
        }

        for (int i = 2 ; i < res.length ; i++){
            res[i] = a[i-2];
        }
        return res;
    }

    public byte[] shifting_transformation(byte[] H_in , byte[] m , byte[] S){
        for (int i = 0 ; i < 12 ; i++){
            S = psi(S).clone();
        }
        S = xor(S,m).clone();
        S = psi(S).clone();
        S = xor(S,H_in).clone();
        for (int i = 0 ; i < 61 ; i++){
            S = psi(S).clone();
        }
        return S;
    }

    public void print(byte[] data){
        for (int i = data.length -1 ; i > -1 ; i--){
            //System.out.print(data[i]+" ");
            System.out.print(Integer.toString(Byte.toUnsignedInt(data[i]),16)+" ");
        }
    }

    public byte[] ZeroAddToArrayfunction(byte[] a , int k){
        for (int i = 0 ; i < 32-k ; i++){
            byte buf = a[i];
            a[i] = 0;
            a[i+k] = buf;
        }
        return a;
    }

    public byte[] gosh_Hash(ArrayList<byte[]> keys, byte[] data) {

        byte[] block1 = new byte[8];
        byte[] block2 = new byte[8];
        byte[] block3 = new byte[8];
        byte[] block4 = new byte[8];

        for (int i = 0 ; i < 8 ; i++){
            block4[i] = data[i];
        }

        for (int i = 0 ; i < 8 ; i++){
            block3[i] = data[i+8];
        }

        for (int i = 0 ; i < 8 ; i++){
            block2[i] = data[i+16];
        }

        for (int i = 0 ; i < 8 ; i++){
            block1[i] = data[i+24];
        }

        block1 = new GostChangeMethod().encodeprocess(block1,keys.get(0)).clone();
        block2 = new GostChangeMethod().encodeprocess(block2,keys.get(1)).clone();
        block3 = new GostChangeMethod().encodeprocess(block3,keys.get(2)).clone();
        block4 = new GostChangeMethod().encodeprocess(block4,keys.get(3)).clone();

        for (int i = 0 ; i < 8 ; i++){
            data[i] = block4[i];
        }

        for (int i = 0 ; i < 8 ; i++){
            data[i+8] = block3[i];
        }

        for (int i = 0 ; i < 8 ; i++){
            data[i+16] = block2[i];
        }

        for (int i = 0 ; i < 8 ; i++){
            data[i+24] = block1[i];
        }

        return data;

    }

    public ArrayList<byte[]> key_generate(byte[] H_in, byte[] m){

        byte[] c2 = new byte[32];
        byte[] c4 = new byte[32];
        byte[] c3 = {-1,0,-1,-1,0,0,0,-1,-1,0,0,-1,0,-1,-1,0,0,-1,0,-1,0,-1,0,-1,-1,0,-1,0,-1,0,-1,0};

        byte[] U = H_in.clone();
        byte[] V = m.clone();
        byte[] W;
        byte[] k1;
        W = xor(U,V);
        ArrayList<byte[]> arrayList_key = new ArrayList<>();
        ArrayList<byte[]> arrayList_const = new ArrayList<>();
        arrayList_const.add(c2);
        arrayList_const.add(c3);
        arrayList_const.add(c4);
        k1 = P_function(W);
        arrayList_key.add(k1);
        for (int i = 2 ; i < 5 ; i++){
            U = xor(arrayList_const.get(i-2),A_function(U));
            V = A_function(A_function(V));
            byte[] ki = P_function(xor(U,V));
            arrayList_key.add(ki);
        }
        return arrayList_key;
    }

    public byte[] normalize(byte[] block,int n){
        byte[] buf = new byte[n];
        for (int i = n-1 ; i > -1 ; i--){
            if ((n-1-i)<block.length)
                buf[i] = block[i-(n-block.length)];
        }
        return buf;
    }

    public byte[] P_function(byte[] block){
        int i = 3;
        int k = 8;
        byte[] buf = block.clone();
        for (int j = 0 ; j < block.length ; j++){
            buf[j] = block[(8*i + k)-1];
            i--;
            if ((i<0)&&(k>0)){
                i=3;
                k--;
            }
        }
        return buf;
    }

    public byte[] A_function(byte[] block){

        byte[] y1 = new byte[8];
        byte[] y2 = new byte[8];
        byte[] y3 = new byte[8];
        byte[] y4 = new byte[8];

        for (int i = 0 ; i < 8 ; i++){
            y4[i] = block[i];
        }

        for (int i = 8 ; i < 16 ; i++){
            y3[i-8] = block[i];
        }
        for (int i = 16 ; i < 24 ; i++){
            y2[i-16] = block[i];
        }
        for (int i = 24  ; i < 32 ; i++){
            y1[i-24] = block[i];
        }

        y1 = xor(y1,y2);


        for (int i = 0 ; i < 8 ; i++){
            block[i] = y1[i];
        }

        for (int i = 8 ; i < 16 ; i++){
            block[i] = y4[i-8];
        }
        for (int i = 16 ; i < 24 ; i++){
            block[i] = y3[i-16];
        }
        for (int i = 24  ; i < 32 ; i++){
            block[i] = y2[i-24];
        }
        return block;
    }

    public void mod256(byte[] a, byte[] b){
        int size;
        if (a.length>b.length)
            size = a.length;
        else size = b.length;
        for (int i = size-1 ; i >-1 ; i++ ){
            byte c = a[i];
        }
    }


}
