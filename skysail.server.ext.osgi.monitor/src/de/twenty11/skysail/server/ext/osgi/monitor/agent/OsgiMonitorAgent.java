package de.twenty11.skysail.server.ext.osgi.monitor.agent;

import java.lang.instrument.Instrumentation;

import javassist.ClassPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.CallbackDefinition;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.Callbacks;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundle.BundleCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundlecontext.BundleContextCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.serviceregistration.ServiceRegistrationCallback;

/**
 * A java agent to monitor the underlying OSGi Framework.
 * 
 * It uses the {@link OsgiFrameworkTransformer} to instrument certain methods of the OSGi framework and defines the
 * callback functionality to be executed be the instrumented classes once they are called.
 * 
 */
public class OsgiMonitorAgent {

    /** the standard logger. */
    private static final Logger logger = LoggerFactory.getLogger(OsgiMonitorAgent.class);

    /** a logger to caputure the results. */
    private static final Logger agentLogger = createLoggerFor("agentLogger", "agentLogger.log");

    private static OsgiFrameworkTransformer transformer;

    private static int port = 9997;

    public static void premain(String agentArgs, Instrumentation inst) {

        logger.info("\n[Agent Setup]\n");

        createCommandListenerThread();

        logger.info("Starting instrumentation for profiling...");

        Callbacks callbacks = defineCallbacks();
        transformer = new OsgiFrameworkTransformer(ClassPool.getDefault(), callbacks, agentLogger);
        if (inst.isRetransformClassesSupported()) {
            inst.addTransformer(transformer, true);
        } else {
            logger.warn("Retransformation is not supported be the current JVM...");
            logger.warn("No osgi monitoring will be performed by skysail.server.ext.osgi.monitor.");
        }
    }

    private static void createCommandListenerThread() {
//        logger.info("starting CommandListener");
//        CommandListener serverSocketListener;
//        try {
//           // serverSocketListener = new CommandListener(port);
//           // new Thread(serverSocketListener, "socketListener").start();
//        } catch (IOException e) {
//            logger.error("was not able to create new CommandListener on port {}", port, e);
//        }
    }

    private static Callbacks defineCallbacks() {
        Callbacks callbacks = new Callbacks();
        callbacks.add(new CallbackDefinition(new BundleCallback(), agentLogger));
        callbacks.add(new CallbackDefinition(new BundleContextCallback(), agentLogger));
        callbacks.add(new CallbackDefinition(new ServiceRegistrationCallback(), agentLogger));
        return callbacks;
    }

    private static Logger createLoggerFor(String name, String file) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();

        ple.setPattern("%msg%n");
        ple.setContext(lc);
        ple.start();

        // FileAppender<ILoggingEvent> fileAppender = createAndStartFileAppender(file, lc, ple);

        ConsoleAppender<ILoggingEvent> consoleAppender = createAndStartConsoleAppender(lc, ple);

        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(name);
        // logger.addAppender(fileAppender);
        logger.addAppender(consoleAppender);

        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false);

        // if (defaultLogger instanceof ch.qos.logback.classic.Logger) {
        // Appender<ILoggingEvent> stdOutAppender = ((ch.qos.logback.classic.Logger) defaultLogger)
        // .getAppender("STDOUT");
        // if (stdOutAppender != null) {
        // logger.addAppender(stdOutAppender);
        // }
        // }

        return logger;
    }

    // private static FileAppender<ILoggingEvent> createAndStartFileAppender(String file, LoggerContext lc,
    // PatternLayoutEncoder ple) {
    // FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
    // fileAppender.setFile(file);
    // fileAppender.setEncoder(ple);
    // fileAppender.setContext(lc);
    // fileAppender.setAppend(false);
    // fileAppender.start();
    // return fileAppender;
    // }

    private static ConsoleAppender<ILoggingEvent> createAndStartConsoleAppender(LoggerContext lc,
            PatternLayoutEncoder ple) {
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
        consoleAppender.setEncoder(ple);
        consoleAppender.setContext(lc);
        consoleAppender.start();
        return consoleAppender;
    }
}
