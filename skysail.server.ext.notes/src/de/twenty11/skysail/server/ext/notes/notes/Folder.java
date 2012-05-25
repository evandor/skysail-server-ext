/**
 */
package de.twenty11.skysail.server.ext.notes.notes;

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
 *   <li>{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getName <em>Name</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getPath <em>Path</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getNotes <em>Notes</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.server.ext.notes.notes.NotesPackage#getFolder()
 * @model
 * @generated
 */
public interface Folder extends EObject {
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
     * @see de.twenty11.skysail.server.ext.notes.notes.NotesPackage#getFolder_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getName <em>Name</em>}' attribute.
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
     * @see de.twenty11.skysail.server.ext.notes.notes.NotesPackage#getFolder_Path()
     * @model
     * @generated
     */
    String getPath();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getPath <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path</em>' attribute.
     * @see #getPath()
     * @generated
     */
    void setPath(String value);

    /**
     * Returns the value of the '<em><b>Notes</b></em>' containment reference list.
     * The list contents are of type {@link de.twenty11.skysail.server.ext.notes.notes.Note}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Notes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Notes</em>' containment reference list.
     * @see de.twenty11.skysail.server.ext.notes.notes.NotesPackage#getFolder_Notes()
     * @model containment="true"
     * @generated
     */
    EList<Note> getNotes();

    /**
     * Returns the value of the '<em><b>Children</b></em>' reference list.
     * The list contents are of type {@link de.twenty11.skysail.server.ext.notes.notes.Folder}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' reference list.
     * @see de.twenty11.skysail.server.ext.notes.notes.NotesPackage#getFolder_Children()
     * @model
     * @generated
     */
    EList<Folder> getChildren();

} // Folder
