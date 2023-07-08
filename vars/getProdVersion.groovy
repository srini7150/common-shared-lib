def call(String module) {
    def prodVersionCounter = module + "/prod.version";
    return LibraryResource(prodVersionCounter).trim();
}