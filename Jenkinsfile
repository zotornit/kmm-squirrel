pipeline {
    agent { label 'canXCODE' }
    stages {
        stage('Build SNAPSHOT') {
            when { not { buildingTag() } }
            environment {
                SIGNING_KEY_ID = credentials('maven-central-signing-zotornit-key-id')
                SIGNING_KEY_PASSPHRASE = credentials('maven-central-signing-zotornit-key-passphrase')
                PUBLISH_ACCOUNT = credentials('maven-central-sonatype-io-github-zotornit-publish')
            }
            steps {
                script {
                    sh './gradlew clean publish -Dorg.gradle.internal.publish.checksums.insecure=true -PpublishUser=$PUBLISH_ACCOUNT_USR -PpublishPassword=$PUBLISH_ACCOUNT_PSW -PsigningKeyId=$SIGNING_KEY_ID -PsigningPassphrase=$SIGNING_KEY_PASSPHRASE'
                }
            }
        }
        stage('Build MASTER tag') {
            when { buildingTag() }
            environment {
                SIGNING_KEY_ID = credentials('maven-central-signing-zotornit-key-id')
                SIGNING_KEY_PASSPHRASE = credentials('maven-central-signing-zotornit-key-passphrase')
                PUBLISH_ACCOUNT = credentials('maven-central-sonatype-io-github-zotornit-publish')
            }
            steps {
                script {
                    // macOS requires the ".bak" parameter otherwise sed will not work
                    sh '''
                        sed -i ".bak" "/^version/d" build.gradle.kts
                        echo "\nversion = \\"$TAG_NAME\\"" >> build.gradle.kts
                    '''
                    sh './gradlew clean publishAllPublicationsToSonatypeRepository closeAndReleaseSonatypeStagingRepository -Dorg.gradle.internal.publish.checksums.insecure=true -PpublishUser=$PUBLISH_ACCOUNT_USR -PpublishPassword=$PUBLISH_ACCOUNT_PSW -PsigningKeyId=$SIGNING_KEY_ID -PsigningPassphrase=$SIGNING_KEY_PASSPHRASE -PpublishUrl=https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/'
                }
            }
        }
    }

    environment {
        EMAIL_TO = "tp@zotorn.de"
    }
    post {
//             always {
//             }
        success {
            emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
                      to: "${EMAIL_TO}",
                      subject: 'Build success: $PROJECT_NAME - #$BUILD_NUMBER')

        }
        failure {
            emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
                      to: "${EMAIL_TO}",
                      subject: 'Build FAILURE: $PROJECT_NAME - #$BUILD_NUMBER')
        }
        unstable {
            emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
                      to: "${EMAIL_TO}",
                      subject: 'Jenkins build UNSTABLE: $PROJECT_NAME - #$BUILD_NUMBER')
        }
        changed {
            emailext (body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, escapeHtml=false}',
                      to: "${EMAIL_TO}",
                      subject: 'Jenkins build CHANGED back to normal: $PROJECT_NAME - #$BUILD_NUMBER')
        }
    }
}