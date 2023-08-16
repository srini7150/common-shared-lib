import com.bnp.god.devops.library.GodUtils;

def call (String module, String branch) {
    def releaseBranch = getReleaseBranch();
    def istBranch = getIstBranch();
    GodUtils godUtils = new GodUtils();
    if (branch == "main") {
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch, istBranch);
    } else if (branch =~ /(hotfix.*)/) {
        def hotfixCount = getHotfixCounter(module);
        return godUtils.getVersion(getProdVersion(module), branch, releaseBranch, istBranch) + "." + hotfixCount;
    }
    else {
        return godUtils.getVersion(getCounterFileContent(module), branch, releaseBranch, istBranch);
    }
}