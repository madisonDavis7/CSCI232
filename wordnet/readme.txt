/******************************************************************************
 *  Name: madison davis
 *
 *  Hours to complete assignment (optional): too many -_-
 *
 ******************************************************************************/

Programming Assignment 3: WordNet


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/
-HashMap: keys sre nouns and vslues are synset ID's (strings)
-it was effecient and had all unique keys


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/
-Digraph: vertices are synset ID's and edges are hypernym relationships
-each synset can have multiple hypernyms so graph makes ShortestCommonAncestor
-flexible and efficient


/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. For each method in the API, what
 *  is the order of growth of the worst-case running time as a function
 *  of the number of vertices V and the number of edges E in the digraph?
 *  For each method, what is the order of growth of the best-case running time?
 *
 *  If you use hashing, you may assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use something like a BreadthFirstDirectedPaths object, 
 *  don't forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description:
-do BFS to find shortest common ancestor between v and was
-does it from each v and w 
-each generates istTo[] storing shortest paths 
-iterates over each vertex to see if there is one that reaches v and w
-for each it can it sums the distances from v and w to s (source)
-sca is one with shortes

                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w):                    O(V)                   V + E

ancestor(int v, int w):                    O(V)                 V + E

length(Iterable<Integer> v,
       Iterable<Integer> w):                O(V)                V(V + E)

ancestor(Iterable<Integer> v,
         Iterable<Integer> w):                O(V)              V(V + E)


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
 nada

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, but do include any 
 *  help from people (including course staff, TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
-I looked up apis for some stuff and what data structures to use
-a couple methods were based off a classmates 

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

type conversions were anoyinggg

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

//🎃