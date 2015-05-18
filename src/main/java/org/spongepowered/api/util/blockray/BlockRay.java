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
package org.spongepowered.api.util.blockray;

import com.flowpowered.math.vector.Vector3d;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.util.Collect;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockRay {

    private final Location startLocation;
    private final Vector3d startDirection;
    private final BlockRayFilter filter;

    private boolean hasFinished = false;
    private Location endLocation;

    private List<Location> computedIntersecting = new ArrayList<Location>();

    private Iterator<Location> intersecting = new UnmodifiableIterator<Location>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Location next() {
            return null;
        }
    };

    private List<Location> computedDiscrete = new ArrayList<Location>();

    private Iterator<Location> discrete = new UnmodifiableIterator<Location>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Location next() {
            return null;
        }
    };

    public BlockRay(Location startLocation, Vector3d startDirection, BlockRayFilter filter) {
        this.startLocation = startLocation;
        this.startDirection = startDirection;
        this.filter = filter;
    }

    // traces this ray for one step
    private void trace() {

    }

    private void traceUntilIntersecting() {

    }

    private void traceUntilDiscrete() {

    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return null;
    }

    public Iterable<Location> getIntersecting() {
        return Iterables.concat(computedIntersecting, Collect.iterableFromIterator(intersecting));
    }

    public Iterable<Location> getDiscrete() {
        return Iterables.concat(computedIntersecting, Collect.iterableFromIterator(discrete));
    }

    public static BlockRay fromEntityRotation(Entity entity, BlockRayFilter filter) {
        return new BlockRay(entity.getLocation(), entity.getRotation(), filter);
    }

    public static BlockRay fromEntityRotation(Entity entity) {
        return fromEntityRotation(entity, BlockRayFilter.ONLY_AIR);
    }

}
