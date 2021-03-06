/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.twenty11.skysail.ext.forms.FormsPackage
 * @generated
 */
public interface FormsFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	FormsFactory eINSTANCE = de.twenty11.skysail.ext.forms.impl.FormsFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Field</em>'.
     * @generated
     */
	Field createField();

	/**
     * Returns a new object of class '<em>Form</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Form</em>'.
     * @generated
     */
	Form createForm();

	/**
     * Returns a new object of class '<em>Value</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value</em>'.
     * @generated
     */
	Value createValue();

	/**
     * Returns a new object of class '<em>Form Instance</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Form Instance</em>'.
     * @generated
     */
	FormInstance createFormInstance();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	FormsPackage getFormsPackage();

} //FormsFactory
