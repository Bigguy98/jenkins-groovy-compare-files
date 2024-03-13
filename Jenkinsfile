node {
    stage('Example') {
        script {
                    def adminProfile = "${env.WORKSPACE}/Admin.profile-meta.xml"
                    def itSupportAdminProfile = "${env.WORKSPACE}/IT Support Admin.profile-meta.xml"
                    
                    echo "Admin Profle Path: ${adminProfile}"
                    echo "IT Support Admin Profle Path: ${itSupportAdminProfile}"
                    echo "Current Directory: ${pwd()}"

                    if (fileExists(adminProfile)) {
                        if (fileExists(itSupportAdminProfile)) {

                        def adminLayouts = getFileLayouts(adminProfile)
                        def supportLayouts = getFileLayouts(itSupportAdminProfile)
                        // Compare layout assignments using regex

                        def missingLayoutAssignments = getMissingLayoutAssignments(adminLayouts, supportLayouts)
                            if (!missingLayoutAssignments.isEmpty()) {
                            error "IT Support profile is missing the following layout assignments:\n${missingLayoutAssignments.join('\n')}"
                            }
                        } else {
                            error "IT Support Admin profile file is missing"
                        }
                    } else {
                            echo "Admin profile file is not present, skipping verification"
                    }

                }
    }
}

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