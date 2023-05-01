package com.gersonfaneto.yams.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * <code>ObjectIO</code> contains functions for dealing with: reading and writing data off/from
 * <code>Serializable</code> Objects.
 *
 * @author gersonfaneto
 * @version 1.0.0
 * @see java.io.Serializable
 */
public class ObjectIO<T> {

  private String savePath;

  /**
   * Constructs a new <code>ObjectIO</code>.
   *
   * @param savePath The file to save the objects in.
   */
  public ObjectIO(String savePath) {
    this.savePath = savePath;
  }

  /**
   * Saves all the received <code>Object</code>s data in to a file.
   *
   * @param objectsToSave The <code>List</code> of <code>Object</code>s to be saved.
   */
  public boolean saveObjects(List<T> objectsToSave) {
    try {
      FileOutputStream fileOut = new FileOutputStream(savePath);
      ObjectOutputStream objectOutput = new ObjectOutputStream(fileOut);

      for (T currentObject : objectsToSave) {
        objectOutput.writeObject(currentObject);
      }

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
   * @return The <code>List</code> of all retrieved <code>Object</code>s.
   */
  public List<T> loadObjects() {
    try {
      List<T> retrievedObjects = new LinkedList<>();

      FileInputStream fileIn = new FileInputStream(savePath);
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);

      while (fileIn.available() > 0) {
        T readObject = (T) objectIn.readObject();
        retrievedObjects.add(readObject);
      }

      fileIn.close();
      objectIn.close();

      return retrievedObjects;
    } catch (Exception e) {
      return null;
    }
  }

  public String getSavePath() {
    return savePath;
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }
}
