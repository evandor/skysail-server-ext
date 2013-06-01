package de.twenty11.skysail.server.ext.quartz;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.quartz.JobDetail;

import de.twenty11.skysail.common.AbstractPresentable;
import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;
import de.twenty11.skysail.common.forms.IgnoreValuesProvider;

@Form(name = "TriggerDetailForm")
public class TriggerDescriptor extends AbstractPresentable {

    public TriggerDescriptor(JobDetail jobDetail) {
        setInstanceToInspect(jobDetail);
    }

    public TriggerDescriptor() {
        // TODO Auto-generated constructor stub
    }

    @JsonCreator
    public TriggerDescriptor(@JsonProperty("name") String name) {
        this.name = name;
    }

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name must not be empty")
    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String name;

    public String getName() {
        return name;
    }

}
