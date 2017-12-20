#!/bin/bash

BUCKET_NAME=your-bucket-name
BUCKET_REGION=ap-northeast-1

SCRIPT_DIR=$(cd $(dirname $0);pwd)
ROOT_DIR=$(cd $(dirname $0);cd ..;pwd)
DEPLOY_WORK_DIR="${ROOT_DIR}/.deploy"
TEMPLATE_COPY_FILE_PATH="${ROOT_DIR}/.deploy/template.yaml"
OUTPUT_FILE_PATH="${ROOT_DIR}/.deploy/serverless-output.yaml"


$SCRIPT_DIR/preset.sh

aws cloudformation package \
   --template-file $TEMPLATE_COPY_FILE_PATH \
   --output-template-file $OUTPUT_FILE_PATH \
   --s3-bucket $BUCKET_NAME

aws cloudformation deploy \
   --template-file $OUTPUT_FILE_PATH \
   --stack-name gradle-groovy-sam-sample \
   --capabilities CAPABILITY_IAM \
   --region $BUCKET_REGION \
   --profile admin
