import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JCryptor {

    public static long key;

    public static long getKey() {
        return key;
    }

    public static void setKey(long key) {
        JCryptor.key = key;
    }

    public static void encrypt(byte[] arr) {

        int l = arr.length;
        int q = (int) Math.sqrt(key);

        for (int i = 0; i < l; i++) {

            if (key % ((i == 0) ? i + 1 : i) == 0) {
                arr[i] += key / l + i + q;
            } else {
                arr[i] += key / l + i;
            }

        }

    }

    public static void decrypt(byte[] arr) {

        int l = arr.length;
        int q = (int) Math.sqrt(key);

        for (int i = 0; i < l; i++) {

            if (key % ((i == 0) ? 1 : i) == 0) {
                arr[i] -= key / l + i + q;
            } else {
                arr[i] -= key / l + i;
            }

        }

    }

    public static byte[] fileToByteArr(String s) throws IOException {

        Path p = Paths.get(s);
        return Files.readAllBytes(p);

    }

    public static void byteArrToFile(String path, byte[] bytes) throws IOException {

        FileOutputStream s = new FileOutputStream(path);
        s.write(bytes);

    }

}
