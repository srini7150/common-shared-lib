import com.bnp.god.devops.library.GodUtils;

def call (String module, String branch) {
    def releaseBranch = getReleaseBranch();
    GodUtils godUtils = new GodUtils();
    if (branch == "master") {
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch);
    } else if (branch.endsWith("-hotfix")) {
        def hotfixCount = getHotfixCount(module);
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch); + "-hotfix" + hotfixCount;
    }
    else {
        return godUtils.getCounterFileContent(module, branch, releaseBranch);
    }
}