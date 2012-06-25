/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.twenty11.skysail.ext.forms.Field#getName <em>Name</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.Field#isRequired <em>Required</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.Field#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.ext.forms.FormsPackage#getField()
 * @model
 * @generated
 */
public interface Field extends EObject {
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
     * @see de.twenty11.skysail.ext.forms.FormsPackage#getField_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link de.twenty11.skysail.ext.forms.Field#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Required</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Required</em>' attribute.
     * @see #setRequired(boolean)
     * @see de.twenty11.skysail.ext.forms.FormsPackage#getField_Required()
     * @model
     * @generated
     */
	boolean isRequired();

	/**
     * Sets the value of the '{@link de.twenty11.skysail.ext.forms.Field#isRequired <em>Required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Required</em>' attribute.
     * @see #isRequired()
     * @generated
     */
	void setRequired(boolean value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link de.twenty11.skysail.ext.forms.Type}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see de.twenty11.skysail.ext.forms.Type
     * @see #setType(Type)
     * @see de.twenty11.skysail.ext.forms.FormsPackage#getField_Type()
     * @model
     * @generated
     */
	Type getType();

	/**
     * Sets the value of the '{@link de.twenty11.skysail.ext.forms.Field#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see de.twenty11.skysail.ext.forms.Type
     * @see #getType()
     * @generated
     */
	void setType(Type value);

} // Field
