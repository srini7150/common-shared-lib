def call(String module) {
    def hotfixCounter = module + "/hotfix.counter";
    return LibraryResource(hotfixCounter).trim();
}