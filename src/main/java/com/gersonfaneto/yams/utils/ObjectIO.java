package com.gersonfaneto.yams.utils;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <code>ObjectIO</code> contains functions for dealing with: reading and writing data off/from
 * <code>Serializable</code> Objects.
 *
 * @author gersonfaneto
 * @version 1.0.0
 * @see java.io.Serializable
 */
public abstract class ObjectIO {

  /**
   * Saves a received <code>Object</code> data in to a file.
   *
   * @param targetObject The <code>Object</code> to be saved.
   * @param savePath     The path of the new file.
   * @throws IOException If any file I/O error happens in the process.
   */
  public static void savePerson(Object targetObject, String savePath) throws IOException {
    FileOutputStream fileOut = new FileOutputStream(savePath);
    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOut);

    objectOutput.writeObject(targetObject);

    fileOut.close();
    objectOutput.close();
  }

  /**
   * Reads the data from the received file path and attempts to reconstruct an <code>Object</code>
   * from it.
   *
   * @param savePath The path of the file to be read.
   * @return The retrieved <code>Object</code>.
   * @throws IOException            If any file I/O error happens in the process.
   * @throws ClassNotFoundException If the <code>savePath</code> data didn't match any of the known
   *                                <code>Serializable</code> Objects.
   */
  public static Object loadPerson(String savePath) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(savePath);
    ObjectInputStream objectIn = new ObjectInputStream(fileIn);

    Object readObject = objectIn.readObject();

    fileIn.close();
    objectIn.close();

    return readObject;
  }
}
