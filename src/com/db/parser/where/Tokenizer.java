package com.db.parser.where;

import java.util.ArrayList;
import java.util.List;

import com.db.utils.ServerSideError;

public class Tokenizer {
	private List<Character> possibleChars =new ArrayList<Character>(){{
		add( '['); add( ']'); add( '{'); add( '}'); add( '@');
		add('.'); add(':'); add('\\'); add('/'); add('\'');
	}};
	public Token[] tokenize(String infix)/* throws UnparsableExpressionException, UnknownFunctionException*/ {
		final List<Token> tokens = new ArrayList<Token>();
		final char[] chars = infix.toCharArray();
		// iterate over the chars and fork on different types of input
		Token lastToken;
		int parenthesesBalance = 0;
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == ' ')
				continue;
			if (Character.isDigit(c)) {
				final StringBuilder valueBuilder = new StringBuilder(1);
				// handle the numbers of the expression
				valueBuilder.append(c);
				int numberLen = 1;
				while (chars.length > i + numberLen && Character.isDigit(chars[i + numberLen])) {
					valueBuilder.append(chars[i + numberLen]);
					numberLen++;
				}
				i += numberLen - 1;
				lastToken = new NumberToken(valueBuilder.toString());
			}else if(OperatorToken.isOperator(String.valueOf(c))){ //case <,>,<=,>=, =\
				final StringBuilder nameBuilder = new StringBuilder();
				nameBuilder.append(c);
				int offset = 1;
				while (chars.length > i + offset &&  (chars[i + offset] == '=')) {
					nameBuilder.append(chars[i + offset++]);
				}
				String name = nameBuilder.toString();
				if(OperatorToken.isOperator(name)){ //case >= and <=
					i += offset - 1;
					lastToken = new OperatorToken(name, OperatorToken.getOperation(name));
				}else{
					throw new ServerSideError(String.format("UnparsableExpressionException reading %s position %d",c ,i));
				}
			}else if (Character.isLetter(c) || c == '\'' || c == '_') {
				// can be a variable or function
				final StringBuilder nameBuilder = new StringBuilder();
				nameBuilder.append(c);
				int offset = 1;
				while (chars.length > i + offset && (Character.isLetter(chars[i + offset]) || Character.isDigit(chars[i + offset])
						|| possibleChars.contains(chars[i + offset]) || chars[i + offset] == '_')) {
					nameBuilder.append(chars[i + offset++]);
				}
				String name = nameBuilder.toString();
				if(OperatorToken.isOperator(name)){ //case and or operators
					i += offset - 1;
					lastToken = new OperatorToken(name, OperatorToken.getOperation(name));
				}else if (isStringValue(name)) {
					// a variable
					i += offset - 1;
					lastToken = new StringToken(name);
				} else if (isVariable(name)) {
					// might be a function
					i += offset - 1;
					lastToken = new VariableToken(name);
				}  else {
					// an unknown symbol was encountered
					throw new ServerSideError(String.format("UnparsableExpressionException reading %s starting from possition %d",name ,i));
				}
			}/* else if(OperatorToken.isOperator(String.valueOf(c))){ //case <,>,
				lastToken = new OperatorToken(String.valueOf(c), OperatorToken.getOperation(String.valueOf(c)));
			}*/else if (c == '(' || c == ')'/* || c == '[' || c == ']' || c == '{' || c == '}'*/) {
				lastToken = new ParenthesisToken(String.valueOf(c));
				if(((ParenthesisToken)lastToken).isOpen())
					parenthesesBalance++;
				else
					parenthesesBalance--;
			} else {
				// an unknown symbol was encountered
				throw new ServerSideError(String.format("UnparsableExpressionException reading %s position %d",c ,i));
			}
			tokens.add(lastToken);
		}
		checkParenthesesBalanceLevel(parenthesesBalance);
		return tokens.toArray(new Token[tokens.size()]);
	}
	
	private void checkParenthesesBalanceLevel(int parenthesesBalance) {
		if(parenthesesBalance > 0)
			throw new ServerSideError(String.format("Unbalanced parenthases : %s '(' more",parenthesesBalance));
		else if(parenthesesBalance < 0)
			throw new ServerSideError(String.format("Unbalanced parenthases : %s  ')' more",Math.abs(parenthesesBalance)));
	}

	private boolean isVariable(String name) {
		if(!isStringValue(name)){
			for (Character c : possibleChars) {
				for (char ch : name.toCharArray()) {
					if(c == ch)
						return false;
				}
			}
			return true;
		}
		return false;	
	}

	private boolean isStringValue(String name) {
		if(name.startsWith("\'") && name.endsWith("\'") && (name.substring(1, name.length()-1)).lastIndexOf("\'") == -1)
			return true;
		return false;
	}
}
