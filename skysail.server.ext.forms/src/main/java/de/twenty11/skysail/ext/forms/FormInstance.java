/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.twenty11.skysail.ext.forms.FormInstance#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.FormInstance#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.ext.forms.FormsPackage#getFormInstance()
 * @model
 * @generated
 */
public interface FormInstance extends EObject {
	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' containment reference list.
	 * The list contents are of type {@link de.twenty11.skysail.ext.forms.Value}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' containment reference list.
	 * @see de.twenty11.skysail.ext.forms.FormsPackage#getFormInstance_EReference0()
	 * @model type="de.twenty11.skysail.ext.forms.Value" containment="true"
	 * @generated
	 */
	EList getEReference0();

	/**
	 * Returns the value of the '<em><b>Form</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form</em>' containment reference.
	 * @see #setForm(Form)
	 * @see de.twenty11.skysail.ext.forms.FormsPackage#getFormInstance_Form()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Form getForm();

	/**
	 * Sets the value of the '{@link de.twenty11.skysail.ext.forms.FormInstance#getForm <em>Form</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form</em>' containment reference.
	 * @see #getForm()
	 * @generated
	 */
	void setForm(Form value);

} // FormInstance
