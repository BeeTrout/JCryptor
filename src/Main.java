import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static JTextField pathF;
    public static JTextField keyF;
    public static ButtonGroup modeGroup;
    public static JRadioButton encrypt;
    public static JRadioButton decrypt;

    public static void main(String[] args) {

        gui();

    }

    public static void start() throws IOException {

        String pathStr = pathF.getText();
        JCryptor.setKey(Long.parseLong(keyF.getText()));

        byte[] byteArr = JCryptor.fileToByteArr(pathStr);

        System.out.println(Arrays.toString(byteArr));

        if (encrypt.isSelected()) {
            JCryptor.encrypt(byteArr);
        } else {
            JCryptor.decrypt(byteArr);
        }

        System.out.println(Arrays.toString(byteArr));

        JCryptor.byteArrToFile(pathStr, byteArr);

    }

    public static void gui() {

        JFrame frame = new JFrame();
        frame.setSize(450,350);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("JCryptor");

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel title = new JLabel("Select mode:");
        title.setBounds(30, 10, 200, 30);
        panel.add(title);

        encrypt = new JRadioButton("Encrypt", true);
        decrypt = new JRadioButton("Decrypt");
        encrypt.setBounds(30, 40, 200, 30);
        decrypt.setBounds(30, 70, 200, 30);
        panel.add(encrypt);
        panel.add(decrypt);

        modeGroup = new ButtonGroup();
        modeGroup.add(encrypt);
        modeGroup.add(decrypt);

        JLabel keyL = new JLabel("Enter encryption key:");
        keyL.setBounds(30, 120, 150, 30);
        panel.add(keyL);

        keyF = new JTextField();
        keyF.setBounds(180, 120, 100, 30);
        panel.add(keyF);

        JButton keyB = new JButton("Select random");
        keyB.setBounds(300, 120, 100, 30);
        keyB.setMargin(new Insets(5, 0, 5, 0));
        keyB.addActionListener(e -> {
            long n;
            do {
                n = ThreadLocalRandom.current().nextLong();
                keyF.setText("" + n);
            } while (n == 0);
        });
        panel.add(keyB);

        JLabel pathL = new JLabel("Enter file path:");
        pathL.setBounds(30, 160, 150, 30);
        panel.add(pathL);

        pathF = new JTextField();
        pathF.setBounds(180, 160, 222, 30);
        panel.add(pathF);

        JButton start = new JButton("Start");
        start.setBounds(170, 240, 100, 30);
        start.addActionListener(e -> {
            try {
                Main.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        panel.add(start);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
