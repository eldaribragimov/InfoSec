import org.junit.Assert;
import org.junit.Test;

public class Testing {

    @Test

    public void test()  {
        byte[] arraydata = new DataProvider().read();  // считываем блок
        byte[] array = new GostChangeMethod().encodeprocess(arraydata,new Key().keygenerate()); //  зашифровываем блок
        array = new GostChangeMethod().decodeprocess(array,new Key().keygenerate()); // расшифровываем зашифрованный блок тем же ключом


        // проверяем что первоначальный блок совпадает с расшифрованным

        boolean c = true;
        for (int i = 0 ; i < array.length ; i++){
            if (array[i]!=arraydata[i])
                c = false;
        }

        System.out.println(c);

        Assert.assertTrue(c);

    }
}

