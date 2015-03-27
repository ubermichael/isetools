#!/bin/bash
#make sure test numbers match with respect to tests
for k in `seq 1 59`; 
do 
	diff <(sed -n 1p input/test_$k.txt) <(sed -n 2p output/test_$k.xml)
done