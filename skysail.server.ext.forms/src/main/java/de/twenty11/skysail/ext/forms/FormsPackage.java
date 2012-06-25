/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.twenty11.skysail.ext.forms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.twenty11.skysail.ext.forms.FormsFactory
 * @model kind="package"
 * @generated
 */
public interface FormsPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "forms";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://forms/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "forms";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	FormsPackage eINSTANCE = de.twenty11.skysail.ext.forms.impl.FormsPackageImpl.init();

	/**
     * The meta object id for the '{@link de.twenty11.skysail.ext.forms.impl.FieldImpl <em>Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.twenty11.skysail.ext.forms.impl.FieldImpl
     * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getField()
     * @generated
     */
	int FIELD = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FIELD__NAME = 0;

	/**
     * The feature id for the '<em><b>Required</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FIELD__REQUIRED = 1;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FIELD__TYPE = 2;

	/**
     * The number of structural features of the '<em>Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FIELD_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link de.twenty11.skysail.ext.forms.impl.FormImpl <em>Form</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.twenty11.skysail.ext.forms.impl.FormImpl
     * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getForm()
     * @generated
     */
	int FORM = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__NAME = 0;

	/**
     * The feature id for the '<em><b>Fields</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__FIELDS = 1;

	/**
     * The number of structural features of the '<em>Form</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.twenty11.skysail.ext.forms.impl.ValueImpl <em>Value</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.twenty11.skysail.ext.forms.impl.ValueImpl
     * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getValue()
     * @generated
     */
	int VALUE = 2;

	/**
     * The feature id for the '<em><b>String Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE__STRING_VALUE = 0;

	/**
     * The feature id for the '<em><b>Field</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE__FIELD = 1;

	/**
     * The number of structural features of the '<em>Value</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.twenty11.skysail.ext.forms.impl.FormInstanceImpl <em>Form Instance</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.twenty11.skysail.ext.forms.impl.FormInstanceImpl
     * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getFormInstance()
     * @generated
     */
	int FORM_INSTANCE = 3;

	/**
     * The feature id for the '<em><b>EReference0</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_INSTANCE__EREFERENCE0 = 0;

	/**
     * The feature id for the '<em><b>Form</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_INSTANCE__FORM = 1;

	/**
     * The number of structural features of the '<em>Form Instance</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_INSTANCE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.twenty11.skysail.ext.forms.Type <em>Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.twenty11.skysail.ext.forms.Type
     * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getType()
     * @generated
     */
	int TYPE = 4;


	/**
     * Returns the meta object for class '{@link de.twenty11.skysail.ext.forms.Field <em>Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field</em>'.
     * @see de.twenty11.skysail.ext.forms.Field
     * @generated
     */
	EClass getField();

	/**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.ext.forms.Field#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.twenty11.skysail.ext.forms.Field#getName()
     * @see #getField()
     * @generated
     */
	EAttribute getField_Name();

	/**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.ext.forms.Field#isRequired <em>Required</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Required</em>'.
     * @see de.twenty11.skysail.ext.forms.Field#isRequired()
     * @see #getField()
     * @generated
     */
	EAttribute getField_Required();

	/**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.ext.forms.Field#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.twenty11.skysail.ext.forms.Field#getType()
     * @see #getField()
     * @generated
     */
	EAttribute getField_Type();

	/**
     * Returns the meta object for class '{@link de.twenty11.skysail.ext.forms.Form <em>Form</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Form</em>'.
     * @see de.twenty11.skysail.ext.forms.Form
     * @generated
     */
	EClass getForm();

	/**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.ext.forms.Form#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.twenty11.skysail.ext.forms.Form#getName()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_Name();

	/**
     * Returns the meta object for the containment reference list '{@link de.twenty11.skysail.ext.forms.Form#getFields <em>Fields</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fields</em>'.
     * @see de.twenty11.skysail.ext.forms.Form#getFields()
     * @see #getForm()
     * @generated
     */
	EReference getForm_Fields();

	/**
     * Returns the meta object for class '{@link de.twenty11.skysail.ext.forms.Value <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value</em>'.
     * @see de.twenty11.skysail.ext.forms.Value
     * @generated
     */
	EClass getValue();

	/**
     * Returns the meta object for the attribute '{@link de.twenty11.skysail.ext.forms.Value#getStringValue <em>String Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String Value</em>'.
     * @see de.twenty11.skysail.ext.forms.Value#getStringValue()
     * @see #getValue()
     * @generated
     */
	EAttribute getValue_StringValue();

	/**
     * Returns the meta object for the reference '{@link de.twenty11.skysail.ext.forms.Value#getField <em>Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field</em>'.
     * @see de.twenty11.skysail.ext.forms.Value#getField()
     * @see #getValue()
     * @generated
     */
	EReference getValue_Field();

	/**
     * Returns the meta object for class '{@link de.twenty11.skysail.ext.forms.FormInstance <em>Form Instance</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Form Instance</em>'.
     * @see de.twenty11.skysail.ext.forms.FormInstance
     * @generated
     */
	EClass getFormInstance();

	/**
     * Returns the meta object for the containment reference list '{@link de.twenty11.skysail.ext.forms.FormInstance#getEReference0 <em>EReference0</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>EReference0</em>'.
     * @see de.twenty11.skysail.ext.forms.FormInstance#getEReference0()
     * @see #getFormInstance()
     * @generated
     */
	EReference getFormInstance_EReference0();

	/**
     * Returns the meta object for the containment reference '{@link de.twenty11.skysail.ext.forms.FormInstance#getForm <em>Form</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Form</em>'.
     * @see de.twenty11.skysail.ext.forms.FormInstance#getForm()
     * @see #getFormInstance()
     * @generated
     */
	EReference getFormInstance_Form();

	/**
     * Returns the meta object for enum '{@link de.twenty11.skysail.ext.forms.Type <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Type</em>'.
     * @see de.twenty11.skysail.ext.forms.Type
     * @generated
     */
	EEnum getType();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	FormsFactory getFormsFactory();

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
         * The meta object literal for the '{@link de.twenty11.skysail.ext.forms.impl.FieldImpl <em>Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.twenty11.skysail.ext.forms.impl.FieldImpl
         * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getField()
         * @generated
         */
		EClass FIELD = eINSTANCE.getField();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FIELD__NAME = eINSTANCE.getField_Name();

		/**
         * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FIELD__REQUIRED = eINSTANCE.getField_Required();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FIELD__TYPE = eINSTANCE.getField_Type();

		/**
         * The meta object literal for the '{@link de.twenty11.skysail.ext.forms.impl.FormImpl <em>Form</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.twenty11.skysail.ext.forms.impl.FormImpl
         * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getForm()
         * @generated
         */
		EClass FORM = eINSTANCE.getForm();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__NAME = eINSTANCE.getForm_Name();

		/**
         * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__FIELDS = eINSTANCE.getForm_Fields();

		/**
         * The meta object literal for the '{@link de.twenty11.skysail.ext.forms.impl.ValueImpl <em>Value</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.twenty11.skysail.ext.forms.impl.ValueImpl
         * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getValue()
         * @generated
         */
		EClass VALUE = eINSTANCE.getValue();

		/**
         * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE__STRING_VALUE = eINSTANCE.getValue_StringValue();

		/**
         * The meta object literal for the '<em><b>Field</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE__FIELD = eINSTANCE.getValue_Field();

		/**
         * The meta object literal for the '{@link de.twenty11.skysail.ext.forms.impl.FormInstanceImpl <em>Form Instance</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.twenty11.skysail.ext.forms.impl.FormInstanceImpl
         * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getFormInstance()
         * @generated
         */
		EClass FORM_INSTANCE = eINSTANCE.getFormInstance();

		/**
         * The meta object literal for the '<em><b>EReference0</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM_INSTANCE__EREFERENCE0 = eINSTANCE.getFormInstance_EReference0();

		/**
         * The meta object literal for the '<em><b>Form</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM_INSTANCE__FORM = eINSTANCE.getFormInstance_Form();

		/**
         * The meta object literal for the '{@link de.twenty11.skysail.ext.forms.Type <em>Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.twenty11.skysail.ext.forms.Type
         * @see de.twenty11.skysail.ext.forms.impl.FormsPackageImpl#getType()
         * @generated
         */
		EEnum TYPE = eINSTANCE.getType();

	}

} //FormsPackage
