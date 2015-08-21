package org.atgel.web.template;

import java.util.HashMap;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.atgel.web.el.SimpleResolver;

import atg.nucleus.logging.ApplicationLogging;
import atg.nucleus.logging.ClassLoggingFactory;
import de.odysseus.el.util.SimpleContext;

public class ExpressionEvaluator {

 
	private static ApplicationLogging mLogger = ClassLoggingFactory.getFactory().getLoggerForClass(ExpressionEvaluator.class);

	public static String evaluate(String expressionText, Map<String, Object> rootObjects) {
		String result = "";
		try {
			if (expressionText == null) {
				return "";
			}

			ExpressionFactory factory = new de.odysseus.el.ExpressionFactoryImpl();
			SimpleContext context = new SimpleContext(new SimpleResolver(rootObjects));
			ValueExpression expression = factory.createValueExpression(context, expressionText, String.class);
			Object expressionEvaluated = expression.getValue(context);
			if (expressionEvaluated != null) {
				result = expressionEvaluated.toString();
			}

			// trap to catch expressions within expressions
			if (result.contains("${")) {
				mLogger.logError("!!! expressions within expressions '" + expressionText + "' '" + result + "'\n");
			}
		} catch (Exception e) {
			result = "evaluation error: " + e.getMessage() + " expression=\'" + expressionText + "\' ";
			mLogger.logError("ExpressionEvaluator " + result + "\n");
		}
		return result;
	}

	public static String evaluate(String expersionText) {
		String result = evaluate(expersionText, new HashMap<String, Object>());
		return result;
	}

}
