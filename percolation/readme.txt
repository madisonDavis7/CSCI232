/******************************************************************************
 *  Name:     Madison Davis
 *
 *  Hours to complete assignment (optional): Too many
 *
 ******************************************************************************/

Programming Assignment 1: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates? This should be something like 3-5 sentences.
 *****************************************************************************/

It first checks the four surrounding spots to see if they are open and if so connects 
them with the union(). Then I used the find() method to see if the curret node is 
connected to the top. 


/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to at most a few
 *  minutes. Do not include data points that takes less than 0.1 seconds.
 *  Each data point should be double the size of the preceding data point
 *****************************************************************************/

(keep T constant; n doubles)

 n          time (seconds)
------------------------------

Test with N: 50 and T: 50
mean(): 0.5898239999999999
stddev(): 0.025196457152965465
confidenceLow(): 0.5828398983998225
confidenceHigh(): 0.5968081016001773
time elapsed in seconds: 0.155

Test with N: 100 and T: 50
mean(): 0.593016
stddev(): 0.016744567928198107
confidenceLow(): 0.5883746425682135
confidenceHigh(): 0.5976573574317865
time elapsed in seconds: 0.621

Test with N: 200 and T: 50
mean(): 0.5924974999999999
stddev(): 0.010078722346242755
confidenceLow(): 0.5897038206966438
confidenceHigh(): 0.595291179303356
time elapsed in seconds: 11.871

Test with N: 400 and T: 50
mean(): 0.5925406249999999
stddev(): 0.006861628142685595
confidenceLow(): 0.590638678714448
confidenceHigh(): 0.5944425712855519
time elapsed in seconds: 170.184

(keep n constant; T doubles)

 T          time (seconds)
------------------------------

Test with N: 50 and T: 50
mean(): 0.5898720000000002
stddev(): 0.026536744183051818
confidenceLow(): 0.5825163897891203
confidenceHigh(): 0.59722761021088
time elapsed in seconds: 0.17

Test with N: 50 and T: 100
mean(): 0.5933439999999998
stddev(): 0.024762011961139908
confidenceLow(): 0.5884906456556164
confidenceHigh(): 0.5981973543443831
time elapsed in seconds: 0.097

Test with N: 50 and T: 200
mean(): 0.5917939999999999
stddev(): 0.027840030540617466
confidenceLow(): 0.5879355686207925
confidenceHigh(): 0.5956524313792073
time elapsed in seconds: 0.192

Test with N: 50 and T: 400
mean(): 0.5921509999999999
stddev(): 0.0261983029353378
confidenceLow(): 0.5895835663123368
confidenceHigh(): 0.594718433687663
time elapsed in seconds: 0.386


/******************************************************************************
 *  Using the empirical data from the above two tables, give an estimate
 *  of the running time of PercolationStats.java as function of both n and 
 *  T, using asymptotic notation, like:
 *
 *       O( N^2.0 * T )
 *
 *  Recall that with asymptotic notation, you are interested in the
 *  growth rate of run time as input sizes grow - you drop lower-order
 *  terms and constant factors. In class, we've discussed a way to 
 *  estimate polynomial factors from empirical run times.
 *
 *  How does this estimate compare to the theoretical run time growth
 *  rate, according to analysis of your code?
 *
 *****************************************************************************/

The worst case for quickfind in this case is O(T * N^2). From my empirical data 
and finding the ratios of each and averaging that I ended up with O( N^3.4 * T^.4)




/******************************************************************************
 *  Repeat the previous three questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 *****************************************************************************/

(keep T constant; n doubles)

 n          time (seconds)
------------------------------

Test with N: 300 and T: 100
mean(): 0.592158888888889
stddev(): 0.007288988888887259
confidenceLow(): 0.5907302470666671
confidenceHigh(): 0.5935875307111109
time elapsed in seconds: 0.757

Test with N: 600 and T: 100
mean(): 0.5926594444444446
stddev(): 0.004740030641131202
confidenceLow(): 0.591730398438783
confidenceHigh(): 0.5935884904501063
time elapsed in seconds: 2.812

Test with N: 1200 and T: 100
mean(): 0.5927059027777778
stddev(): 0.002559210112559955
confidenceLow(): 0.592204297595716
confidenceHigh(): 0.5932075079598396
time elapsed in seconds: 19.4

Test with N: 2400 and T: 100
mean(): 0.5929785902777779
stddev(): 0.00146791131300427
confidenceLow(): 0.592690879660429
confidenceHigh(): 0.5932663008951268
time elapsed in seconds: 132.075


(keep n constant; T doubles)

 T          time (seconds)
------------------------------

Test with N: 400 and T: 500
mean(): 0.5919487250000002
stddev(): 0.005530696691979442
confidenceLow(): 0.5914639380603651
confidenceHigh(): 0.5924335119396352
time elapsed in seconds: 5.525

Test with N: 400 and T: 1000
mean(): 0.5928216875000009
stddev(): 0.005851693526298132
confidenceLow(): 0.5924589957776385
confidenceHigh(): 0.5931843792223632
time elapsed in seconds: 10.801

Test with N: 400 and T: 2000
mean(): 0.5927468218749999
stddev(): 0.005850782540238253
confidenceLow(): 0.5924904000243617
confidenceHigh(): 0.5930032437256382
time elapsed in seconds: 25.515

Test with N: 400 and T: 4000
mean(): 0.5926867953125003
stddev(): 0.005828335642353424
confidenceLow(): 0.5925061733196422
confidenceHigh(): 0.5928674173053585
time elapsed in seconds: 46.137


Run time estimate:

 From my empirical data I got an estimated runntime of O( N^2.5 * T^2). 
 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

:<
 
My Computer.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, TA,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

I worked with the TA a few times to get everythig working. I also worked with 
Soren and Nate to bounce ideas off of and figure out more of the conceptual parts. 
A couple of my functions specifically the tests, were either taken from one of them
or modeled after what they had. 


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

Not being able to run or compile the program in VS code because it won't recognize
the Princeton libraries. 

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed (or hated) doing it.                                             
 *****************************************************************************/

//👻