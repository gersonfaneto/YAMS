package com.gersonfaneto.yams.utils;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class ObjectIO {
    public static void savePerson(Object targetPerson, String savePath) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(savePath);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOut);

        objectOutput.writeObject(targetPerson);

        fileOut.close();
        objectOutput.close();
    }

    public static Object loadPerson(String savePath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(savePath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        Object readObject = objectIn.readObject();

        fileIn.close();
        objectIn.close();

        return readObject;
    }
}
