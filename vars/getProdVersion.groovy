import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call(String module) {
    def prodVersionCounter = module + "/prod.version";
    return libraryResource(prodVersionCounter).trim();
}