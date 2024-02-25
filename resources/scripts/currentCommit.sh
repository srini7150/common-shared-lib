#!/bin/bash

current_commit() {

    REPO_NAME=$1
    GIT_URL=$2
    BRANCH=$3

    TEMP_FOLDER="temp_$REPO_NAME"

    if [ -d $TEMP_FOLDER ]; then
        rm -rf $TEMP_FOLDER
    fi

    mkdir $TEMP_FOLDER && cd $TEMP_FOLDER && git clone $GIT_URL &> /dev/null && cd $REPO_NAME && git checkout $BRANCH &> /dev/null && git log --oneline -n 1 | awk '{print $1}'

}

current_commit "$1" "$2" "$3"