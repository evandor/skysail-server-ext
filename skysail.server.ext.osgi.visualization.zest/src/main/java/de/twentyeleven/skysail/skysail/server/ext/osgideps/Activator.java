package de.twentyeleven.skysail.skysail.server.ext.osgideps;

import java.net.URI;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.InstallOperation;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private static final String JUNO_P2_REPO = "http://download.eclipse.org/releases/juno";
    private static BundleContext context;
    private ZestOsgiServicesVisualizer zestOsgiServicesVisualizer;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;

        ServiceReference sr = bundleContext.getServiceReference(IProvisioningAgentProvider.SERVICE_NAME);
        IProvisioningAgentProvider agentProvider = null;
        if (sr == null)
            return;
        agentProvider = (IProvisioningAgentProvider) bundleContext.getService(sr);
        final IProvisioningAgent agent = agentProvider.createAgent(new URI(
                "http://download.eclipse.org/releases/helios"));

        // get the repository managers and define our repositories
        IMetadataRepositoryManager manager = (IMetadataRepositoryManager) agent
                .getService(IMetadataRepositoryManager.SERVICE_NAME);
        IMetadataRepository repository = manager.loadRepository(new URI("http://download.eclipse.org/releases/helios"),
                new NullProgressMonitor());

        IArtifactRepositoryManager artifactManager = (IArtifactRepositoryManager) agent
                .getService(IArtifactRepositoryManager.SERVICE_NAME);
        manager.addRepository(new URI(JUNO_P2_REPO));
        artifactManager.addRepository(new URI(JUNO_P2_REPO));

        // Load and query the metadata
        IMetadataRepository metadataRepo = manager.loadRepository(new URI(JUNO_P2_REPO), new NullProgressMonitor());
        Collection toInstall = metadataRepo.query(QueryUtil.createIUQuery("org.eclipse.equinox.p2.demo.feature.group"),
                new NullProgressMonitor()).toUnmodifiableSet();

        // Creating an operation
        InstallOperation installOperation = new InstallOperation(new ProvisioningSession(agent), toInstall);
        if (installOperation.resolveModal(new NullProgressMonitor()).isOK()) {
            Job job = installOperation.getProvisioningJob(new NullProgressMonitor());
            job.addJobChangeListener(new JobChangeAdapter() {
                public void done(IJobChangeEvent event) {
                    agent.stop();
                }
            });
            job.schedule();
        }

        IQuery<IInstallableUnit> createIUQuery = QueryUtil.createIUQuery("org.eclipse.jdt");

        zestOsgiServicesVisualizer = new ZestOsgiServicesVisualizer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
        zestOsgiServicesVisualizer = null;
    }

}
