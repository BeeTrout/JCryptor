public class JCryptor {

    public static void encrypt(byte[] arr) {

        int l = arr.length;

        for (int i = 0; i < l; i++) {

            if (Main.key % ((i == 0) ? i + 1 : i) == 0) {
                arr[i] += Main.key / l + i + Math.sqrt(Main.key);
            } else {
                arr[i] += Main.key / l + i;
            }

        }

    }

    public static void decrypt(byte[] arr) {

        int l = arr.length;

        for (int i = 0; i < l; i++) {

            if (Main.key % ((i == 0) ? i + 1 : i) == 0) {
                arr[i] -= Main.key / l + i + Math.sqrt(Main.key);
            } else {
                arr[i] -= Main.key / l + i;
            }

        }

    }

}
