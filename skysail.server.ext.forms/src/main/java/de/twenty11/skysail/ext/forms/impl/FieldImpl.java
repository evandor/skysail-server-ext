/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms.impl;

import de.twenty11.skysail.ext.forms.Field;
import de.twenty11.skysail.ext.forms.FormsPackage;
import de.twenty11.skysail.ext.forms.Type;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.twenty11.skysail.ext.forms.impl.FieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.impl.FieldImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.impl.FieldImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldImpl extends EObjectImpl implements Field {
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
     * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isRequired()
     * @generated
     * @ordered
     */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isRequired() <em>Required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isRequired()
     * @generated
     * @ordered
     */
	protected boolean required = REQUIRED_EDEFAULT;

	/**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected static final Type TYPE_EDEFAULT = Type.STRING_LITERAL;

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected Type type = TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FieldImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FormsPackage.Literals.FIELD;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormsPackage.FIELD__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isRequired() {
        return required;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRequired(boolean newRequired) {
        boolean oldRequired = required;
        required = newRequired;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormsPackage.FIELD__REQUIRED, oldRequired, required));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Type getType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setType(Type newType) {
        Type oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormsPackage.FIELD__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FormsPackage.FIELD__NAME:
                return getName();
            case FormsPackage.FIELD__REQUIRED:
                return isRequired() ? Boolean.TRUE : Boolean.FALSE;
            case FormsPackage.FIELD__TYPE:
                return getType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FormsPackage.FIELD__NAME:
                setName((String)newValue);
                return;
            case FormsPackage.FIELD__REQUIRED:
                setRequired(((Boolean)newValue).booleanValue());
                return;
            case FormsPackage.FIELD__TYPE:
                setType((Type)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case FormsPackage.FIELD__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FormsPackage.FIELD__REQUIRED:
                setRequired(REQUIRED_EDEFAULT);
                return;
            case FormsPackage.FIELD__TYPE:
                setType(TYPE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FormsPackage.FIELD__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FormsPackage.FIELD__REQUIRED:
                return required != REQUIRED_EDEFAULT;
            case FormsPackage.FIELD__TYPE:
                return type != TYPE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", required: ");
        result.append(required);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //FieldImpl
