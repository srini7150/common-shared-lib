import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call(String module) {
    def versionCounterFile = module + "/version.counter";
    return LibraryResource(versionCounterFile).trim();
}