import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call(String module) {
    def prodVersionCounter = module + "/prod.version";
    return LibraryResource(prodVersionCounter).trim();
}