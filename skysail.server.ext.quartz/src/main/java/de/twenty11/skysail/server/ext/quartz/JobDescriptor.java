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

@Form(name = "JobDetailForm")
public class JobDescriptor extends AbstractPresentable {

    public JobDescriptor(JobDetail jobDetail) {
        instanceToInspect = jobDetail;
    }

    public JobDescriptor() {
        // TODO Auto-generated constructor stub
    }

    @JsonCreator
    public JobDescriptor(@JsonProperty("name") String name) {
        this.name = name;
    }

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name must not be empty")
    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String name;

    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
