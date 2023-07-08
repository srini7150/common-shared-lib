import com.cloudbees.groovy.cps.NonCPS;

@NonCPS
def call(String module) {
    def hotfixCounter = module + "/hotfix.counter";
    return libraryResource(hotfixCounter).trim();
}