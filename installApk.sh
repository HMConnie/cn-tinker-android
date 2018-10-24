#!/usr/bin/env bash
# 0 debug  1 release

adb uninstall cn.connie.tinker

if [ $1 -eq 0 ]; then
  cd app/official/debug
  adb install app-official-debug.apk
elif [ $1 -eq 1 ]; then
 cd app/official/release
 adb install app-official-release.apk
else
  cd app/official/debug
  adb install app-official-debug.apk
fi