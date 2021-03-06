/*******************************************************************************
 * Copyright (c) 2008, 2019 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 247997)
 *     Matthew Hall - bugs 264307, 264619
 *     Justin Kuenzel - NPE
 ******************************************************************************/

package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.masterdetail.MasterDetailObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.DelegatingValueProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;

/**
 * @since 3.3
 *
 */
public class AnonymousBeanValueProperty extends DelegatingValueProperty {
	private final String propertyName;

	private Map delegates;

	/**
	 * @param propertyName
	 * @param valueType
	 */
	public AnonymousBeanValueProperty(String propertyName, Class valueType) {
		super(valueType);
		this.propertyName = propertyName;
		this.delegates = new HashMap();
	}

	@Override
	protected IValueProperty doGetDelegate(Object source) {
		return getClassDelegate(source.getClass());
	}

	private IValueProperty getClassDelegate(Class beanClass) {
		if (delegates.containsKey(beanClass))
			return (IValueProperty) delegates.get(beanClass);

		IValueProperty delegate;
		try {
			delegate = BeanProperties.value(beanClass, propertyName,
					(Class) getValueType());
		} catch (IllegalArgumentException noSuchProperty) {
			delegate = null;
		}
		delegates.put(beanClass, delegate);
		return delegate;
	}

	@Override
	public IObservableValue observeDetail(IObservableValue master) {
		Object valueType = getValueType();
		if (valueType == null)
			valueType = inferValueType(master.getValueType());
		return MasterDetailObservables.detailValue(master, valueFactory(master
				.getRealm()), valueType);
	}

	private Object inferValueType(Object masterObservableValueType) {
		if (masterObservableValueType instanceof Class) {
			IValueProperty classDelegate = getClassDelegate((Class) masterObservableValueType);
			return classDelegate != null ? classDelegate.getValueType() : null;
		}
		return null;
	}

	@Override
	public String toString() {
		String s = "?." + propertyName; //$NON-NLS-1$
		Class valueType = (Class) getValueType();
		if (valueType != null)
			s += "<" + BeanPropertyHelper.shortClassName(valueType) + ">"; //$NON-NLS-1$//$NON-NLS-2$
		return s;
	}
}
