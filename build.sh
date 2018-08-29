#!/bin/bash

set -e

if [ -z "$ANDROID_HOME" ];then
    export ANDROID_HOME=$PWD/sdk
fi

./gradlew assembleDebug
LD_LIBRARY_PATH=./signapk/ java -jar signapk/signapk.jar keys/platform.x509.pem keys/platform.pk8 ./app/build/outputs/apk/debug/app-debug.apk app.apk
