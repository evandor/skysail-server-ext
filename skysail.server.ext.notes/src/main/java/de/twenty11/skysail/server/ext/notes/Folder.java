/**
 */
package de.twenty11.skysail.server.ext.notes;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getId <em>Id</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getName <em>Name</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getPath <em>Path</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getNotes <em>Notes</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getSubfolders <em>Subfolders</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder()
 * @model
 * @generated
 */
public interface Folder extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(int)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder_Id()
     * @model
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Path</em>' attribute.
     * @see #setPath(String)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder_Path()
     * @model
     * @generated
     */
    String getPath();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder#getPath <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path</em>' attribute.
     * @see #getPath()
     * @generated
     */
    void setPath(String value);

    /**
     * Returns the value of the '<em><b>Notes</b></em>' containment reference list.
     * The list contents are of type {@link de.twenty11.skysail.server.ext.notes.integrationtests.Note}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Notes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Notes</em>' containment reference list.
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder_Notes()
     * @model containment="true"
     * @generated
     */
    EList<Note> getNotes();

    /**
     * Returns the value of the '<em><b>Subfolders</b></em>' containment reference list.
     * The list contents are of type {@link de.twenty11.skysail.server.ext.notes.integrationtests.Folder}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Subfolders</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Subfolders</em>' containment reference list.
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getFolder_Subfolders()
     * @model containment="true"
     * @generated
     */
    EList<Folder> getSubfolders();

} // Folder
