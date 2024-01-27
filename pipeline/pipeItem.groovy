pipeline {
    agent any

    stages {
        stage('Execute commands') {
            steps {
                script {
                    sh 'mkdir debianItem'
                    sh 'mkdir debianItem/DEBIAN'
                    sh 'mkdir debianItem/usr'
                    sh 'mkdir debianItem/usr/local'
                    sh 'mkdir debianItem/usr/local/bin'
                    sh 'wget https://github.com/RemmReyy/lab/archive/main.zip'
                    sh 'unzip main.zip'
                    sh 'mv lab-main/debian/control debianItem/DEBIAN'
                    sh 'mv lab-main/calc_files.sh debianItem/usr/local/bin/'
                    sh 'dpkg-deb --build debianItem'
                }
            }
        }
        stage('Install Debian Package') {
            steps {
                script {
                    sh 'dpkg -i debianItem.deb'
                    sh 'chmod +x /usr/local/bin/calc_files.sh'
                    sh 'calc_files.sh --check_dir=lab-main'
                    sh 'rm -r lab-main'
                }
            }
        }
    }
}
