package de.twenty11.skysail.server.ext.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	}

}
