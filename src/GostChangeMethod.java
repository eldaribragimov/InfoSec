import java.util.ArrayList;

public class GostChangeMethod {

    int[][] array;

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }



    public ArrayList<Long> process(byte[] arrayListfromfile, int[] arrayListkey) {

        ArrayList<Long> arrayList = new ArrayList<>();
        long b = 0;

        // таблица замен

        int [][] a = new int[][]{{4, 10, 9, 2, 13, 8, 0, 14, 6, 11, 1, 12, 7, 15, 5, 3},{14,11,4,12,6,13,15,10,2,3,8,1,0,7,5,9},{5,8,1,13,10,3,4,2,14,15,12,7,6,0,9,11},{7,13,10,1,0,8,9,15,14,4,6,12,11,2,5,3},{6,12,7,1,5,15,13,8,4,10,9,14,0,3,11,2},{4,11,10,0,7,2,1,13,3,6,8,5,9,12,15,14},{13,11,4,1,3,15,5,9,0,10,14,7,6,8,2,12},{1,15,13,0,5,7,10,4,9,2,3,14,6,11,8,12}};

        setArray(a);

        for (int i = 0 ; i < arrayListfromfile.length ; i++){
            Long s = Byte.toUnsignedLong(arrayListfromfile[i]);
            b = b | s;
            if ( ( i + 1 ) % 8 == 0){
                arrayList.add(b);
                b = 0;
            }
            else
                b = b << 8;
        }
        for (int i = 0 ; i < arrayList.size() ; i++){
            arrayList.set(i,function(arrayList.get(i),arrayListkey));
        }
        return arrayList;
    }


    public long function(long value,int[] arrayListkey){
        int n1 ;
        int n2 ;
        int [] a;
        int[][] b = getArray();
        int v;
        n1 = (int)((value & (-4294967296L)) >>> 32);   // выделение из блока 64 бит первого блока 32 бит
        n2 = (int)(value & 4294967295L );            // выделение из блока 64 бит второго блока 32 бит

            for (int c = 0 ; c < arrayListkey.length; c++) {
                a = new int[8];
                v = (n2+arrayListkey[c]);         //  сложение по модулю 2^32 второго блока и ключа
                a[0] = (v & (-268435456)) >>> 28; // выделение из блока 32 бит первого блока 4 бит
                a[1] = (v & 251658240) >>> 24;   // выделение из блока 32 бит второго блока 4 бит
                a[2] = (v & 15728640) >>> 20;    // выделение из блока 32 бит третьего блока 4 бит
                a[3] = (v & 983040) >>> 16;     // выделение из блока 32 бит четвертого блока 4 бит
                a[4] = (v & 61440) >>> 12;     // выделение из блока 32 бит пятого блока 4 бит
                a[5] = (v & 3840) >>> 8;       // выделение из блока 32 бит шестого блока 4 бит
                a[6] = (v & 240) >>> 4;       // выделение из блока 32 бит седьмого блока 4 бит
                a[7] = v & 15;             // выделение из блока 32 бит восьмого блока 4 бит

                // табличная подстановка

                for (int j = a.length - 1; j > -1; j--) {
                    int m = a.length-j-1;
                    a[m] = b[j][a[m]];
                }

                // собираем обратно блок 32 бит

                a[0] = a[0] << 28;
                a[1] = a[1] << 24;
                a[2] = a[2] << 20;
                a[3] = a[3] << 16;
                a[4] = a[4] << 12;
                a[5] = a[5] << 8;
                a[6] = a[6] << 4;
                v = a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|a[7]; // соединяем блоки по 4 бит в один блок 32 бит

                // выполняем сдвиг влево на 11 бит

                int buf = v >>> 20;
                v = v << 11;
                v = v | buf;

                // сложение по модулю 2

                v = v ^ n1;

                if (c > 30){       // если раунд последний
                    n1 = v;
                }
                else {
                    n1 = n2;
                    n2 = v;
                }
            }

            // возвращаем склееный из n1 и n2 блок 64 бит

            return ( Integer.toUnsignedLong(n1) << 32 ) | (( (long) n2 ) & 4294967295L ) ;
    }

}
