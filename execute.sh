#!/bin/bash
cd src/
fmpjrun -np $1 -class es.udc.smunin.electricforce.Main $2
cd ..
