@NonCPS
def getFileLayouts(String fileName) {
    File file = new File(fileName)
    String fileContent = file.text
    def layouts = []
    def temp = (fileContent =~ /<layout>(.+)<\/layout>/)
    temp.eachWithIndex { item, index ->
        layouts.add(item[1])
    }
    return layouts;
}

def getMissingLayoutAssignments(List<String> layouts1, List<String> layouts2 ) {
    return (layouts1 - layouts2)
}

return this
