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
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.twenty11.skysail.ext.forms.Value#getStringValue <em>String Value</em>}</li>
 *   <li>{@link de.twenty11.skysail.ext.forms.Value#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.twenty11.skysail.ext.forms.FormsPackage#getValue()
 * @model
 * @generated
 */
public interface Value extends EObject {
	/**
     * Returns the value of the '<em><b>String Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>String Value</em>' attribute.
     * @see #setStringValue(String)
     * @see de.twenty11.skysail.ext.forms.FormsPackage#getValue_StringValue()
     * @model
     * @generated
     */
	String getStringValue();

	/**
     * Sets the value of the '{@link de.twenty11.skysail.ext.forms.Value#getStringValue <em>String Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>String Value</em>' attribute.
     * @see #getStringValue()
     * @generated
     */
	void setStringValue(String value);

	/**
     * Returns the value of the '<em><b>Field</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Field</em>' reference.
     * @see #setField(Field)
     * @see de.twenty11.skysail.ext.forms.FormsPackage#getValue_Field()
     * @model required="true"
     * @generated
     */
	Field getField();

	/**
     * Sets the value of the '{@link de.twenty11.skysail.ext.forms.Value#getField <em>Field</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Field</em>' reference.
     * @see #getField()
     * @generated
     */
	void setField(Field value);

} // Value
