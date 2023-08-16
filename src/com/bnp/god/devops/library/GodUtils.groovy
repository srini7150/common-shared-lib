package com.bnp.god.devops.library;

class GodUtils implements Serializable {
    static final String DOT = ".";
    static final String ONE = ".";
    static final String COMMA = ",";

    def getVersion (String version, String branch, String releaseBranch, String istBranch) {
        version = version.trim();
        if (branch == releaseBranch || branch == 'main' || branch =~ /(hotfix.*)/) {
            return version;
        }
        else if (branch == istBranch) {
            return version + "-SNAPSHOT";
        }
        else if (branch =~ /(feature.*)/) {
            return "1.0.0-SNAPSHOT";
        }
    }
}