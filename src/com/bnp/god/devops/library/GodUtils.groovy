package com.bnp.god.devops.library;

class GodUtils implements Serializable {
    static final String DOT = ".";
    static final String ONE = ".";
    static final String COMMA = ",";

    def getVersion (String version, String branch, String releaseBranch) {
        version = version.trim();
        if (branch == releaseBranch || branch == 'main' || branch == branch.startsWith("hotfix")) {
            return version;
        }
        else if (branch.startsWith("feature")) {
            return version + "-SNAPSHOT"
        }
    }
}