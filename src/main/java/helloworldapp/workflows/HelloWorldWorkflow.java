package helloworldapp.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorldWorkflow {

    /**
     * This is the method executed when the Workflow Execution is started.
     * The Workflow Execution completes when this method finishes execution.
     */
    @WorkflowMethod
    String getGreeting(String name);
}
