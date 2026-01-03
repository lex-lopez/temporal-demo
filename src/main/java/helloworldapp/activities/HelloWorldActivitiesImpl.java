package helloworldapp.activities;

public class HelloWorldActivitiesImpl implements HelloWorldActivities {
    @Override
    public String composeGreeting(String name) {
        return "Hello " + name + "!";
    }
}
