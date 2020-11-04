#!/usr/bin/env bash
echo -n "[04-GVT][$0] starting... "
cd my_repo

java -jar /home/mateusz/IdeaProjects/JavaLab/JavaLab/04-gvt/build/libs/04-gvt-1.0.jar history > message.out
if [[ $? -ne 0 ]]; then
    cd -
    echo "fail - invalid exit code after history: " $0
    exit 1
fi

cmp -s message.out ../expected10.out
if [[ $? -ne 0 ]]; then
    cd -
    echo "fail - invalid history content."
    exit 2
fi
rm -r -f message.out
cd -
