import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(55, 3, 40, 300.5);
        GameProgress game2 = new GameProgress(89, 2, 35, 250);
        GameProgress game3 = new GameProgress(100, 4, 60, 500.6);

        File saveFile1 = new File("D:\\GamesJava2\\SaveGames", "save1.dat");
        newFile(saveFile1);
        File saveFile2 = new File("D:\\GamesJava2\\SaveGames", "save2.dat");
        newFile(saveFile2);
        File saveFile3 = new File("D:\\GamesJava2\\SaveGames", "save3.dat");
        newFile(saveFile3);

        saveGame(game1, "D:\\GamesJava2\\SaveGames\\save1.dat");
        saveGame(game2, "D:\\GamesJava2\\SaveGames\\save2.dat");
        saveGame(game3, "D:\\GamesJava2\\SaveGames\\save3.dat");

        String[] saveList = new String[3];
        saveList[0] = "D:\\GamesJava2\\SaveGames\\save1.dat";
        saveList[1] = "D:\\GamesJava2\\SaveGames\\save2.dat";
        saveList[2] = "D:\\GamesJava2\\SaveGames\\save3.dat";

        zipFiles("D:\\GamesJava2\\SaveGames\\save.zip", saveList);

        saveFile1.delete();
        saveFile2.delete();
        saveFile3.delete();
    }

    public static void newFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("File " + file.getName() + " created");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void saveGame(GameProgress save, String way) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void zipFiles(String wayToZip, String[] arr) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(wayToZip))) {
            for (String wayToFile : arr) {
                try (FileInputStream fis = new FileInputStream(wayToFile)) {
                    ZipEntry entry = new ZipEntry(wayToFile);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
