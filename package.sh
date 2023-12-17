#!/usr/bin/env bash
set -eu
TARGET="${TARGET:=.}"
JARNAME='main.jar'
MAINCLASS='me.arthurmeade12.decliner.main'
COMPILEPATH='me/arthurmeade12/decliner'
COMPDECL=${COMPDECL:=false};
javac -d "${TARGET}" "${TARGET}"/*.java
if [[ "${COMPDECL}" == 'true' ]]
then
	javac -d "${TARGET}" "${TARGET}"/**/*.java
fi
jar -c -f "${TARGET}/${JARNAME}" -e "${MAINCLASS}" "${TARGET}/${COMPILEPATH}"
exit "${?}"
