# Data Plan
## Anish Kottu

This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci307/current/assign/01_data/):


### What is the answer to the two questions below for the data file yob1900.txt (pick a letter that makes it easy too answer)? 
1. Given the year 1900, the top ranked male name is John. The top ranked female name in 1900 was Mary.
2. Given the year 1900, the male gender, and the first letter "Y", there are 3 names with a total 22 babies fitting this specification. Given the year 1900, the female gender, and the first letter "Y", there are 5 names with a total 135 babies fitting this specification.

### Describe the algorithm you would use to answer each one.
1. The list is already ordered by the number of occurrences of each name, divided into two sections, male and female. Depending on which gender represented the top section, all the algorithm must do is output the first name. Then, it proceeds down the list until it arrives at a line marked the opposite gender, and outputs the associated first name at the top of that section.
2. For this one, perhaps just go through the file provided by the year, and order the list alphabetically. Then, proceed to create a count of the number of names a letter starts with, and the number of occurrences of those names.

### Likely you may not even need a data structure to answer the questions below, but what about some of the questions in Part 2?
I think in general, I need to create a series of data structures within data structures, populated by the files when those files are needed to be read. In fact, I have implemented this data structure already in trying to answer the first two questions, and additionally think that this data structure can be the basis of extending this implementation to other questions in part 2.

### What are some ways you could test your code to make sure it is giving the correct answer (think beyond just choosing "lucky" parameter values)?
I would have to start testing edge cases. One that comes to mind already is the edge case that occurs when there is a need to access multiple years' worth of data, which would effectively mean taking data from multiple files. If this edge case occurs, the algorithm I suggested for the first question no longer works, and a different method would need to be applied. I also think it would be helpful to examine what might happen if the user asked for both genders in conjunction, instead of just one or the other, like the questions have been so far.

### What kinds of things make the second question harder to test?
The reason the second question is harder to test is because it has more variables, and deals with more algorithmic complexities, such as both the total number of names and the total occurrences of those names. It is harder to keep track of an accumulation than to simply pull a certain name based on some specifications.

### What kind of errors might you expect to encounter based on the questions or in the dataset?
I think problems will come up when trying to combine both genders, or by combining multiple years' worth of data into one. Additionally, I think there will be bugs, especially in implementing helper methods, classes, and traversing through the data structure.

### How would you detect those errors and what would a reasonable "answer" be in such cases?
IntelliJ actually has been helpful in giving me assistance on my way to solving the little bugs I was faced with along the way, like giving me compilation errors. In conjuction with JUnit testing, it can help me identify particular lines where errors occur, and the debugging tools will be able to help me step through the code to find out why certain values are incorrect and where they are coming from.

### How would your algorithm and testing need to change (if at all) to handle multiple files (i.e., a range of years)?
I think all that would need to change in this case is my file reading algorithm, which would now need to account for taking in a startYear and endYear, and looping through the names of the files in the file system to populate my big global data structure.
