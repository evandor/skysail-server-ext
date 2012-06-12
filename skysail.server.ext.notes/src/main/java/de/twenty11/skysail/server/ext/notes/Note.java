/**
 */
package de.twenty11.skysail.server.ext.notes;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Note</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getId <em>Id</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getCreated <em>Created</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getChanged <em>Changed</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getTitle <em>Title</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote()
 * @model
 * @generated
 */
public interface Note extends EObject {
    /**
     * Returns the value of the '<em><b>Created</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Created</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Created</em>' attribute.
     * @see #setCreated(Date)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote_Created()
     * @model
     * @generated
     */
    Date getCreated();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getCreated <em>Created</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Created</em>' attribute.
     * @see #getCreated()
     * @generated
     */
    void setCreated(Date value);

    /**
     * Returns the value of the '<em><b>Changed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed</em>' attribute.
     * @see #setChanged(Date)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote_Changed()
     * @model
     * @generated
     */
    Date getChanged();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getChanged <em>Changed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed</em>' attribute.
     * @see #getChanged()
     * @generated
     */
    void setChanged(Date value);

    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote_Title()
     * @model
     * @generated
     */
    String getTitle();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getTitle <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
    void setTitle(String value);

    /**
     * Returns the value of the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content</em>' attribute.
     * @see #setContent(String)
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote_Content()
     * @model
     * @generated
     */
    String getContent();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getContent <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(String value);

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
     * @see de.twenty11.skysail.server.ext.notes.integrationtests.NotesPackage#getNote_Id()
     * @model
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link de.twenty11.skysail.server.ext.notes.integrationtests.Note#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='if (eIsProxy()) \n    return super.toString();\nStringBuffer result = new StringBuffer(super.toString());\nresult.append(title);\nreturn result.toString();\n\t\t'"
     * @generated
     */
    String toString();

} // Note
