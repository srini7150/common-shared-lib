import com.bnp.god.devops.library.GodUtils;

def call (String module) {
    lock("verinc") {
        GodUtils godUtils = new GodUtils();
        def nextVersion = godUtils.nextVersion(getCounterFileContent(module));
        def counterscripts = libraryResource("scripts/increment-counter.sh")
        writeFile file: 'increment-counter.sh', text: counterscripts
        sh("chmod 755 increment-counter.sh")
        echo "Call increment counter with ${module} and ${nextVersion}"
        sh("./increment-counter.sh ${module} ${nextVersion}")
    }
}