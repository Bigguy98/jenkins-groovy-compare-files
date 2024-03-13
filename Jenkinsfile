pipeline {
    // agent any

    stages {

        stage('[tttest-123] Verify Admin Profile') {
            node(SF_NODE_LABEL) {
            
                def fileHandler = load "${rootDir}/filehandler.Groovy" 

                def adminProfile = "source_code\\force-app\\main\\default\\profiles\\Admin.profile-meta.xml"
                def itSupportAdminProfile = "source_code\\force-app\\main\\default\\profiles\\IT Support Admin.profile-meta.xml"
                
                echo "Admin Profle Path: ${adminProfile}"
                echo "IT Support Admin Profle Path: ${itSupportAdminProfile}"
                echo "Current Directory: ${pwd()}"

                if (fileExists(adminProfile)) {
                    if (fileExists(itSupportAdminProfile)) {

                    def adminLayouts = fileHandler.getFileLayouts(adminProfile)
                    def supportLayouts = fileHandler.getFileLayouts(itSupportAdminProfile)
                    // Compare layout assignments using regex

                    def missingLayoutAssignments = fileHandler.getMissingLayoutAssignments(adminLayouts, supportLayouts)
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
}