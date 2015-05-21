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
package org.spongepowered.api.world.gen.populator;

import org.spongepowered.api.data.type.DoubleSizePlantType;
import org.spongepowered.api.util.VariableAmount;
import org.spongepowered.api.util.weighted.WeightedCollection;
import org.spongepowered.api.util.weighted.WeightedObject;
import org.spongepowered.api.world.gen.Populator;

import java.util.Collection;

/**
 * Represents a populator which spawns in an assortment of two block tall
 * plants. The number of plants attempted to be generated is
 * {@code final = base + [0,variance) }.
 */
public interface DoublePlant extends Populator {

    /**
     * Gets a mutable weighted collection of possible plants which may be
     * selected to be spawned in by this populator.
     * 
     * @return The possible types to be spawned
     */
    WeightedCollection<WeightedObject<DoubleSizePlantType>> getPossibleTypes();

    /**
     * Gets the number of plants to create per chunk.
     * 
     * @return The amount
     */
    VariableAmount getPlantsPerChunk();

    /**
     * Gets the number of plants to create per chunk, cannot be negative.
     * 
     * @param count The new amount
     */
    void setPlantsPerChunk(VariableAmount count);

    /**
     * A builder for constructing {@link DoublePlant} populators.
     */
    interface Builder {

        /**
         * Sets which plant types may be spawned in by this populator.
         * 
         * @param types Possible types
         * @return This builder, for chaining
         */
        Builder types(WeightedObject<DoubleSizePlantType>... types);

        /**
         * Sets which plant types may be spawned in by this populator.
         * 
         * @param types Possible types
         * @return This builder, for chaining
         */
        Builder types(Collection<WeightedObject<DoubleSizePlantType>> types);

        /**
         * Sets the number of plants to create, cannot be negative.
         * 
         * @param count The new base amount
         * @return This builder, for chaining
         */
        Builder perChunk(VariableAmount count);

        /**
         * Resets this builder to the default values.
         * 
         * @return This builder, for chaining
         */
        Builder reset();

        /**
         * Builds a new instance of a {@link DoublePlant} populator with the
         * settings set within the builder.
         * 
         * @return A new instance of the populator
         * @throws IllegalStateException If there are any settings left unset
         *         which do not have default values
         */
        DoublePlant build() throws IllegalStateException;

    }

}
