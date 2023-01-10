package variable;

public class VariableFactory {

	public static Variable createVariable(boolean isFinal, String name, String type, boolean in) throws Exception {
		switch (type) {
			case INT:
				return new IntVariable(isFinal, name);
			case DOUBLE:
				return new DoubleVariable(isFinal, name);
			case STRING:
				return new IntVariable(isFinal, name);
				case CHAR:
				return new CharVariable(isFinal, name);
			case BOOLEAN:
				return new BooleanVariable(isFinal, name);


		}
		throw new Exception();


	}

	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String;";
	private static final String BOOLEAN = "boolean";
	private static final String CHAR = "char";
}
