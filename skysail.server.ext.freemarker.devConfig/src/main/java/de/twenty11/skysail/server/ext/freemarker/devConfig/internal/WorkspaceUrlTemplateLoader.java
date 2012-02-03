package de.twenty11.skysail.server.ext.freemarker.devConfig.internal;

import java.io.File;
import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

import freemarker.cache.URLTemplateLoader;

/**
 * This class is instantiated from the FtlTrackerCustomizer and provides the
 * actual Template URLs.
 * 
 * @author carsten
 * 
 */
public class WorkspaceUrlTemplateLoader extends URLTemplateLoader {

    /**
     * Bundles might contain freemarker (ftl) files, which can be specified by
     * an identifier string.
     * 
     * Usually, this identifier is enough to find a specific ftl file inside the
     * bundle specified, but during development it is much quicker to find it in
     * the workspace.
     * 
     * @see freemarker.cache.URLTemplateLoader#getURL(java.lang.String)
     * @param identifier
     *            e.g. "de.evandor.skysail.server.restletosgi:menu.ftl"
     * @return url to the ftl file
     */
    @Override
    protected final URL getURL(final String identifier) {

        TemplateIdentifier templateIdentifier = new TemplateIdentifier(identifier);

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath workspaceDirectory = workspace.getRoot().getLocation();

        String[] packageParts = templateIdentifier.getPackageParts();
        String repIdentifier = "dev.gitRepPath." + packageParts[0] + "." + packageParts[1];
        String gitRepPath = ConfigServiceProvider.getConfigString(repIdentifier);
        if (gitRepPath == null) {
            gitRepPath = "";
        }
        File restletOsgiFtlDir = workspaceDirectory.append("../../../").append(gitRepPath)
                        .append("/" + templateIdentifier.getBundleName() + "/src/main/resources/freemarker/ftls")
                        .toFile();
        String[] ftlDirListing = restletOsgiFtlDir.list();
        for (String filename : ftlDirListing) {
            if (filename.endsWith(templateIdentifier.getTemplateName())) {
                try {
                    String filepath = ("file:///" + restletOsgiFtlDir + File.separator + filename).replace("\\", "/");
                    return new URL(filepath);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return null;
    }

    /**
     * helper class to split the identifier into meaningful values for bundle
     * and template names.
     * 
     * @throws IllegalArgumentException
     *             when the identifier is null, empty or not of the form 'a.b*:c'
     * @author carsten
     * 
     */
    static class TemplateIdentifier {

        /** an identifier for a bundle matching the symbolic name. */
        private String bundleName;

        /**
         * the name of a ftl template which can be found in the corresponding
         * bundle.
         */
        private String templateName;

        /** the bundlename's package parts, i.e. [a,b] for a bundlename like a.b. */
        private String[] packageParts;

        /**
         * getting an identifier expected in the form of
         * <bundlename>:<templatename>. The bundlename must at least have one ".".
         * 
         * @param identifier
         *            a string to be split into bundleName and templateName.
         */
        public TemplateIdentifier(final String identifier) {
            if (identifier == null || identifier.trim().length() == 0) {
                throw new IllegalArgumentException("provided identifier is empty");
            }
            if (!identifier.contains(":") || identifier.indexOf(":") == 0
                            || identifier.indexOf(":") == identifier.length() - 1) {
                throw new IllegalArgumentException("provided identifier is not of the form '*:c'");
            }
            String[] identifierSplit = identifier.split(":");
            this.bundleName = identifierSplit[0];
            this.templateName = identifierSplit[1];
            this.packageParts = this.bundleName.split("\\.");
            if (packageParts.length < 2) {
                throw new IllegalArgumentException("provided identifier is not of the form 'a.b*:c'");
            }
        }

        /**
         * @return the symbolic name of the bundle.
         */
        public String getBundleName() {
            return bundleName;
        }
        /**
         * @return the name of the template to be found in the corresponding
         *         bundle.
         */
        public String getTemplateName() {
            return templateName;
        }

        /**
         * @return a list of the parts which form the bundle name (split by dots ".").
         */
        public String[] getPackageParts() {
            return packageParts;
        }
    }

}
