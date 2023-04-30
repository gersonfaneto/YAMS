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
   */
  public static boolean savePerson(Object targetObject, String savePath) {
    try {
      FileOutputStream fileOut = new FileOutputStream(savePath);
      ObjectOutputStream objectOutput = new ObjectOutputStream(fileOut);

      objectOutput.writeObject(targetObject);

      fileOut.close();
      objectOutput.close();
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Reads the data from the received file path and attempts to reconstruct an <code>Object</code>
   * from it.
   *
   * @param savePath The path of the file to be read.
   * @return The retrieved <code>Object</code>.
   */
  public static Object loadPerson(String savePath) {
    try {
      FileInputStream fileIn = new FileInputStream(savePath);
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);

      Object readObject = objectIn.readObject();

      fileIn.close();
      objectIn.close();

      return readObject;
    } catch (Exception e) {
      return null;
    }
  }
}
