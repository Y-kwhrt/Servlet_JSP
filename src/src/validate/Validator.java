package validate;

import java.util.regex.Pattern;

public class Validator {
	
	private StringBuffer errors;
	
	
	
	public Validator() {
		errors = new StringBuffer();
	}
	
	public String getErr() {
		 return errors.toString();
	}
	
	public boolean requireCheck(String param,String message) {
		boolean result = true;
		
		if(param == "") {
			result = false;
			errors.append("■" + message + "は必須入力です。<br>");
		}
		return result;
	}
	public boolean lengthCheck(String param,String message,int size) {
		boolean result = true;
		
		if(param.length() > size) {
			result = false;
			errors.append("■" + message + "は" + size + "文字以内です。<br>");
		}
		
		return result;
	}
	public boolean wordCheck(String param,String message) {
		boolean result = true;
		
		Pattern p = Pattern.compile("^[0-9\\-]*$");
		boolean paramIsNumberOnly = p.matcher(param).find();
		
		if(!paramIsNumberOnly) {
			result = false;
			errors.append("■" + message + "は数値入力です。<br>");
		}
		return result;
	}
	public boolean checklengthCheck(int param,String message,int size) {
		boolean result = true;
		
		if(size <= param) {
			result = false;
			int accSize = size - 1;
			errors.append("■" + message + "の選択は"+ accSize +"つまでです。<br>");
		}
		return result;
	}
	public boolean choiceCheck(int parm,String message,int size) {
		boolean result = true;
		
		if(parm == size) {
			result = false;
			errors.append("■" + message + "を選択してください。<br>");
		}
		return result;
	}
	public boolean lowlimCheck(String param,String message,int size) {
		boolean result = true;
		
		Pattern p = Pattern.compile("^[0-9\\-]*$");
		boolean paramIsNumberOnly = p.matcher(param).find();
		if(paramIsNumberOnly) {
		 if(Integer.parseInt(param) < size) {
			result = false;
			errors.append("■" + message + "は" + size + "以上です。<br>");
		 }
		}
		return result;
	}
}