#!/bin/bash


ROOT_DIR=$(cd $(dirname $0);cd ..;pwd)
DEPLOY_WORK_DIR="${ROOT_DIR}/.deploy"
FUNCTIONS_DIST_DIR="${ROOT_DIR}/.build/distributions/functions"
SAM_TEMPLATE_PATH="${ROOT_DIR}/deploy/template.yaml"


[ ! -e $DEPLOY_WORK_DIR ] && mkdir -p $DEPLOY_WORK_DIR
rm -rf $DEPLOY_WORK_DIR/*

cd $ROOT_DIR
./gradlew buildZip

cp $FUNCTIONS_DIST_DIR/* $DEPLOY_WORK_DIR/
cp $SAM_TEMPLATE_PATH $DEPLOY_WORK_DIR/
