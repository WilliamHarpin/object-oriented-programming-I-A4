//========================================
// Assignment 4
// Written by: William Harpin 40212540
// For COMP 248 Section U - April 8th Winter 2022
//========================================

/*
This program is a classroom management system in which the user can accomplish multiple tasks. These tasks include: Enrolling a student, finding a student's position,
retrieving a student's information, deleting a student, updating scores, updating the student's name and displaying individual and classroom report cards. All of these
tasks are executed by referring to the student ID which in turn, through the getStudentIdx method, lets the program know which position this student holds in the class
list. Knowing this position and through the use of arrays for every type of information (final exam grades, assignment grades, etc.), the program keeps track of which
specific elements correspond to each specific student. Thus, the program can retrieve these values, calculate weighted grades for each student, return their letter
grade and display a report card for the entire class. Through the use of an all encompassing while loop in the main method, after each task, the program re-prompts
the user for a task code, allowing for further changes.
*/

import java.util.Scanner;
import java.util.Arrays;

class Comp248secU { // Variable Declaration on the next lines
	private static int classSize;
	private static String semesterYear;
	private static String lectureRoom;
	private static String instructorFname;
	private static String instructorLname;
	private String[] studFname = { " " };
	private String[] studLname = { " " };
	private int[] studID = { 1 };
	private double[] assgt1 = { 0.0 };
	private double[] assgt2 = { 0.0 };
	private double[] assgt3 = { 0.0 };
	private double[] assgt4 = { 0.0 };
	private double[] labs = { 0.0 };
	private double[] midterm = { 0.0 };
	private double[] finalExam = { 0.0 };
	
	// More Variables
	static int currentPlace = 0;
	static int currentclasssize=0;
	static boolean found;
	private double[] weightScores = { 0.0 };
	private static char[] letterGrades = { ' ' };
	static char letterGrade;
	static double minimum;
	static double maximum;
	static double sum;
	static double[] minArray = new double[7];
	static double[] maxArray = new double[7];
	static double[] avgArray = new double[7];
	public Comp248secU(String fname, String lname, String room, String semYr, int size) { //Constructor
		instructorFname = fname;
		instructorLname = lname;
		lectureRoom = room;
		semesterYear = semYr;
		classSize = size;
		studID = Arrays.copyOf(studID, classSize);
		studFname = Arrays.copyOf(studFname, classSize);
		studLname = Arrays.copyOf(studLname, classSize);
		assgt1 = Arrays.copyOf(assgt1, classSize);
		assgt2 = Arrays.copyOf(assgt2, classSize);
		assgt3 = Arrays.copyOf(assgt3, classSize);
		assgt4 = Arrays.copyOf(assgt4, classSize);
		finalExam = Arrays.copyOf(finalExam, classSize);
		midterm = Arrays.copyOf(midterm, classSize);
		labs = Arrays.copyOf(labs, classSize);
		weightScores = Arrays.copyOf(weightScores, classSize);
		letterGrades = Arrays.copyOf(letterGrades, classSize);
		
	}	
	
	
	public void addStudent(String fname, String lname, int studID) {
		if (currentclasssize >= classSize) { //Checking if the class is already full
			System.out.println("Student with ID: " + studID + " CANNOT be added. Class is already full.");
		}
		else { // If class isn't full, add the student's information to the arrays and display success message
			System.out.println("Student with ID: " + studID + " added successfully.");
			this.studID[currentclasssize] = studID;
			this.studFname[currentclasssize] = fname;
			this.studLname[currentclasssize] = lname;
			currentclasssize++;
		}
	}
	
	
	public int getStudentIdx(int studID) {
		int i = 0;
		Comp248secU.found = false; // Important boolean that will be used throughout the project to know if the student ID entered exists
		for (i = 0; i < this.studID.length; i++) {
			if (studID == this.studID[i]) { // is the student id found in the array
				Comp248secU.found = true; // Updated to true if a matching student ID is found while looping through the array
				currentPlace = i;
				break;
			}
			else {
				Comp248secU.found = false;
			}
		}
		if (!Comp248secU.found) {
			System.out.print("Student with ID: " + studID + " does NOT exist\n");
		}
		return i;
	}
	
	
	public String[] getStudentInfo(int studID) {
		getStudentIdx(studID); // used to obtain my the position of the student's info in the arrays (currentPlace)
		if (Comp248secU.found == true) {
			System.out.print("Student's First Name = " + this.studFname[currentPlace] + "\n"); // Printing all info
			System.out.print("Student's Last Name = " + this.studLname[currentPlace] + "\n");
			System.out.print("Student's ID = " + this.studID[currentPlace] + "\n");
			System.out.print("Score in Assignment 1 = " + this.assgt1[currentPlace] + "\n");
			System.out.print("Score in Assignment 2 = " + this.assgt2[currentPlace] + "\n");
			System.out.print("Score in Assignment 3 = " + this.assgt3[currentPlace] + "\n");
			System.out.print("Score in Assignment 4 = " + this.assgt4[currentPlace] + "\n");
			System.out.print("Cumulative Score in Labs = " + this.labs[currentPlace] + "\n");
			System.out.print("Mid-Term Test Score = " + this.midterm[currentPlace] + "\n");
			System.out.print("Final Examination Score = " + this.finalExam[currentPlace] + "\n");
		}
		else {
			System.out.println("Unable to retrieve information for Student with ID: " + studID); // Prints if student with that ID is not found
		}
		return studFname;
	}
	
	
	public int delStudent(int studID) {
		getStudentIdx(studID);
		if (Comp248secU.found == true) {
			for (int j = currentPlace; j < classSize; j++) {
				if (j != 0) {
					this.studFname[j] = this.studFname[j-1];
					this.studLname[j] = this.studFname[j-1];
					this.studID[j] = this.studID[j-1];
				}
				else {
					this.studFname[j] = this.studFname[j+1];
					this.studLname[j] = this.studLname[j+1];
					this.studID[j] = this.studID[j+1];
				}
			}
			System.out.println("Successfully removed Student with ID: " + studID);
		}
		else {
			System.out.println("Unable to retrieve information for Student with ID: " + studID); // if not found
		}
		return 0;
	}
	
	
	public int updateStudentPart(String fname, String lname, int studID) {
		getStudentIdx(studID); // Get position
			if (Comp248secU.found == true) {
				this.studFname[currentPlace] = fname; // If found, replace the current first name and last name with the new string input
				this.studLname[currentPlace] = lname;
				this.studID[currentPlace] = studID;
				System.out.println("Successfully updated identification particulars for Student with ID: " + studID);
			}
			else {
				System.out.println("Unable to retrieve information for Student with ID: " + studID); // If not found
			}
		return 0;
	}
	
	
	public int updateAssgtScore(double a1, double a2, double a3, double a4, int studID) {
		getStudentIdx(studID); // Get position
		if (Comp248secU.found == true) {
			this.assgt1[currentPlace] = a1; // The values for assgt are initialized to 0. Here we are replacing the values in the array with the corresponding user input
			this.assgt2[currentPlace] = a2;
			this.assgt3[currentPlace] = a3;
			this.assgt4[currentPlace] = a4;
			System.out.println("Successfully updated assignments' for Student with ID: " + studID);
		}
		else {
			System.out.println("Unable to retrieve information for Student with ID: " + studID); // if not found
		}
		return 0;	
	}
	
	
	public int updateOtherScores(double lab, double test, double exam, int studID) {
		getStudentIdx(studID); // Get position
		if (Comp248secU.found == true) {
			this.labs[currentPlace] = lab; // Similar to the previous method, the values for labs, mid term and final are updated with the user input
			this.midterm[currentPlace] = test;
			this.finalExam[currentPlace] = exam;
			System.out.println("Successfully updated Cummulative Labs, Mid-Term Test, and Final Examination scores for Student with ID: " + studID);
		}
		else {
			System.out.println("Unable to retrieve information for Student with ID: " + studID); // if not found
		}
		return 0;	
	}
	
	static double weightScore = 0; // initialize weightScore variable
	public double computeWeightScore(int studID) {
		getStudentIdx(studID); // Get position
		if (Comp248secU.found == true) { // The following will calculate the weighted score based on all the inputed specific scores for the student with this student ID
			weightScore = ((((this.assgt1[currentPlace])/20.0)*100.0)*0.02) + ((((this.assgt2[currentPlace])/20.0)*100.0)*0.03) +
								((((this.assgt3[currentPlace])/20.0)*100.0)*0.05) + ((((this.assgt4[currentPlace])/20.0)*100.0)*0.08) +
								((((this.labs[currentPlace])/12.0)*100.0)*0.12) + ((((this.midterm[currentPlace])/30.0)*100.0)*0.30) +
								((((this.finalExam[currentPlace])/40.0)*100.0)*0.40);
			weightScores[currentPlace] = weightScore; 
		}
		else {}
		return weightScore; // returns calculated weightScore
	}
	
	
	public static char computeGrade(double weightScore) {
			double wgtScore = weightScore;
			if (wgtScore >= 88) { // Use if statements to find which letter grade the weightScore corresponds to
				letterGrade = 'A';
			}
			else if (wgtScore >= 80) {
				letterGrade = 'B';
			}
			else if (wgtScore >= 67) {
				letterGrade = 'C';
			}
			else if (wgtScore >= 60) {
				letterGrade = 'D';
			}
			else {
				letterGrade = 'F';
			}
			letterGrades[currentPlace] = letterGrade;
		return letterGrade; // returns letter grade
	}
	
	
	public static double findMin(double[] dataArr) { // loop through array to find the minimum
		minimum = dataArr[0];
		for (int i = 1; i < classSize; i++) {
			if (dataArr[i] < minimum) {
				minimum = dataArr[i];
			}
		}
		return minimum; // return minimum
	}
	
	public static double findMax(double[] dataArr) { // loop through array to find the maximum
		maximum = dataArr[0];
		for (int i = 1; i < classSize; i++) {
			if (dataArr[i] > maximum) {
				maximum = dataArr[i];
			}
		}
		return maximum; // return maximum
	}
	
	
	public static double findAvg(double[] dataArr) { // loop through array and add up elements to find the sum
		sum = 0;
		for (int i = 0; i < classSize; i++) {
			sum = sum + dataArr[i];
		}
		sum/=classSize; // divide the sum by the classSize and store this calculated value in the sum
		return sum; // return sum (which after execution corresponds to the average)
	}
	
	
	public double[] getClassMin() { // looping through and assigning the minimum values of each assignment/exam to the minArray
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				findMin(assgt1);
				minArray[i] = minimum;
			}
			if (i == 1) {
				findMin(assgt2);
				minArray[i] = minimum;
			}
			if (i == 2) {
				findMin(assgt3);
				minArray[i] = minimum;
			}
			if (i == 3) {
				findMin(assgt4);
				minArray[i] = minimum;
			}
			if (i == 4) {
				findMin(labs);
				minArray[i] = minimum;
			}
			if (i == 5) {
				findMin(midterm);
				minArray[i] = minimum;
			}
			if (i == 6) {
				findMin(finalExam);
				minArray[i] = minimum;
			}
		}
		return minArray;
	}
	
	
	public double[] getClassMax() { // looping through and assigning the maximum values of each assignment/exam to the maxArray
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				findMax(assgt1);
				maxArray[i] = maximum;
			}
			if (i == 1) {
				findMax(assgt2);
				maxArray[i] = maximum;
			}
			if (i == 2) {
				findMax(assgt3);
				maxArray[i] = maximum;
			}
			if (i == 3) {
				findMax(assgt4);
				maxArray[i] = maximum;
			}
			if (i == 4) {
				findMax(labs);
				maxArray[i] = maximum;
			}
			if (i == 5) {
				findMax(midterm);
				maxArray[i] = maximum;
			}
			if (i == 6) {
				findMax(finalExam);
				maxArray[i] = maximum;
			}
		}
		return maxArray;
	}
	
	
	public double[] getClassAvg() { // looping through and assigning the average values of each assignment/exam to the avgArray
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				findAvg(assgt1);
				avgArray[i] = sum;
			}
			if (i == 1) {
				findAvg(assgt2);
				avgArray[i] = sum;
			}
			if (i == 2) {
				findAvg(assgt3);
				avgArray[i] = sum;
			}
			if (i == 3) {
				findAvg(assgt4);
				avgArray[i] = sum;
			}
			if (i == 4) {
				findAvg(labs);
				avgArray[i] = sum;
			}
			if (i == 5) {
				findAvg(midterm);
				avgArray[i] = sum;
			}
			if (i == 6) {
				findAvg(finalExam);
				avgArray[i] = sum;
			}
		}
		return avgArray;
	}
	
	
	public void classReportCard() {
		System.out.println();
		System.out.println();
		System.out.println("Displaying Report Card...");
		System.out.println("-------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("First Name\tLast Name\tStud. ID\tA1\tA2\tA3\tA4\tLab\tTest\tExam\tWgt.\t*");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < classSize; i++) { // Printing out all info assigned to specific class positions (i) and formatting this info so that everything lines up
			System.out.printf("%-16s%-16s%-16d%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%s", studFname[i], studLname[i], studID[i], assgt1[i], assgt2[i], 
					assgt3[i], assgt4[i], labs[i], midterm[i], finalExam[i], computeWeightScore(studID[i]), computeGrade(Comp248secU.weightScore));
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%42s\t" , "Minimum Score in Class: "); // formatting so that it lines up
		for (int i = 0; i < 7; i++) {
			System.out.printf("%-8.2f", getClassMin()[i]); // Printing the minArray which corresponds to the lowest grade in each assignment/exam
		}
		System.out.println();
		System.out.printf("%42s\t" , "Average Score in Class: "); // formatting
		for (int i = 0; i < 7; i++) {
			System.out.printf("%-8.2f", getClassAvg()[i]); // Printing the avgArray which corresponds to the average grade in each assignment/exam
		}
		System.out.println();
		System.out.printf("%42s\t" , "Maximum Score in Class: "); // formatting
		for (int i = 0; i < 7; i++) {
			System.out.printf("%-8.2f", getClassMax()[i]); // Printing the maxArray which corresponds to the highest grade in each assignment/exam
		}
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}	
	
}

public class A4_Q1_Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	Scanner input = new Scanner(System.in);
		
	System.out.println("Welcome to the Simple Classroom Management System:"); // Welcome message
	System.out.println("--------------------------------------------------");
	System.out.println();
	System.out.println("Enter instructor's particulars (FirstName, LastName, LectureRoom, Semester, MaxClassSize) as a single-line entry:");
	boolean condition = true;
	int Studentid = 0;
	String firstName = input.next(); // obtain inputs
	String lastName = input.next();
	String lectureRoom = input.next();
	String semester = input.next();
	int MaxClassSize = 0;
	if (input.hasNextInt() == true) { // validating that the input is an integer
		MaxClassSize = input.nextInt();
	}
	else {
		System.out.println("Error: Your input/entry for 'MaxClassSize' is NOT a valid integer. Kindly retry again!"); // error message + termination if it is not an integer
		System.exit(0); 
	}
	
	Comp248secU comp248secu = new Comp248secU(firstName, lastName, lectureRoom, semester, MaxClassSize);
	
	System.out.println(); // printing out all the codes that the user will refer to when progressing through the program
	System.out.println("Code => Description");
	System.out.println("-------------------");
	System.out.println("103 => Enrol New Student");
	System.out.println("106 => Find Student Position in Class List");
	System.out.println("109 => Retrieve Student's Information");
	System.out.println("112 => Unenrol Student");
	System.out.println("115 => Update Student's Particulars");
	System.out.println("118 => Update Assignment Scores");
	System.out.println("121 => Update Other Scores");
	System.out.println("124 => Display Student's Report Card");
	System.out.println("127 => Display Class Report Card");
	System.out.println("0 ===> Exit");
	System.out.println();
	System.out.println("Please enter a code, from the aforementioned, that corresponds to your task: ");
	
	int code; // initializing the code variable which corresponds to the task the user is wanting to do
	
	while (condition) { // while loop will be used to re-prompt the user after every task and/or error
	System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
	code = input.nextInt(); // obtaining the code
	
	if (!(code >= -128 && code <= 127)) { // validating if the code is between -128 and 127
		System.out.println("Error: Your input/entry is not a valid integer between -128 to 127. Kindly retry again!"); // error message + termination if not
		System.exit(0);
	}
	
	// all following if/else if statements are used to find which code the user entered and execute the corresponding task
	if (code == 103) {
		System.out.println();
		System.out.println();
		System.out.println("Enrolling New Student...");
		System.out.println("------------------------");
		System.out.println("Enter student's particulars (FirstName, LastName, StudentID) as a single-line entry:");
		String studentfirstname = input.next(); // obtain first name
		String studentlastname = input.next(); // obtain last name
		if (input.hasNextInt() == true) { // check if input is an integer
			Studentid = input.nextInt(); // if so, assign this integer to the Studentid variable
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // if it wasn't an integer, error message and restart while loop
			input.next();
			continue;
		}	
		comp248secu.addStudent(studentfirstname, studentlastname, Studentid); // calling the addStudent method
		continue;
	}
	
	
	else if (code == 106) {
		System.out.println();
		System.out.println();
		System.out.println("Finding Student's Position in Class List...");
		System.out.println("-------------------------------------------");
		System.out.println("Enter StudentID:");
		if (input.hasNextInt() == true) { // validate input
			Studentid = input.nextInt();
			comp248secu.getStudentIdx(Studentid); // calling getStudentIdx method
			if (Comp248secU.found) { // The following will be printed in the main method because the getStudentIdx method is used many other times in the program and we don't want these to print every time
				System.out.println("The position of student with ID: " + Studentid + ",in the class list, is: " + Comp248secU.currentPlace); // currentPlace corresponds to position
			}
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not an integer but here we don't terminate. User is re-prompted for a task code
		}
		continue;
	}
	
	
	else if (code == 109) {
		System.out.println();
		System.out.println();
		System.out.println("Retrieving Student's Information...");
		System.out.println("-----------------------------------");
		System.out.print("Enter StudentID: ");
		if (input.hasNextInt() == true) { // validate input
		Studentid = input.nextInt();
		comp248secu.getStudentInfo(Studentid); // call getStudentInfo method
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next(); // clearing scanner
		}
		continue;
	}
	
	
	else if (code == 112) {
		System.out.println();
		System.out.println();
		System.out.println("Unenrolling Student...");
		System.out.println("----------------------");
		System.out.println("Enter StudentID: ");
		if (input.hasNextInt() == true) { // validate input
			Studentid = input.nextInt();
			comp248secu.delStudent(Studentid); // call delStudent method
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next(); // clearing scanner
		}	
	}
	
	
	else if (code == 115) {
		System.out.println();
		System.out.println();
		System.out.println("Updating Student's Particulars...");
		System.out.println("---------------------------------");
		System.out.println("Enter update to student's particulars (FirstName, LastName, StudentID) as a single-line entry:");
		String newFirstName = input.next(); // obtain new particulars
		String newLastName = input.next();
		if (input.hasNextInt() == true) { // validate student id is an integer
			Studentid = input.nextInt();
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next();
			continue; // brings us back to the beginning of the while loop
		}
		comp248secu.updateStudentPart(newFirstName, newLastName, Studentid); // call updateStudentPart method
	}
	
	
	else if (code == 118) {
		System.out.println();
		System.out.println();
		System.out.println("Updating Assignment Scores...");
		System.out.println("-----------------------------");
		System.out.println("Enter update to student's Assignment scores (Assignment1, Assignment2, Assignment3, Assignment4, StudentID) as a single-line entry:");
		double ass1 = input.nextDouble(); // obtain assignment scores
		double ass2 = input.nextDouble();
		double ass3 = input.nextDouble();
		double ass4 = input.nextDouble();
		if (input.hasNextInt() == true) { // validate student id is an integer
			Studentid = input.nextInt();
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next();
			continue; // brings us back to the beginning of the while loop
		}
		comp248secu.updateAssgtScore(ass1, ass2, ass3, ass4, Studentid); // call updateAssgtScore method
 	}
 	
	
	else if (code == 121) {
		System.out.println();
		System.out.println();
		System.out.println("Updating Other Scores...");
		System.out.println("------------------------");
		System.out.println("Enter update to student's other scores (CumulativeLabs, Midterm, FinalExam, StudentID) as a single-line entry:");
		double cumuLabs = input.nextInt(); // update various grade inputs
		double midTerm = input.nextInt();
		double finalExam = input.nextInt();
		if (input.hasNextInt() == true) { // validate student id is an integer
			Studentid = input.nextInt();
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next();
			continue; // brings us back to the beginning of the while loop
		}
		comp248secu.updateOtherScores(cumuLabs, midTerm, finalExam, Studentid); // call updateOtherScores method
	}
	
	
	else if (code == 124) {
		System.out.println();
		System.out.println();
		System.out.println("Displaying Student's Report Card...");
		System.out.println("-----------------------------------");
		System.out.println("Enter StudentID:");
		if (input.hasNextInt() == true) { // validate student id is an integer
			Studentid = input.nextInt();
		}
		else {
			System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!"); // error if not integer
			input.next();
			continue; // brings us back to the beginning of the while loop
		}
		comp248secu.getStudentInfo(Studentid); // call getStudentInfo method to print out first part
		comp248secu.computeWeightScore(Studentid); // call computerWeightScore method to calculate the weighted score
		System.out.println("----------------------------------");
		System.out.print("Student's Cummulative Weighted Score = ");
		System.out.printf("%.2f\n" , Comp248secU.weightScore); // print formatted weight score
		Comp248secU.computeGrade(Comp248secU.weightScore); // call computeGrade method with the weight score as the element used
		System.out.println("Student's Final Letter Grade = " + Comp248secU.letterGrade); // print letter grade
	}
	
	
	else if (code == 127) {
		comp248secu.classReportCard(); // call classReportCard
	}
	
	
	else if (code == 0) {
		System.out.println();
		System.out.println("Thank you for using the SimpleClassroom Management System! Goodbye."); // Farewell message and termination
		System.exit(0);
	}
	
	
	else {
		System.out.println();
		System.out.println("Thank you for patronizing our Simple Classroom Management System."); // this is printed if the user inputs a valid integer but that does not correspond to one of the task codes
		System.exit(0); // termination
	}

	}
	input.close(); // close scanner
	
	}

}
