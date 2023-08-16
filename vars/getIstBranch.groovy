import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call() {
    return libraryResource("ist-branch").trim();
}