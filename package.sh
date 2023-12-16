#!/usr/bin/env bash
TARGET="${TARGET:=.}"
JARNAME='declension.jar'
MAINCLASS='me.arthurmeade12.decliner.declension'
COMPILEPATH='me/arthurmeade12/decliner'
javac -d "${TARGET}" "${TARGET}"/*.java
#javac -d "${TARGET}" "${TARGET}"/**/*.java
jar -c -f "${TARGET}/${JARNAME}" -e "${MAINCLASS}" "${TARGET}/${COMPILEPATH}"

