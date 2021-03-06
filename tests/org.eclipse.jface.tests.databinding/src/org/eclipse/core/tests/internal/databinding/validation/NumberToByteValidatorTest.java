/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.core.tests.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToByteConverter;
import org.eclipse.core.internal.databinding.validation.NumberToByteValidator;
import org.eclipse.core.internal.databinding.validation.NumberToNumberValidator;

import com.ibm.icu.text.NumberFormat;

/**
 * @since 1.1
 */
public class NumberToByteValidatorTest extends NumberToNumberValidatorTestHarness {
	@Override
	protected Number doGetOutOfRangeNumber() {
		return Integer.valueOf(Byte.MAX_VALUE + 1);
	}

	@Override
	protected NumberToNumberValidator doGetToBoxedTypeValidator(Class<?> fromType) {
		NumberToByteConverter converter = new NumberToByteConverter(NumberFormat.getInstance(),
				fromType, false);
		return new NumberToByteValidator(converter);
	}

	@Override
	protected NumberToNumberValidator doGetToPrimitiveValidator(Class<?> fromType) {
		NumberToByteConverter converter = new NumberToByteConverter(NumberFormat.getInstance(),
				fromType, true);
		return new NumberToByteValidator(converter);
	}
}
