#!/usr/bin/env bash

# This script is used to retrieve the code coverage information from index.html
# file generated by the test2junit plugin. The two resulting files that are named
# coverage_forms.property and coverage_lines.property could be used to generate
# code coverage statistic etc. The format is also compatible with Jenkins graph
# plotter plugins.

echo YVALUE="$(tail -n 7 target/coverage/index.html | head -n 1 | sed 's/.*>\(.*\)[.].*/\1/')" >> coverage_forms.property
echo YVALUE="$(tail -n 5 target/coverage/index.html | head -n 1 | sed 's/.*>\(.*\)[.].*/\1/')" >> coverage_lines.property

