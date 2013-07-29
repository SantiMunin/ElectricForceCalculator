ElectricForceCalculator
=======================

Example application which computes the electric force of a bunch of electric charges. It allows to use several processors thanks to the [FastMPJ](http://torusware.com/nevonproducts/fastmpj/) technology.

Usage
-----------

```bash
git clone https://github.com/SantiMunin/ElectricForceCalculator
cd ElectricForceCalculator
make
sh execute.sh <num_procs> <num_charges>
```

Try a huge number of charges! Example: `sh execute.sh 4 30000`
