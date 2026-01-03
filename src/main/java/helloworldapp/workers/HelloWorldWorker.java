package helloworldapp.workers;

import helloworldapp.activities.HelloWorldActivitiesImpl;
import helloworldapp.shared.SharedTemporalQueueNames;
import helloworldapp.workflows.HelloWorldWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class HelloWorldWorker {
    public static void main(String[] args) {
        /*
        By default, the client connects to the default namespace of the Temporal Service running at localhost
        on port 7233 by using the newLocalServiceStubs() method. If you want to connect to an external Temporal
        Service you would use the following code:

        WorkflowServiceStubs service =
            WorkflowServiceStubs.newServiceStubs(
                WorkflowServiceStubsOptions.newBuilder().setTarget("host:port").build());

        WorkflowClient client =
            WorkflowClient.newInstance(
                service, WorkflowClientOptions.newBuilder().setNamespace("YOUR_NAMESPACE").build());
         */

        // Get a Workflow service stub
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        /*
        * Get a workflow service client which can be used to start, Signal, and Query Workflow Executions.
         */
        WorkflowClient client = WorkflowClient.newInstance(service);

        /*
         * Define the workflow factory. It is used to create workflow workers that poll specific Task Queues.
         */
        WorkerFactory factory = WorkerFactory.newInstance(client);

        /*
         * Define the workflow worker. Workflow workers listen to a defined task queue and process
         * workflows and activities.
         */
        Worker worker = factory.newWorker(SharedTemporalQueueNames.HELLO_WORLD_TASK_QUEUE);

        /*
         * Register our workflow implementation with the worker.
         * Workflow implementations must be known to the worker at runtime in
         * order to dispatch workflow tasks.
         */
        worker.registerWorkflowImplementationTypes(HelloWorldWorkflowImpl.class);

        /*
         * Register our Activity Types with the Worker. Since Activities are stateless and thread-safe,
         * the Activity Type is a shared instance.
         */
        worker.registerActivitiesImplementations(new HelloWorldActivitiesImpl());

        System.out.println("Worker is running and actively polling the Task Queue.");
        System.out.println("To quit, use ^C to interrupt.");

        /*
         * Start all the workers registered for a specific task queue.
         * The started workers then start polling for workflows and activities.
         */
        factory.start();
    }
}
