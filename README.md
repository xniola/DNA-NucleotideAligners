# Needleman-Wunsch e Gotoh aligners.
Implementation of DNA sequence alignment algorithms. 

Project carried out as part of the undergraduate course in Algorithms and Data Structures at Unicam with Prof. Luca Tesei.

Below is a brief description of the project. For more information see the .doc file in the repository.


## Sequence Alignment

A sequence is a finite succession of symbols taken from a certain alphabet. In Java it can be safely equated with a string of characters. In biology specific letter sequences are used to represent strands of DNA or RNA. After the discovery of these, there was a strong push to develop automatic methods (algorithms) for recognizing and comparing sequences, which led to the birth of bioinformatics, a research field straddling computer science and biology. 
In bioinformatics, one of the problems on sequences that is useful to solve efficiently is that of similarity. This is justified by the fact that similar sequences probably evolved similarly or "evolved" from common ancestors. But exactly what does "similarity" mean? The answers may vary, depending also on the kind of "biological relationship" one is looking for. One of the most widely used notions of similarity is based on the notion of sequence alignment.
Suppose we have a special symbol, called a gap, and usually represented by a hyphen: "-." An alignment of two given sequences is a pair of sequences potentially containing gaps and indicating how to transform one sequence into the other and vice versa. 

Of course there can be different alignments between the same strings and among other things potentially infinite!

But then what is the "right" alignment? The basic idea is that each operation in the alignment, except of course the match, can be assigned a certain cost. So one can take as an alignment of two sequences any one for which the total cost of the operations (the sum) is minimal. It follows then that the problem of finding the alignment between two sequences is an optimization problem. 
    
We denote by a function that assigns costs to operations, called the cost function or scoring function. We then denote by the cost associated with the operation and assume that the cost of each type of operation is, in general, greater than or equal to zero except in the case of matching, for which the cost is zero.

How can we go about efficiently calculating the minimum, that is, the alignment distance? Dynamic programming comes to our aid! Indeed, we can show that it is possible to decompose the optimal alignment problem into analogous subproblems and that these subproblems enjoy the optimal substructure property.
