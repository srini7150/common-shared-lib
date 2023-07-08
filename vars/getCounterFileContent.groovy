def call(String module) {
    def versionCounterFile = module + "/version.counter";
    return LibraryResource(versionCounterFile).trim();
}