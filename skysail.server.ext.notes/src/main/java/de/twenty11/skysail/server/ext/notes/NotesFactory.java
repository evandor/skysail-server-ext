/**
 */
package de.twenty11.skysail.server.ext.notes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.twenty11.skysail.server.ext.notes.NotesPackage
 * @generated
 */
public interface NotesFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NotesFactory eINSTANCE = de.twenty11.skysail.server.ext.notes.impl.NotesFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Note</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Note</em>'.
     * @generated
     */
    Note createNote();

    /**
     * Returns a new object of class '<em>Folder</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Folder</em>'.
     * @generated
     */
    Folder createFolder();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    NotesPackage getNotesPackage();

} //NotesFactory
