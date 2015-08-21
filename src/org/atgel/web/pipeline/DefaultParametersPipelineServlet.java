package org.atgel.web.pipeline;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.atgel.util.ComponentResolver;
import org.atgel.web.el.ImplicitComponents;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

public class DefaultParametersPipelineServlet extends InsertablePipelineServlet {

	private ImplicitComponents implicitComponenets;

	public ImplicitComponents getImplicitComponenets() {
		return implicitComponenets;
	}

	public void setImplicitComponenets(ImplicitComponents implicitComponenets) {
		this.implicitComponenets = implicitComponenets;
	}



	public void runServlet(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse pResponse) throws IOException, ServletException {

		if (implicitComponenets != null) {
			for (Iterator<Entry<String, String>> it = implicitComponenets.getComponents().entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				pRequest.setAttribute(key, ComponentResolver.getComponent(value));
			}
		}
		passRequest(pRequest, pResponse);
	}
}
