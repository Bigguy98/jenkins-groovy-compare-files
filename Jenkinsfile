pipeline {
    agent any


    stages {
        stage('Hello') {
            steps {
                script {
                    def fileHandler = load "filehandler.groovy" 

                    def adminProfile = "${env.WORKSPACE}/Admin.profile-meta.xml"
                    def itSupportAdminProfile = "${env.WORKSPACE}/IT Support Admin.profile-meta.xml"
                    
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

}
