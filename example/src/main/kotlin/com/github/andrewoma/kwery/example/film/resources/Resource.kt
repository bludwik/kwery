/*
 * Copyright (c) 2015 Andrew O'Malley
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

package com.github.andrewoma.kwery.example.film.resources

import com.github.andrewoma.kwery.fetcher.GraphFetcher
import com.github.andrewoma.kwery.fetcher.Node
import com.github.andrewoma.kwery.mapper.Column

interface Resource {
    val fetcher: GraphFetcher

    fun <T> parameters(vararg parameters: Pair<Column<T, *>, Any?>): Map<Column<T, *>, Any?> {
        return parameters.toList().toMap().filter { it.value != null }
    }

    fun <T> List<T>.fetch(root: String?): List<T> {
        if (root == null) return this
        return fetcher.fetch(this, Node.parse(root))
    }

    fun <T> T.fetch(root: String?): T {
        if (root == null) return this
        return fetcher.fetch(this, Node.parse(root))
    }
}