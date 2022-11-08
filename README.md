## Technical Assesment for Diamond Age

**You will need java 8+ installed to run this** 

To execute the program, execute this on the command line:

`./gradlew run`

This will fetch some libraries from maven central. If there are gradle errors related to fetching libraries, you may have
maven central blocked in your network.


After running the simulation, you will see an output of node status values as well as the numbers that the worker used for comparison. 

There will be a final result that shows the overall status.  It will look something like:

```
============ END Final Status: 'SUCCESS'
```

the tree for this solution looks like:

```

                              |- worker
                 |- sequence -|        
                 |            |- worker
                 |
                 |            |- worker
fallback (root) -|- sequence -|
                 |            |- worker
                 |            
                 |            |- worker
                 |- sequence -|        
                              |- worker


```

### Tests
if you wish to run the unit tests use the following on the command line:

`./gradlew test`

