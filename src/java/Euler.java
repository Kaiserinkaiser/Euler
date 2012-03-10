import java.io.Console;
//import java.lang.reflect.*;

import problems.*;

public class Euler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console console = System.console();
		
		boolean stillPrompting = true;
		
		while( stillPrompting ){
			stillPrompting = promptForProblemNumber(console);
		}
	}
	
	private static boolean promptForProblemNumber(Console console){
		String problemNumber = console.readLine("Enter a problem number, or press Enter to exit:");
		
		if( "".equals(problemNumber) ){
			return false;
		}
		
		// Problem numbers must be 4 characters
		while( problemNumber.length() < 4 ){
			problemNumber = "0".concat(problemNumber);
		}
		
		problemNumber = "P".concat(problemNumber);
		
		// Get the problem definition.  Fuck yeah reflection.
		try{
			Class c = Class.forName("problems.".concat(problemNumber));
			IProblem p = (IProblem)c.newInstance();
			double solution = p.solve();
			console.printf("The answer to %s is: %f\n", problemNumber, solution);
		} catch (ClassNotFoundException e){
			console.printf("That problem hasn't been created yet! Chose another.\n");
		} catch (IllegalAccessException e){
			console.printf("Something is wrong with that problem! Chose another.\n");
		} catch (InstantiationException e){
			console.printf("Something is wrong with that problem! Chose another.\n");
		}
		
		return true;
		
	}

}
