import com.bnp.god.devops.library.GodUtils

def call (String module) {
    lock("verinc") {
        GodUtils godUtils = new GodUtils();
        def counterFileValue = getCounterFileContent(module);
        echo "counterFileValue is${counterFileValue}";
        def nextVersion = godUtils.nextVersion(getCounterFileContent(module));
        echo "nextVersion is ${nextVersion}"
        def counterscripts = libraryResource("scripts/increment-counter.sh")
        writeFile file: 'increment-counter.sh', text: counterscripts
        sh ("ls -la")
        sh("chmod 755 increment-counter.sh")
        echo "Call increment counter with ${module} and ${nextVersion}"
        sh ("ls -la")
        sh("source ./increment-counter.sh && incVer ${module} ${nextVersion}")
    }
}