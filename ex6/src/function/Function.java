package function;
import variable.Type;

import java.util.HashMap;

/**
 * this class represents a function in the program it have a name and a signature.
 * and it can check whether the signature match other signatures
 */
public class Function {

	/**
	 * constructor for the function class
	 * @param name the name of the function
	 * @param signature a array of types representing the types the fun
	 */
	Function(String name,Type[] signature){
		this.name=name;
		this.signature = signature;

	}

	/**
	 * checks whether a Signature matches the Signature of this function
	 * @param otherSignature array of type representing arguments
	 * @return true if equal.
	 */
	boolean signatureMatch(Type[] otherSignature){
		if(this.signature.length!= otherSignature.length){
			return false;
		}
		for(int i = 0;i<this.signature.length;i++){
			if (this.signature[i]!=otherSignature[i]){
				return false;
			}
		}
		return true;

	}
	/*
	represents the signature of the function. eg what types of variables the function gets and in what order
	 */
	private Type[] signature;
	/*
	the name of the function
	 */
	private String name;


}
