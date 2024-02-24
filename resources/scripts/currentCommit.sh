#!/bin/sh

REPO_NAME=$1
GITLAB_HOST=$2
BRANCH_NAME=$3
TEMP_FOLDER="temp_$REPO_NAME"

if [ -d $TEMP_FOLDER ]; then
    rm -rf $TEMP_FOLDER
fi

mkdir $TEMP_FOLDER && cd $TEMP_FOLDER
git clone https://$GITLAB_HOST
CURRENT_COMMIT_ID=$(cd $REPO_NAME && git checkout $BRANCH_NAME && git log --oneline -n 1 | awk '{print $1}')
echo "$CURRENT_COMMIT_ID"
