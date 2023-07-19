package me.qscbm.gobang;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        if (!Game.jpg.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(Game.jpg);
            fileOutputStream.write(Objects.requireNonNull(Main.class.getResource("jpg.jpg")).getFile().getBytes());
            fileOutputStream.close();
        }
        new Game();
    }
}