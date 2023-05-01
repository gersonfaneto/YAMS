package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.entities.user.UserCRUD;
import com.gersonfaneto.yams.dao.entities.user.UserMemoryDAO;
import com.gersonfaneto.yams.exceptions.users.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.users.PermissionDeniedException;
import com.gersonfaneto.yams.exceptions.users.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserTypeNotFoundException;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

/**
 * Controller containing all the operations related with the <code>User</code> and model and its
 * subclasses on the System, such as creating, updating and retrieving information about them. Most
 * of the operations relly on the CRUD operations accessed through the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see UserMemoryDAO
 * @see UserCRUD
 * @see User
 */
public abstract class AuthController {

  /**
   * Registers a new <code>User</code> to the System. <strong>Note: Operation can only be performed
   * by the <code>Administrator</code> of the System.</strong>
   *
   * @param loggedUser   The <code>User</code> attempting the operation.
   * @param userEmail    The email chosen by the <code>User</code>.
   * @param userPassword The password chosen by the <code>User</code>.
   * @param userName     The name of the <code>User</code>.
   * @param userType     The type of the <code>User</code>.
   * @return The registered <code>User</code>.
   * @throws UserAlreadyRegisteredException If the <code>userEmail</code> is already taken by other
   *                                        <code>User</code>.
   * @throws UserTypeNotFoundException      If the <code>userType</code> didn't match any of the
   *                                        ones declared under the <code>UserType</code>
   *                                        enumeration.
   * @throws PermissionDeniedException      If the <code>loggedUser</code> isn't the
   *                                        <code>Administrator
   *                                        </code> of the System.
   * @see User
   * @see UserType
   */
  public static User registerUser(
      User loggedUser,
      String userEmail,
      String userPassword,
      String userName,
      String userType
  ) throws UserAlreadyRegisteredException, UserTypeNotFoundException, PermissionDeniedException {
    if (loggedUser.getUserType() != UserType.Administrator) {
      throw new PermissionDeniedException("You don't have the needed privileges");
    }

    if (DAO.fromUsers().findByEmail(userEmail) != null) {
      throw new UserAlreadyRegisteredException("User already registered!");
    }
    if (UserType.findByName(userType) == null) {
      throw new UserTypeNotFoundException("User type not found!");
    }

    Technician newUser = new Technician(
        userEmail,
        userPassword,
        UserType.findByName(userType), userName
    );
    DAO.fromUsers().createOne(newUser);

    return newUser;
  }

  /**
   * Gives access to a <code>User</code> to the System by validating its credentials.
   *
   * @param userEmail    The email of the <code>User</code>.
   * @param userPassword The password of the <code>User</code>.
   * @return
   * @throws UserNotFoundException    If the <code>userEmail</code> didn't match any of the ones
   *                                  from the registered <code>User</code>s.
   * @throws InvalidPasswordException If the <code>userPassword</code> didn't match the one of the
   *                                  found <code>User</code>.
   * @see User
   * @see UserType
   */
  public static User loginUser(
      String userEmail,
      String userPassword
  ) throws UserNotFoundException, InvalidPasswordException {
    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    if (!userPassword.equals(foundUser.getUserPassword())) {
      throw new InvalidPasswordException("Password incorrect!");
    }

    return foundUser;
  }

  /**
   * Update the access credentials of a given <code>User</code>.
   *
   * @param userEmail    The new email chosen by the <code>User</code>.
   * @param userPassword The new password chosen by the <code>User</code>.
   * @return The updated <code>User</code>.
   * @throws UserNotFoundException If the <code>userEmail</code> didn't match any of the ones from
   *                               the registered <code>User</code>s.
   * @see User
   */
  public static User updateInfo(
      String userEmail,
      String userPassword
  ) throws UserNotFoundException {
    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    foundUser.setUserEmail(userEmail);
    foundUser.setUserPassword(userPassword);

    DAO.fromUsers().updateInformation(foundUser);

    return foundUser;
  }

  /**
   * Unregisters a given <code>User</code> from the System. <strong>Note: Operation can only be
   * performed by the <code>Administrator</code> of the System</strong>
   *
   * @param loggedUser The <code>User</code> attempting the operation.
   * @param userEmail  The email of the targeted <code>User</code>.
   * @return The removed <code>User</code>.
   * @throws UserNotFoundException     If the <code>userEmail</code> didn't match any of the ones
   *                                   from the registered <code>User</code>s.
   * @throws PermissionDeniedException If the <code>loggedUser</code> isn't the <code>Administrator
   *                                   </code> of the System.
   * @see User
   * @see UserType
   */
  public static User unregisterUser(
      User loggedUser,
      String userEmail
  ) throws UserNotFoundException, PermissionDeniedException {
    if (loggedUser.getUserType() != UserType.Administrator) {
      throw new PermissionDeniedException("You don't have the needed privileges");
    }

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    DAO.fromUsers().deleteByID(foundUser.getUserID());

    return foundUser;
  }
}
