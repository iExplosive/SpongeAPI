/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.util;

import java.util.Random;

/**
 * Represents a value which may vary depending on a seed object
 * 
 * @param <T> The seed object type
 */
public abstract class SeededVariableAmount<T> {

    /**
     * Creates a new 'fixed' variable amount, calls to {@link #getAmount} will
     * always return the fixed value.
     * 
     * @param value The fixed value
     * @return A variable amount representation
     */
    public static <T> SeededVariableAmount<T> fixed(double value) {
        return new WrappedVariableAmount<T>(VariableAmount.fixed(value));
    }

    /**
     * Creates a new variable about which has a base and variance. The final
     * amount will be the base amount plus or minus a random amount between zero
     * (inclusive) and the variance (exclusive).
     * 
     * @param base The base value
     * @param variance The variance
     * @return A variable amount representation
     */
    public static <T> SeededVariableAmount<T> baseWithVariance(double base, double variance) {
        return new WrappedVariableAmount<T>(VariableAmount.baseWithVariance(base, variance));
    }

    /**
     * Creates a new variable amount which has a base and an additional amount.
     * The final amount will be the base amount plus a random amount between
     * zero (inclusive) and the additional amount (exclusive).
     * 
     * @param base The base value
     * @param addition The additional amount
     * @return A variable amount representation
     */
    public static <T> SeededVariableAmount<T> baseWithRandomAddition(double base, double addition) {
        return new WrappedVariableAmount<T>(VariableAmount.baseWithRandomAddition(base + addition / 2, addition / 2));
    }

    /**
     * Creates a new variable about which has a base and a chance to apply a
     * random variance. The chance should be between zero and one with a chance
     * of one signifying that the variance will always be applied. If the chance
     * succeeds then the final amount will be the base amount plus or minus a
     * random amount between zero (inclusive) and the variance (exclusive). If
     * the chance fails then the final amount will just be the base value.
     * 
     * @param base The base value
     * @param variance The variance
     * @param chance The chance to apply the variance
     * @return A variable amount representation
     */
    public static <T> SeededVariableAmount<T> baseWithOptionalVariance(double base, double variance, double chance) {
        return new WrappedVariableAmount<T>(VariableAmount.baseWithOptionalVariance(base, variance, chance));
    }

    /**
     * Creates a new variable about which has a base and a chance to apply a
     * random additional amount. The chance should be between zero and one with
     * a chance of one signifying that the additional amount will always be
     * applied. If the chance succeeds then the final amount will be the base
     * amount plus a random amount between zero (inclusive) and the additional
     * amount (exclusive). If the chance fails then the final amount will just
     * be the base value.
     * 
     * @param base The base value
     * @param addition The additional amount
     * @param chance The chance to apply the additional amount
     * @return A variable amount representation
     */
    public static <T> SeededVariableAmount<T> baseWithOptionalAddition(double base, double addition, double chance) {
        return new WrappedVariableAmount<T>(VariableAmount.baseWithOptionalAddition(base, addition, chance));
    }

    /**
     * Gets an instance of the variable amount depending on the given random
     * object and the seed object.
     * 
     * @param rand The random object
     * @param seed The seed object
     * @return The amount
     */
    public abstract double getAmount(Random rand, T seed);

    /**
     * Gets the amount as if from {@link #getAmount(Random, Object)} but floored
     * to the nearest integer equivalent.
     * 
     * @param rand The random object
     * @param seed The seed object
     * @return The floored amount
     */
    public int getFlooredAmount(Random rand, T seed) {
        return (int) Math.floor(getAmount(rand, seed));
    }

    /**
     * A {@link SeededVariableAmount} which wraps another {@link VariableAmount}
     * and defers all operations to the inner VariableAmount without the seed.
     * 
     * @param <T> The seed type
     */
    public static final class WrappedVariableAmount<T> extends SeededVariableAmount<T> {

        private final VariableAmount inner;

        /**
         * Creates a new WrappedVariableAmount wrapping the given
         * {@link VariableAmount}.
         * 
         * @param inner The inner variable amount
         */
        public WrappedVariableAmount(VariableAmount inner) {
            this.inner = inner;
        }

        @Override
        public double getAmount(Random rand, T seed) {
            return this.inner.getAmount(rand);
        }

        @Override
        public String toString() {
            return this.inner.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WrappedVariableAmount)) {
                return false;
            }
            WrappedVariableAmount<?> var = (WrappedVariableAmount<?>) obj;
            return this.inner.equals(var.inner);
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 37 * result + this.inner.hashCode();
            return result;
        }

    }

}
