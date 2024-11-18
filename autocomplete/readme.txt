/******************************************************************************
 *  Name: Madison Davis
 *
 *  Hours to complete assignment (optional): too many
 *
 ******************************************************************************/

Programming Assignment 2: Autocomplete


/******************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that equals the search key.
 *****************************************************************************/

I initialize two variables (low and high) and use those in binary search to keep 
searching for the target key. I divide the array in half and continue searching 
until the key is found. Then I search the left side again and do the same thing to 
see if the key is on the left, and if it is then that index becomes the first index
of the key, if no key is found then -1 is returned. 



/******************************************************************************
 *  What is the order of growth of the number of compares (in the
 *  worst case) that each of the operations in the Autocomplete
 *  data type make, as a function of the number of terms n and the
 *  number of matching terms m?  (Big-Oh notation)
 *
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower-order terms, e.g., m^2 + m log n.
 *****************************************************************************/

constructor: O(n log n)

allMatches(): O(n + m log n)

numberOfMatches(): O(log n)




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

nada

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings or lectures, but do include
 *  any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 *****************************************************************************/

I looked up the code for binary search and went off that, also had to look 
up some smaller things like reversing a sorting in ascending order. 

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/

For the most part I had far fewer issues with this program once I wrapped my 
head around it. 



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/

This was a fun one to work on

//👻