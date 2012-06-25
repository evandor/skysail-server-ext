package de.twenty11.skysail.server.ext.forms;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import de.twenty11.skysail.ext.forms.Form;
import de.twenty11.skysail.ext.forms.FormsFactory;
import de.twenty11.skysail.ext.forms.impl.FormsFactoryImpl;

public class FormTest {
	
    private FormsFactory formFactory;

	@Before
    public void setUp() throws Exception {
		FormsFactoryImpl.init();
        formFactory = FormsFactoryImpl.eINSTANCE;
    }

    @After
    public void tearDown() throws Exception {
    }

	@Test
	public void test() {
		Form simpleForm = formFactory.createForm();
		simpleForm.setName("Simple Form");
		// create resource set and resource
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register XML resource factory
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",
		new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI("simple.xmi"));
		// add the root object to the resource
		resource.getContents().add(simpleForm);
		try {
			resource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
