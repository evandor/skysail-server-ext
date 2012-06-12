/**
 */
package de.twenty11.skysail.server.ext.notes.impl;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.twenty11.skysail.server.ext.notes.Folder;
import de.twenty11.skysail.server.ext.notes.Note;
import de.twenty11.skysail.server.ext.notes.NotesPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.impl.FolderImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.impl.FolderImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.impl.FolderImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.impl.FolderImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.integrationtests.impl.FolderImpl#getSubfolders <em>Subfolders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@Entity(name = "skysail_server_ext_notes_Folder")
public class FolderImpl extends EObjectImpl implements Folder {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final int ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    @Id
    @GeneratedValue
    protected int id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected static final String PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected String path = PATH_EDEFAULT;

    /**
     * The cached value of the '{@link #getNotes() <em>Notes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNotes()
     * @generated
     * @ordered
     */
    protected EList<Note> notes;

    /**
     * The cached value of the '{@link #getSubfolders() <em>Subfolders</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubfolders()
     * @generated
     * @ordered
     */
    protected EList<Folder> subfolders;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FolderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return NotesPackage.Literals.FOLDER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(int newId) {
        int oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.FOLDER__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.FOLDER__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPath() {
        return path;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPath(String newPath) {
        String oldPath = path;
        path = newPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.FOLDER__PATH, oldPath, path));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Note> getNotes() {
        if (notes == null) {
            notes = new EObjectContainmentEList<Note>(Note.class, this, NotesPackage.FOLDER__NOTES);
        }
        return notes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Folder> getSubfolders() {
        if (subfolders == null) {
            subfolders = new EObjectContainmentEList<Folder>(Folder.class, this, NotesPackage.FOLDER__SUBFOLDERS);
        }
        return subfolders;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case NotesPackage.FOLDER__NOTES:
                return ((InternalEList<?>)getNotes()).basicRemove(otherEnd, msgs);
            case NotesPackage.FOLDER__SUBFOLDERS:
                return ((InternalEList<?>)getSubfolders()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case NotesPackage.FOLDER__ID:
                return getId();
            case NotesPackage.FOLDER__NAME:
                return getName();
            case NotesPackage.FOLDER__PATH:
                return getPath();
            case NotesPackage.FOLDER__NOTES:
                return getNotes();
            case NotesPackage.FOLDER__SUBFOLDERS:
                return getSubfolders();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case NotesPackage.FOLDER__ID:
                setId((Integer)newValue);
                return;
            case NotesPackage.FOLDER__NAME:
                setName((String)newValue);
                return;
            case NotesPackage.FOLDER__PATH:
                setPath((String)newValue);
                return;
            case NotesPackage.FOLDER__NOTES:
                getNotes().clear();
                getNotes().addAll((Collection<? extends Note>)newValue);
                return;
            case NotesPackage.FOLDER__SUBFOLDERS:
                getSubfolders().clear();
                getSubfolders().addAll((Collection<? extends Folder>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case NotesPackage.FOLDER__ID:
                setId(ID_EDEFAULT);
                return;
            case NotesPackage.FOLDER__NAME:
                setName(NAME_EDEFAULT);
                return;
            case NotesPackage.FOLDER__PATH:
                setPath(PATH_EDEFAULT);
                return;
            case NotesPackage.FOLDER__NOTES:
                getNotes().clear();
                return;
            case NotesPackage.FOLDER__SUBFOLDERS:
                getSubfolders().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case NotesPackage.FOLDER__ID:
                return id != ID_EDEFAULT;
            case NotesPackage.FOLDER__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case NotesPackage.FOLDER__PATH:
                return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
            case NotesPackage.FOLDER__NOTES:
                return notes != null && !notes.isEmpty();
            case NotesPackage.FOLDER__SUBFOLDERS:
                return subfolders != null && !subfolders.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", path: ");
        result.append(path);
        result.append(')');
        return result.toString();
    }

} //FolderImpl
