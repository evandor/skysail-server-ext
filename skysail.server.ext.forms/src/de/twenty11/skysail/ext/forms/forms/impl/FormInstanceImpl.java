/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms.forms.impl;

import de.twenty11.skysail.ext.forms.forms.Form;
import de.twenty11.skysail.ext.forms.forms.FormInstance;
import de.twenty11.skysail.ext.forms.forms.FormsPackage;
import de.twenty11.skysail.ext.forms.forms.Value;

import java.util.Collection;

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
 * An implementation of the model object '<em><b>Form Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.twenty11.skysail.ext.forms.forms.impl.FormInstanceImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.forms.impl.FormInstanceImpl#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormInstanceImpl extends EObjectImpl implements FormInstance {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected EList eReference0;

	/**
	 * The cached value of the '{@link #getForm() <em>Form</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForm()
	 * @generated
	 * @ordered
	 */
	protected Form form;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FormsPackage.Literals.FORM_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEReference0() {
		if (eReference0 == null) {
			eReference0 = new EObjectContainmentEList(Value.class, this, FormsPackage.FORM_INSTANCE__EREFERENCE0);
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Form getForm() {
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetForm(Form newForm, NotificationChain msgs) {
		Form oldForm = form;
		form = newForm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormsPackage.FORM_INSTANCE__FORM, oldForm, newForm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForm(Form newForm) {
		if (newForm != form) {
			NotificationChain msgs = null;
			if (form != null)
				msgs = ((InternalEObject)form).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormsPackage.FORM_INSTANCE__FORM, null, msgs);
			if (newForm != null)
				msgs = ((InternalEObject)newForm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormsPackage.FORM_INSTANCE__FORM, null, msgs);
			msgs = basicSetForm(newForm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormsPackage.FORM_INSTANCE__FORM, newForm, newForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormsPackage.FORM_INSTANCE__EREFERENCE0:
				return ((InternalEList)getEReference0()).basicRemove(otherEnd, msgs);
			case FormsPackage.FORM_INSTANCE__FORM:
				return basicSetForm(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormsPackage.FORM_INSTANCE__EREFERENCE0:
				return getEReference0();
			case FormsPackage.FORM_INSTANCE__FORM:
				return getForm();
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
			case FormsPackage.FORM_INSTANCE__EREFERENCE0:
				getEReference0().clear();
				getEReference0().addAll((Collection)newValue);
				return;
			case FormsPackage.FORM_INSTANCE__FORM:
				setForm((Form)newValue);
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
			case FormsPackage.FORM_INSTANCE__EREFERENCE0:
				getEReference0().clear();
				return;
			case FormsPackage.FORM_INSTANCE__FORM:
				setForm((Form)null);
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
			case FormsPackage.FORM_INSTANCE__EREFERENCE0:
				return eReference0 != null && !eReference0.isEmpty();
			case FormsPackage.FORM_INSTANCE__FORM:
				return form != null;
		}
		return super.eIsSet(featureID);
	}

} //FormInstanceImpl
