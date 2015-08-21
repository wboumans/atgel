package org.atgel.web.el;

import org.atgel.util.ComponentResolver;
import org.atgel.web.template.ExpressionEvaluator;
import org.atgel.web.template.Template;

import atg.nucleus.logging.ApplicationLogging;
import atg.nucleus.logging.ClassLoggingFactory;
import atg.repository.Repository;
import atg.repository.RepositoryItem;
import atg.repository.RepositoryView;
import atg.repository.rql.RqlStatement;

public class NucleusELFunctions {

	private static ApplicationLogging mLogger = ClassLoggingFactory.getFactory().getLoggerForClass(NucleusELFunctions.class);

	private static Repository PRODUCT_CATALOG_REPOSITORY = (Repository) ComponentResolver.getComponent("/atg/commerce/catalog/ProductCatalog");
	private static Repository PROFILE_REPOSITORY = (Repository) ComponentResolver.getComponent("/atg/userprofiling/ProfileAdapterRepository");
	private static Template TEMPLATE = (Template) ComponentResolver.getComponent("/org/atgel/web/template/Template");

	public static RepositoryItem find(String repositoryName, String itemDescriptor, String id) {
		try {
			Repository repository = (Repository) ComponentResolver.getComponent(repositoryName);
			RepositoryItem item = repository.getItem(id, itemDescriptor);
			return item;
		} catch (Exception e) {
			mLogger.logError(e);
		}
		return null;
	}
	
	public static RepositoryItem[] rql(String repositoryName, String itemDescriptor, String rql) {
		try {
			Repository repository = (Repository) ComponentResolver.getComponent(repositoryName);
		 
			RepositoryView view = repository.getView(itemDescriptor);
			RqlStatement statement = RqlStatement.parseRqlStatement(rql);
	        Object[] params= {};
			RepositoryItem[] items = statement.executeQuery (view, params);
			return items;
		} catch (Exception e) {
			mLogger.logError(e);
		}
		return null;
	}

	public static RepositoryItem findSku(String id) {
		try {

			RepositoryItem item = PRODUCT_CATALOG_REPOSITORY.getItem(id, "sku");
			return item;
		} catch (Exception e) {
			mLogger.logError(e);
		}
		return null;
	}

	public static RepositoryItem findProfile(String id) {
		try {

			RepositoryItem item = PROFILE_REPOSITORY.getItem(id, "user");
			return item;
		} catch (Exception e) {
			mLogger.logError(e);
		}
		return null;
	}
	 


	public static String getTemplate(String name) {
		String template = TEMPLATE.get(name);
		String result = ExpressionEvaluator.evaluate(template);
		return result;
	}

}
