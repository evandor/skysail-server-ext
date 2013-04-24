package de.twenty11.skysail.server.ext.jgit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.ext.jgit.internal.MavenFormDescriptor;
import org.apache.commons.exec.*;


public class ExecuteMavenCommand implements Command {

    private LocalRepositoryDescriptor repositoryDescriptor;
    private MavenFormDescriptor entity;
    private ExecuteWatchdog watchdog;
    private PrintResultHandler resultHandler;


    public ExecuteMavenCommand(LocalRepositoryDescriptor repositoryDescriptor, MavenFormDescriptor entity) {
        this.repositoryDescriptor = repositoryDescriptor;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return "Execute Maven";
    }

    @Override
    public String getDescription() {
        return "execute maven";
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {
        if (applicable()) {
            try {
                executeMaven();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeMaven() throws IOException {
        int exitValue;
        //Map map = new HashMap();
        //map.put("file", file);
        CommandLine commandLine = new CommandLine("mvn.bat");
        commandLine.addArgument(entity.getCommand());
        //commandLine.setSubstitutionMap(map);

        Executor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.setWorkingDirectory(new File(entity.getWorkingDir()));

        watchdog = new ExecuteWatchdog(15000);
        executor.setWatchdog(watchdog);

        if (true) { //printInBackground) {
            System.out.println("[print] Executing non-blocking print job  ...");
            resultHandler = new PrintResultHandler(watchdog);
            executor.execute(commandLine, resultHandler);
        } else {
            System.out.println("[print] Executing blocking print job  ...");
            exitValue = executor.execute(commandLine);
            resultHandler = new PrintResultHandler(exitValue);
        }
    }

    private class PrintResultHandler extends DefaultExecuteResultHandler {

        private ExecuteWatchdog watchdog;

        public PrintResultHandler(ExecuteWatchdog watchdog) {
            this.watchdog = watchdog;
        }

        public PrintResultHandler(int exitValue) {
            super.onProcessComplete(exitValue);
        }

        public void onProcessComplete(int exitValue) {
            super.onProcessComplete(exitValue);
            System.out.println("[resultHandler] The document was successfully printed ...");
        }

        public void onProcessFailed(ExecuteException e) {
            super.onProcessFailed(e);
            if (watchdog != null && watchdog.killedProcess()) {
                System.err.println("[resultHandler] The print process timed out");
            } else {
                System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
            }
        }
    }

}
