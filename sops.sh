#!/usr/bin/env bash

############################################

cd src/main/resources || exit

programname=$0

function usage {
    echo "usage: $programname <decrypt|d|encrypt|e|updateKeys|u> [options]"
    echo "  decrypt|d: decrypt all files in 'environments' and add a '.decrypted' infix"
    echo "  encrypt|e: encrypt all files in 'environments' having the '.decrypted' infix"
    echo "  updateKeys|u: update all encrypted files after the fingerprints in .sops.yaml have been modified"
    echo ""
    echo "  options:"
    echo "  -- none currently --"
    exit 1
}

args=()

# parse commandline
# see (https://stackoverflow.com/a/13359121/1115279)
for i in "$@"
do
case $i in
    decrypt|d)
    MODE=decrypt
    ;;
    encrypt|e)
    MODE=encrypt
    ;;
    updateKeys|u)
    MODE=updateKeys
    ;;
    *)
    args+=("$i")
    ;;
esac
done

if [ -z $MODE ]; then
    usage
fi

if [ "${#args[@]}" -ne 0 ]; then
    usage
fi

# define some colors
RESTORE='\033[0m'
WHITE_BOLD='\033[1;37m'
GREEN='\033[00;32m'
RED='\033[0;31m'
YELLOW='\033[00;33m'
LIGHT_YELLOW='\033[01;33m'

function log() {
  COLOR=$2
  echo -e "${COLOR}$1${RESTORE}"
}

############################################

if [ "$MODE" = "updateKeys" ]; then
    echo "updating keys..."
    gpg --import ./ci.public.key
    find . -type f -name "*.enc.yml" -print0 | xargs -0 -I {} sops updatekeys --yes "{}"
fi

if [ "$MODE" = "decrypt" ]; then
    echo "decrypting..."

    while IFS= read -r -d '' file; do
        file_basename=`basename $file | cut -d. -f1`
        file_decrypted="$file_basename.yml"

        echo "decrypting $file to $file_decrypted"
        sops --decrypt --verbose --input-type yml "$file" > "$file_decrypted"

        if [ $? -ne 0 ]; then
            log "Could not decrypt file:$RESTORE $file" $RED
        fi

    done < <(find . -type f -name "*.enc.yml" -print0 2>/dev/null)
fi

if [ "$MODE" = "encrypt" ]; then
    echo "encrypting..."

    while IFS= read -r -d '' file; do
        file_basename=`basename $file | cut -d. -f1`
        file_encrypted="$file_basename.enc.yml"

        echo "encrypting $file to $file_encrypted"
        sops --encrypt "$file" > "$file_encrypted"

    done < <(find . -type f -name "application-*.yml" ! -name '.sops.yaml' ! -name "*.enc.yml" -print0 2>/dev/null)
fi

