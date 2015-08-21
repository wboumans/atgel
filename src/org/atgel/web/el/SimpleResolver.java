package org.atgel.web.el;

/*
 * Copyright 2006-2009 Odysseus Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;

/**
 * Simple resolver implementation. This resolver handles root properties (top-level identifiers). Resolving "real" properties (
 * <code>base != null</code>) is delegated to a resolver specified at construction time.
 * 
 */
public class SimpleResolver extends ELResolver {
	private final CompositeELResolver delegate;

	/**
	 * Create a resolver capable of resolving top-level identifiers. Everything else is passed to the supplied delegate.
	 */
	public SimpleResolver(Map<String, Object> rootObjects) {
		delegate = new CompositeELResolver();
		delegate.add(new RootPropertyResolver(rootObjects));
		delegate.add(new ArrayELResolver(true));
		delegate.add(new ListELResolver(true));
		delegate.add(new MapELResolver(true));
		delegate.add(new ResourceBundleELResolver());
		delegate.add(new RepositoryItemELResolver(true));
		delegate.add(new I18nELResolver(true));
		delegate.add(new BeanELResolver(true));
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return delegate.getCommonPropertyType(context, base);
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
		return delegate.getFeatureDescriptors(context, base);
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		return delegate.getType(context, base, property);
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		return delegate.getValue(context, base, property);
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return delegate.isReadOnly(context, base, property);
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value) {
		delegate.setValue(context, base, property, value);
	}

	public Object invoke(ELContext context, Object base, Object method, Class<?>[] paramTypes, Object[] params) {
		return delegate.invoke(context, base, method, paramTypes, params);
	}
}
