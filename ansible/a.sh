#!/bin/bash
# Mac Version
git ls-remote --refs --tags https://github.com/docker/compose | \
  cut -f 3 -d '/' | \
  egrep '^\d+\.\d+\.\d+$' | \
  gsort -r --version-sort | \
  head -n 1

# Ubuntu Version
#git ls-remote --refs --tags https://github.com/docker/compose | \
#  cut -d'/' -f3 | \
#  grep -P '^\d+\.\d+\.\d+$' | \
#  sort --version-sort | \
#  tail -n 1
