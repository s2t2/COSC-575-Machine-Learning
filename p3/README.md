# COSC-575: Machine Learning #
### Project 3 ###
### Fall 2018 ###

#### Due: T 11/6 @ 5:00 P.M. ####
#### 10 points ####

Implement Flach's tree learner for symbolic attributes with the modifications we discussed in lecture. Use gain ratio to determine the best attribute for splitting. Implement pre-pruning for nodes with fewer than three examples. Implement post-pruning with the default parameters. Disable post-pruning if the user provides the option -u. Extend the Performance class so it computes and returns the area or the average under the ROC curve by adding the methods

```java
public void add( int actual, double[] pr ) throws Exception
public double getAUC()
public double getSDAUC()
```
For computing AUC, use the rating in pr[0].
Extend the Evaluator class from p2 to perform evaluation using the hold-out method. Use the -p switch to let users specify the proportion of the examples to use as training data.

You can find the important classes and methods for this project in the file p3.zip.

The implementation must be general. It must follow sound object-oriented design principles. Implement the learner as a single executable. No windows. No menus. No prompts. Just do it.

The logic of the implementation should be as follows. The user must provide a training set (using the -t switch). By default, the program should evaluate the method on the training set using 10-fold cross-validation and output the results. Naturally, the user can use the -x switch to change the default. If the user uses the switch -p, then the program should use the hold-out method to evaluate the method and print the results. Finally, if the user uses the switch -T, then the program should use the examples in the testing file to evaluate the method and print the results. You can assume that users will not give conflicting switches for evaluation.

**Instructions for Submission**
In a file named HONOR, please include the statement:
In accordance with the class policies and Georgetown's Honor Code,
I certify that, with the exceptions of the class resources and those
items noted below, I have neither given nor received any assistance
on this project.
Include this file in your zip file submit.zip.
Submit p3 exactly like you submitted p2.

**Plan B**
If Autolab is down, upload your zip file to Canvas.