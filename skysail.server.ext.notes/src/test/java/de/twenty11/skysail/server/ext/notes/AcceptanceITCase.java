package de.twenty11.skysail.server.ext.notes;

import net.thucydides.jbehave.ThucydidesJUnitStories;

public class AcceptanceITCase extends ThucydidesJUnitStories {
    public AcceptanceITCase() {
        findStoriesCalled("**/adding_folder/creation.story");
        // findStoriesCalled("**/adding_folder/*.story");
        // findStoriesCalled("**/deleting_folder/*.story");
        // findStoriesCalled("folder_management/**/*.story");
    }
}
