import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call() {
    return libraryResource("release-branch").trim();
}