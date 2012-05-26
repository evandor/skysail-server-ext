/**
 */
package de.twenty11.skysail.server.ext.notes.impl;

import de.twenty11.skysail.server.ext.notes.Note;
import de.twenty11.skysail.server.ext.notes.NotesPackage;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Note</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.impl.NoteImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.impl.NoteImpl#getChanged <em>Changed</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.impl.NoteImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link de.twenty11.skysail.server.ext.notes.impl.NoteImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NoteImpl extends EObjectImpl implements Note {
    /**
     * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreated()
     * @generated
     * @ordered
     */
    protected static final Date CREATED_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreated()
     * @generated
     * @ordered
     */
    protected Date created = CREATED_EDEFAULT;

    /**
     * The default value of the '{@link #getChanged() <em>Changed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChanged()
     * @generated
     * @ordered
     */
    protected static final Date CHANGED_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getChanged() <em>Changed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChanged()
     * @generated
     * @ordered
     */
    protected Date changed = CHANGED_EDEFAULT;

    /**
     * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected String title = TITLE_EDEFAULT;

    /**
     * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected static final String CONTENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected String content = CONTENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NoteImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return NotesPackage.Literals.NOTE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCreated(Date newCreated) {
        Date oldCreated = created;
        created = newCreated;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.NOTE__CREATED, oldCreated, created));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getChanged() {
        return changed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChanged(Date newChanged) {
        Date oldChanged = changed;
        changed = newChanged;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.NOTE__CHANGED, oldChanged, changed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTitle(String newTitle) {
        String oldTitle = title;
        title = newTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.NOTE__TITLE, oldTitle, title));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContent() {
        return content;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContent(String newContent) {
        String oldContent = content;
        content = newContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NotesPackage.NOTE__CONTENT, oldContent, content));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case NotesPackage.NOTE__CREATED:
                return getCreated();
            case NotesPackage.NOTE__CHANGED:
                return getChanged();
            case NotesPackage.NOTE__TITLE:
                return getTitle();
            case NotesPackage.NOTE__CONTENT:
                return getContent();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case NotesPackage.NOTE__CREATED:
                setCreated((Date)newValue);
                return;
            case NotesPackage.NOTE__CHANGED:
                setChanged((Date)newValue);
                return;
            case NotesPackage.NOTE__TITLE:
                setTitle((String)newValue);
                return;
            case NotesPackage.NOTE__CONTENT:
                setContent((String)newValue);
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
            case NotesPackage.NOTE__CREATED:
                setCreated(CREATED_EDEFAULT);
                return;
            case NotesPackage.NOTE__CHANGED:
                setChanged(CHANGED_EDEFAULT);
                return;
            case NotesPackage.NOTE__TITLE:
                setTitle(TITLE_EDEFAULT);
                return;
            case NotesPackage.NOTE__CONTENT:
                setContent(CONTENT_EDEFAULT);
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
            case NotesPackage.NOTE__CREATED:
                return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
            case NotesPackage.NOTE__CHANGED:
                return CHANGED_EDEFAULT == null ? changed != null : !CHANGED_EDEFAULT.equals(changed);
            case NotesPackage.NOTE__TITLE:
                return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
            case NotesPackage.NOTE__CONTENT:
                return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
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
        result.append(" (created: ");
        result.append(created);
        result.append(", changed: ");
        result.append(changed);
        result.append(", title: ");
        result.append(title);
        result.append(", content: ");
        result.append(content);
        result.append(')');
        return result.toString();
    }

} //NoteImpl
