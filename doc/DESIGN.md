# Project Name
Baby Names Analytics

## Table of Contents
* [Team](#team)
* [Roles](#role)
* [High Level Design](#highleveldesign)
* [Assumptions](#assumptions)
* [New feature Implementation](#newfeatureimplementation)
* [Known issues](#knownissues)
* [Extra features](#extrafeatures)
* [Suggestions](#suggestions)

## Team
Anish Kottu

## Roles
Anish Kottu - Designer, Developer, and Tester

## High-Level Design
* Separate the code to populating the data structure by reading the data file - it is implemented by BabyNameFileReader.
* Construct the data structure to improve the performance of the majority of the functions - The data structure used in 
the implementation is Map<Integer, List<Map<String, List<BabyName>>>>. The highest level is a Map that holds the data by
 year. Each year returns a list with 2 elements representing data for each gender. For each gender, the data is returned
  as a map with the key being the first letter of the name and value being the collection names starting with the same 
  first letter.
* Externalize the sorting mechanism outside objects to makes it easy to implement new features that have different 
ordering needs - This design is implemented by using Comparators.
* Create reusable methods to reduce the cost implementation of new methods - It is achieved by creating many reusable 
methods such as getGenderIndex(), populateDataForYearRange(), getNamesForAStartLetter() etc.
* Create self-contained objects - BabyNameRankSummary is created as a child object to BabyName to keep track of 
aggregation data related to rank. This object is used in several questions such as average rank, highest rank, etc.

## Assumptions
1. Assumed that the performance of the code is important.
2. Assumed that the same service instance will be used by several queries to avoid loading files repeatedly.
3. Assumed that data files will not contain billions of names to cause issues with memory management of the program.
4. Assumed that the format of return strings of the public method in line with the expected format with the client 
program

## New feature Implementation
*New Feature* - Given a range of years, and a gender, what name prefix has the most derivative names? Answer this 
question with an alphabetized list of all names with that prefix. For this question, one name (e.g., “Franklin” or 
“Annabelle”) is considered a derived variant of another (e.g., “Frank” or “Anna”) if the name’s starts with all the 
letters of the other name.
1. Assume that two prefixes will be passed to the public method along with a range of years and gender.
2. Use the populateDataForYearRange() to read the files for the range of years.
3. Get gender index using getGenderIndex().
4. Create two counters, one for each prefix.
5. Loop through all years, for each year get names start with getNamesForAStartLetter() matching first prefix.
6. In the same loop as step 5, get names start with getNamesForAStartLetter() matching second prefix.
7. Loop through collection returned from step 5, and increment counter for the first prefix when the name matches with 
any name in the collection.
8. Loop through collection returned from step 6, and increment counter for the first prefix when the name matches with 
any name in the collection.
9. At the end of step 5, return string indicating which prefix derived most in the data for a given year range.