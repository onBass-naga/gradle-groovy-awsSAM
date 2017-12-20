#!/bin/bash


SCRIPT_DIR=$(cd $(dirname $0);pwd)
ROOT_DIR=$(cd $(dirname $0);cd ..;pwd)
DEPLOY_WORK_DIR="${ROOT_DIR}/.deploy"
TEMPLATE_COPY_FILE_PATH="${ROOT_DIR}/.deploy/template.yaml"
OUTPUT_FILE_PATH="${ROOT_DIR}/.deploy/serverless-output.yaml"


$SCRIPT_DIR/preset.sh

cd $DEPLOY_WORK_DIR
sam local start-api
