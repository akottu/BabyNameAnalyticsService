# Baby Names Analytics

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 

### Timeline

Start Date: August 17, 2020

Finish Date: September 11, 2020

Hours Spent: 25-30

## Table of Contents
* [Team](#team)
* [Resources](#resources)
* [Initial files used](#intialfiles)
* [Data files](#dataFiles)
* [Usage](#usage)
* [Known issues](#knownissues)
* [Extra features](#extrafeatures)
* [Suggestions](#suggestions)

## Team
Anish Kottu

## Resources
JDK 12 API documentation, Clean Code text

## Initial files used
The basic project provided, Main class, data files

## Test files
* AverageRankForABabyNameTest
* AverageRankInRecentYearsTest
* NameGenderMostRecentSameRankTest
* TopLetterListOverRangeTest
* TopNamesByGenderAcrossYearRangeTest
* YearGenderLetterNumberOfNamesTest
* HighestAverageRankTest
* MostRankVariationTest
* NamesHeldRankOftenTest
* NamesWithGivenRankTest
* RankForStartAndEndYearsTest
* RankTimelineForNameGenderTest
* TopRankByGenderTest

The above JUnit test cases are used for testing functionality. The test cases tested used different sets and tested 
with invalid data for the input variables.

## Data files
Data files in ssa_2000s and ssa_complete folders. Also, the datafiles yob3000.txt, yob3001.txt, yob3002.txt, 
yob3010.txt, and yob3011.txt are created to test the code.

## Usage
Use Junit test cases to understand the functionality and usage of the program. Also, there is the Main class that shows
how different methods can be called.

## Assumptions
1. The highest rank for a BabyName meant the lowest numerical value for the rank.
2. The valid gender values are "M" or "F".
3. Every row in the data file has all the required fields.
4. Data files are formatted correctly
5. The order of the child names in the data file indicates the rank of the child name instead of the number of times the
name is used
6. Names follow uniform capitalization and spacing for comparison purposes

## Known issues
1. It will not be able to handle genders other than "M" or "F".
2. It will not be able to handle corrupted data files efficiently.
3. Baby name comparison can be improved to handle capitalization, spaces correctly.
4. Validation can be added to make sure that ranks match the usage of the names.

## Extra features
Collections.sort is used for questions related to top rank, so it would be easy to modify the criteria for ranking or 
to add additional ranking questions.

## Suggestions
* The optional questions are vague and it would be helpful to provide clarity on the optional questions.
* Encouraging documentation for all the phases would help us think better about the implementation.