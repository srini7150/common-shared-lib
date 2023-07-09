import com.cloudbees.groovy.cps.NonCPS;


def call() {
    return libraryResource("release-branch").trim();
}