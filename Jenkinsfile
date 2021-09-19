pipeline {
    agent { label 'canXCODE' }
    stages {
        stage('Build SNAPSHOT') {
            when { not { buildingTag() } }
//             environment {
//                 VELVAX_NEXUS = credentials('maven-test-snapshot')
//             }
            steps {
                script {
                    sh './gradlew clean build publish -Dorg.gradle.internal.publish.checksums.insecure=true'
                }
            }
        }
//         stage('Build MASTER tag') {
//             when { buildingTag() }
//             environment {
//                 VELVAX_NEXUS = credentials('robo-velvax-publish')
//             }
//             steps {
//                 script {
//                     sh 'sed -i \"s/^version.*/version = \\\\\\"$TAG_NAME\\\\\\"/g\" build.gradle.kts'
//                     sh './gradlew clean build publish -Dorg.gradle.internal.publish.checksums.insecure=true -PvelvaxPublishUser=$VELVAX_NEXUS_USR -PvelvaxPublishPassword=$VELVAX_NEXUS_PSW'
//                 }
//             }
//         }
    }

//     environment {
//         EMAIL_TO = "tp@zotorn.de"
//     }
//     post {
// //             always {
// //             }
//         success {
//             emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
//                       to: "${EMAIL_TO}",
//                       subject: 'Build success: $PROJECT_NAME - #$BUILD_NUMBER')
//
//         }
//         failure {
//             emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
//                       to: "${EMAIL_TO}",
//                       subject: 'Build FAILURE: $PROJECT_NAME - #$BUILD_NUMBER')
//         }
//         unstable {
//             emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
//                       to: "${EMAIL_TO}",
//                       subject: 'Jenkins build UNSTABLE: $PROJECT_NAME - #$BUILD_NUMBER')
//         }
//         changed {
//             emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
//                       to: "${EMAIL_TO}",
//                       subject: 'Jenkins build CHANGED back to normal: $PROJECT_NAME - #$BUILD_NUMBER')
//         }
//     }
}