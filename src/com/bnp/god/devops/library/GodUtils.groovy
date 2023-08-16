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
        else {
            return "1.0.0-SNAPSHOT";
        }
    }

    def nextVersion (String version) {
        String[] ver = splitVersion(version);
        def subMinor = Integer.parseInt(ver[2]);
        subMinor++;
        ver[2] = subMinor.toString();
        return ver[0] + DOT + ver[1] + DOT + ver[2];
    }

    def splitVersion (String version) {
        String[] ver = new String[3];
        ver[0] = version.split("\\.")[0];
        ver[1] = version.split("\\.")[1];
        ver[2] = version.split("\\.")[2];
        return ver;
    }
}