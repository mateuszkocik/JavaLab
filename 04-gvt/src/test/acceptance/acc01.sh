#!/usr/bin/env bash
echo -n "[04-GVT][$0] starting... "
mkdir my_repo
cd my_repo
pwd

if [[ $(java -jar /home/mateusz/IdeaProjects/JavaLab/JavaLab/04-gvt/build/libs/04-gvt-1.0.jar) = "Please specify command." ]]; then
    echo "pass"
else
    cd -
    echo "fail"
    exit 1
fi

cd -
