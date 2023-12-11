package org.gb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


/**
 * Class SerializableManager is used to save objects to file and to load objects from file
 */

public class SerializableManager {

    static final String NAME_END = "_" + UUID.randomUUID();
    static final String DIR = "hw3/src/main/java/org/gb/files/";

    /**
     *
     * @param obj - object of serialized Class to be saved to file.
     */
    public static void saveObject(Object obj) {

        String fileName = obj.getClass().getName() + NAME_END;
        Path path = Path.of(DIR, fileName);

        try (
                OutputStream outputStream = Files.newOutputStream(path);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            try {
                objectOutputStream.writeObject(obj);
                System.out.println("Объект успешно сохранен в файл " + fileName);
            }catch (NotSerializableException e){
                System.err.println("The class " +obj.getClass().getName() + " is not Serializable");
                Files.deleteIfExists(path);
                objectOutputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param fileName - String name of file, to be loaded to object
     * @return - saved object
     */
    public static Object loadObject(String fileName) {

        Object obj;

        //Part 1

        try (
                FileInputStream input = new FileInputStream(DIR + fileName);
                ObjectInputStream ois = new ObjectInputStream(input)) {
            obj = ois.readObject();

            System.out.println("Объект успешно загружен из файла " + fileName);
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Ошибка при загрузке объекта: " + e.getMessage());
            return null;
        }

        // Part 2
        Path path = Path.of(DIR, fileName);
        try {
            if (Files.deleteIfExists(path)){
                System.out.println("Файл " + fileName + " успешно удален");
            }else {
                System.out.println("Ошибка при удалении файла " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
}
