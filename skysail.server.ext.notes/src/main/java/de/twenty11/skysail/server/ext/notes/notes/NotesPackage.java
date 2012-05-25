/**
 */
package de.twenty11.skysail.server.ext.notes.notes;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.twenty11.skysail.server.ext.notes.notes.NotesFactory
 * @model kind="package"
 * @generated
 */
public interface NotesPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "notes";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://notes/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "notes";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NotesPackage eINSTANCE = de.twenty11.skysail.server.ext.notes.notes.impl.NotesPackageImpl.init();

    /**
     * The meta object id for the '{@link de.twenty11.skysail.server.ext.notes.notes.impl.NoteImpl <em>Note</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.twenty11.skysail.server.ext.notes.notes.impl.NoteImpl
     * @see de.twenty11.skysail.server.ext.notes.notes.impl.NotesPackageImpl#getNote()
     * @generated
     */
    int NOTE = 0;

    /**
     * The feature id for the '<em><b>Created</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE__CREATED = 0;

    /**
     * The feature id for the '<em><b>Changed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE__CHANGED = 1;

    /**
     * The feature id for the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE__TITLE = 2;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE__CONTENT = 3;

    /**
     * The number of structural features of the '<em>Note</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link de.twenty11.skysail.server.ext.notes.notes.impl.FolderImpl <em>Folder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.twenty11.skysail.server.ext.notes.notes.impl.FolderImpl
     * @see de.twenty11.skysail.server.ext.notes.notes.impl.NotesPackageImpl#getFolder()
     * @generated
     */
    int FOLDER = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER__NAME = 0;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER__PATH = 1;

    /**
     * The feature id for the '<em><b>Notes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER__NOTES = 2;

    /**
     * The feature id for the '<em><b>Subfolders</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER__SUBFOLDERS = 3;

    /**
     * The number of structural features of the '<em>Folder</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_FEATURE_COUNT = 4;


    /**
     * Returns the meta object for class '{@link de.twenty11.skysail.server.ext.notes.notes.Note <em>Note</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Note</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Note#getCreated <em>Created</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Created</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Note#getCreated()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Created();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Note#getChanged <em>Changed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Changed</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Note#getChanged()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Changed();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Note#getTitle <em>Title</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Title</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Note#getTitle()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Title();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Note#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Note#getContent()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Content();

    /**
     * Returns the meta object for class '{@link de.twenty11.skysail.server.ext.notes.notes.Folder <em>Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Folder</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Folder
     * @generated
     */
    EClass getFolder();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Folder#getName()
     * @see #getFolder()
     * @generated
     */
    EAttribute getFolder_Name();

    /**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getPath <em>Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Folder#getPath()
     * @see #getFolder()
     * @generated
     */
    EAttribute getFolder_Path();

    /**
     * Returns the meta object for the containment reference list '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getNotes <em>Notes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Notes</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Folder#getNotes()
     * @see #getFolder()
     * @generated
     */
    EReference getFolder_Notes();

    /**
     * Returns the meta object for the containment reference list '{@link de.twenty11.skysail.server.ext.notes.notes.Folder#getSubfolders <em>Subfolders</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Subfolders</em>'.
     * @see de.twenty11.skysail.server.ext.notes.notes.Folder#getSubfolders()
     * @see #getFolder()
     * @generated
     */
    EReference getFolder_Subfolders();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    NotesFactory getNotesFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.twenty11.skysail.server.ext.notes.notes.impl.NoteImpl <em>Note</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.twenty11.skysail.server.ext.notes.notes.impl.NoteImpl
         * @see de.twenty11.skysail.server.ext.notes.notes.impl.NotesPackageImpl#getNote()
         * @generated
         */
        EClass NOTE = eINSTANCE.getNote();

        /**
         * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTE__CREATED = eINSTANCE.getNote_Created();

        /**
         * The meta object literal for the '<em><b>Changed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTE__CHANGED = eINSTANCE.getNote_Changed();

        /**
         * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTE__TITLE = eINSTANCE.getNote_Title();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTE__CONTENT = eINSTANCE.getNote_Content();

        /**
         * The meta object literal for the '{@link de.twenty11.skysail.server.ext.notes.notes.impl.FolderImpl <em>Folder</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.twenty11.skysail.server.ext.notes.notes.impl.FolderImpl
         * @see de.twenty11.skysail.server.ext.notes.notes.impl.NotesPackageImpl#getFolder()
         * @generated
         */
        EClass FOLDER = eINSTANCE.getFolder();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FOLDER__NAME = eINSTANCE.getFolder_Name();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FOLDER__PATH = eINSTANCE.getFolder_Path();

        /**
         * The meta object literal for the '<em><b>Notes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FOLDER__NOTES = eINSTANCE.getFolder_Notes();

        /**
         * The meta object literal for the '<em><b>Subfolders</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FOLDER__SUBFOLDERS = eINSTANCE.getFolder_Subfolders();

    }

} //NotesPackage
