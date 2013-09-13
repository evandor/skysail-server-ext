package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonSetter;
import org.osgi.framework.Version;

public class BundleDescriptor implements Serializable {

    public static final String SYMBOLIC_NAME = "symbolicName";

    private static final long serialVersionUID = -3490514848138717614L;

    protected long bundleId;

    protected String symbolicName;

    protected String version;

    protected long lastModified;

    protected String state;

    @Transient
    private Map<String, String> links;

//    @Transient
//    private Map<String, String> links;
//
////    @Transient
////    private Reference reference;
//
//    public BundleDescriptor() {
//    }
//
//    public BundleDescriptor(Bundle bundle) {
//        this.bundleId = bundle.getBundleId();
//        this.symbolicName = bundle.getSymbolicName();
//        this.version = handleVersion(bundle.getVersion());
//        this.lastModified = bundle.getLastModified();
//        this.state = mapState(bundle.getState());
////        if (reference != null) {
////            this.reference = new Reference(reference.getBaseRef(), "bundles/details/" + this.bundleId);
////        }
//    }
//
//    private String mapState(int state) {
//        switch (state) {
//        case 1:
//            return "Uninstalled";
//        case 2:
//            return "Installed";
//        case 4:
//            return "Resolved";
//        case 8:
//            return "Starting";
//        case 16:
//            return "Stopping";
//        case 32:
//            return "Active";
//        default:
//            return "unknown state";


    private String mapState(int state) {
        switch (state) {
        case 1:
            return "Uninstalled";
        case 2:
            return "Installed";
        case 4:
            return "Resolved";
        case 8:
            return "Starting";
        case 16:
            return "Stopping";
        case 32:
            return "Active";
        default:
            return "unknown state";
        }
    }

    private String handleVersion(Version version) {
        StringBuffer sb = new StringBuffer();
        sb.append(version.getMajor()).append(".");
        sb.append(version.getMinor()).append(".");
        sb.append(version.getMicro());
        if (version.getQualifier() != null && version.getQualifier().trim() != "") {
            sb.append(".");
            sb.append(version.getQualifier());
        }
        return sb.toString();
    }

    public String getSymbolicName() {
        return symbolicName;
    }

    public void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName;
    }

    public void setBundleId(long bundleId) {
        this.bundleId = bundleId;
    }

    public long getBundleId() {
        return bundleId;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public long getLastModified() {
        return lastModified;
    }

    @JsonSetter
    public void setState(String state) {
        this.state = state;
    }

    public void setState(Integer state) {
        this.state = mapState(state);
    }

    public String getState() {
        return state;
    }

    public void setVersion(Version version) {
        this.version = handleVersion(version);
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "bundles/details/" + this.bundleId;
    }
}
