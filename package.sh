#!/usr/bin/env bash
TARGET="${TARGET:=.}"
JARNAME='declension.jar'
MAINCLASS='me.arthurmeade12.decliner.declension'
COMPILEPATH='me/arthurmeade12/decliner'
COMPDECL=false
javac -d "${TARGET}" "${TARGET}"/*.java
if [[ "${COMPDECL}" == 'true' ]]
then
	javac -d "${TARGET}" "${TARGET}"/**/*.java
fi
jar -c -f "${TARGET}/${JARNAME}" -e "${MAINCLASS}" "${TARGET}/${COMPILEPATH}"

