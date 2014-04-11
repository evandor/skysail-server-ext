package de.twenty11.skysail.server.ext.osgi.monitor.agent.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class MyBundleImpl implements Bundle {

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public void start() throws BundleException {
        System.out.println("start");
    }

    @Override
    public void stop() throws BundleException {
    }

    @Override
    public void update() throws BundleException {
    }

    @Override
    public void update(InputStream in) throws BundleException {
    }

    @Override
    public void uninstall() throws BundleException {
    }

    @Override
    public Dictionary getHeaders() {
        return null;
    }

    @Override
    public long getBundleId() {
        return 0;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public ServiceReference[] getRegisteredServices() {
        return null;
    }

    @Override
    public ServiceReference[] getServicesInUse() {
        return null;
    }

    @Override
    public boolean hasPermission(Object permission) {
        return false;
    }

    @Override
    public URL getResource(String name) {
        return null;
    }

    @Override
    public Dictionary getHeaders(String locale) {
        return null;
    }

    @Override
    public String getSymbolicName() {
        return null;
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        return null;
    }

    @Override
    public Enumeration getResources(String name) throws IOException {
        return null;
    }

    @Override
    public Enumeration getEntryPaths(String path) {
        return null;
    }

    @Override
    public URL getEntry(String name) {
        return null;
    }

    @Override
    public long getLastModified() {
        return 0;
    }

    @Override
    public Enumeration findEntries(String path, String filePattern, boolean recurse) {
        return null;
    }

    @Override
    public int compareTo(Bundle o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void start(int options) throws BundleException {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop(int options) throws BundleException {
        // TODO Auto-generated method stub

    }

    @Override
    public BundleContext getBundleContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<X509Certificate, List<X509Certificate>> getSignerCertificates(int signersType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> A adapt(Class<A> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public File getDataFile(String filename) {
        // TODO Auto-generated method stub
        return null;
    }

}
