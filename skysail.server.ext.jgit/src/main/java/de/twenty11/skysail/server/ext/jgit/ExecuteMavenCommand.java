package de.twenty11.skysail.server.ext.jgit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.OS;
import org.apache.commons.exec.PumpStreamHandler;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.ext.jgit.internal.MavenFormDescriptor;

public class ExecuteMavenCommand implements Command {

    private LocalRepositoryDescriptor repositoryDescriptor;
    private MavenFormDescriptor entity;
    private ExecuteWatchdog watchdog;
    private PrintResultHandler resultHandler;
    private ByteArrayOutputStream outputStream;

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
        // Map map = new HashMap();
        // map.put("file", file);
        CommandLine commandLine = new CommandLine(resolveCmdForOS("mvn"));
        commandLine.addArgument(entity.getCommand());
        // commandLine.setSubstitutionMap(map);

        Executor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.setWorkingDirectory(new File(entity.getWorkingDir()));
        outputStream = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(outputStream, System.err, System.in));

        watchdog = new ExecuteWatchdog(15 * 60 * 1000);
        executor.setWatchdog(watchdog);

        System.out.println("[print] Executing non-blocking print job  ...");
        resultHandler = new PrintResultHandler(watchdog);
        executor.execute(commandLine, resultHandler);

        System.out.println(outputStream.toString());
    }

    private String resolveCmdForOS(String cmd) {
        if (OS.isFamilyWindows()) {
            return cmd + ".bat";
        } else if (OS.isFamilyUnix()) {
            return cmd;
        } else if (OS.isFamilyOpenVms()) {
            return cmd + ".dcl";
        } else {
            throw new IllegalStateException("Execution not supported for this OS");
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

    @Override
    public List<String> executionMessages() {
        // try {
        // outputStream.flush();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        String[] lines = outputStream.toString().split("\\n");
        outputStream.reset();
        return Arrays.asList(lines);
    }
}
