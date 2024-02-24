def call (String module, String branchName) {
    def RepoName = [
        "webapp": "webapp",
        "ansible": "ansible"
    ]
    gitlabhost = "github.com/srini7150/webapp.git"
    def commitidScript = libraryResource("scripts/currentCommit.sh")
    writeFile file: "currentCommitId.sh", text: commitidScript
    def REPO_NAME = RepoName["${module}"]
    sh("ls && chmod 755 currentCommitId.sh")
    def currentCommit = sh(returnStdout: true, script: "source ./currentCommitId.sh && currentCommitId ${REPO_NAME} ${gitlabhost} ${branchName}").trim()
    return "${currentCommit}"
}