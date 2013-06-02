package de.twenty11.skysail.server.ext.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.activiti.internal.MyApplication;

public class TasksResource extends ListServerResource2<TaskDescriptor> {

    @Override
    @Get("html|json")
    public SkysailResponse<List<TaskDescriptor>> getEntities() {
        return getEntities("all tasks");
    }

    @Override
    protected List<TaskDescriptor> getData() {
        MyApplication application = (MyApplication) getApplication();
        TaskService taskService = application.getActivitiTaskService();
        NativeTaskQuery nativeTaskQuery = taskService.createNativeTaskQuery();
        List<Task> tasks = nativeTaskQuery.list();
        List<TaskDescriptor> results = new ArrayList<TaskDescriptor>();
        for (Task task : tasks) {
            results.add(new TaskDescriptor(task));
        }
        return results;
    }

}
