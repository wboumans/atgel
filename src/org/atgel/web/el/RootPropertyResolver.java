package org.atgel.web.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;

import org.atgel.util.ComponentResolver;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.ServletUtil;

public class RootPropertyResolver extends ELResolver {
	private Map<String, Object> rootObjects;

	public RootPropertyResolver(Map<String, Object> rootObjects) {
		this.rootObjects = rootObjects;
	}

	@Override
	public Class<String> getCommonPropertyType(ELContext context, Object base) {
		return base == null ? String.class : null;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
		if (base != null) {
			return null;
		}
		return new ArrayList<FeatureDescriptor>().iterator();
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		if (base != null) {
			return null;
		}
		context.setPropertyResolved(true);
		return null;
	}

	private Map<String, String> getImplicitComponenets() {
		ImplicitComponents implicitComponents = (ImplicitComponents) ComponentResolver.getComponent("/org/atgel/web/el/ImplicitComponents");
		Map<String, String> components = implicitComponents.getComponents();
		return components;
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		if (base != null) {
			return null;
		}
		context.setPropertyResolved(true);
		String propertyAsString = property.toString();

		Object rootObject = rootObjects.get(propertyAsString);
		if (rootObject != null) {
			return rootObject;
		}

		Object result = getPropertyFromImplicitComponents(propertyAsString);
		if (result != null) {
			return result;
		}

		DynamoHttpServletRequest currentRequest = ServletUtil.getCurrentRequest();
		if (currentRequest == null) {
			return null;
		}

		Object parameter = currentRequest.getObjectParameter(propertyAsString);
		if (parameter != null) {
			return parameter;
		}
		Object attribute = currentRequest.getAttribute(propertyAsString);
		return attribute;

	}

	private Object getPropertyFromImplicitComponents(String propertyAsString) {
		Map<String, String> components = getImplicitComponenets();
		if (components != null) {
			String componentString = components.get(propertyAsString);
			if (componentString != null) {
				Object component = ComponentResolver.getComponent(componentString);
				return component;
			}
		}
		return null;
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		if (base != null) {
			return true;
		}

		context.setPropertyResolved(true);
		return true;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value) {
		if (base != null) {
			return;
		}
		context.setPropertyResolved(true);
		throw new PropertyNotWritableException(String.valueOf(value));
	}

}