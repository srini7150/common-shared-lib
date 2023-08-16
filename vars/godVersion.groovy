import com.bnp.god.devops.library.GodUtils;

def call (String module, String branch) {
    def releaseBranch = getReleaseBranch();
    GodUtils godUtils = new GodUtils();
    if (branch == "main") {
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch);
    } else if (branch =~ /(hotfix.*)/) {
        def hotfixCount = getHotfixCounter(module);
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch) + "." + hotfixCount;
    }
    else {
        return godUtils.getVersion(getCounterFileContent(module), branch, releaseBranch);
    }
}