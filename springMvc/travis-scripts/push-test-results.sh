#!/bin/bash

set -o errexit -o nounset

rev=$(git rev-parse --short HEAD)

cd build

git init
git config user.name "cg250"
git config user.email "cg250@student.le.ac.uk"

git remote add upstream "https://$GH_TOKEN@github.com/UOL-CS/co2015-group-06-repo.git"
git fetch upstream
git reset upstream/master

touch .

mkdir travis-build
cd travis-build

# Add the reports DO NOT UNCOMMENT BELOW!
#git add -A cucumber-html-report
#git add -A reports
#git add -A docs
#git add -A libs

git commit -m "[ci skip] Test results from travis at revision ${rev}"
git push -q upstream HEAD:master