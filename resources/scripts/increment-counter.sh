#!/bin/bash
# Increment version.counter and pushes the code back to git

function incVer() {
    if [ -d temp-verinc ]; then
        rm -rf temp-verinc
    fi
    mkdir temp-verinc && cd temp-verinc
    git config --global user.email 'srinivasdevops7150@gmail.com'
    git config --global user.name 'srini7150'
    git clone https://${GITLAB_CREDS}@https://github.com/srini7150/common-shared-lib.git

    cd common-shared-lib && git checkout release && echo $2 > resources/$1/version.counter && git add resources/$1/version.counter && git commit -m "[ci skip] Bumping version to $2 of $1 module" && git push
    cd ../../
    pwd
    rm -rf temp-verinc
}