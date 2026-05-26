/*
 *  The MIT License
 *
 *  Copyright (c) 2019 eXsio.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 *  rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 *  the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 *  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package pl.exsio.nestedj.delegate.control;

import pl.exsio.nestedj.delegate.NestedNodeRetriever;
import pl.exsio.nestedj.delegate.query.NestedNodeRetrievingQueryDelegate;
import pl.exsio.nestedj.model.InMemoryTree;
import pl.exsio.nestedj.model.NestedNode;
import pl.exsio.nestedj.model.NestedNodeInfo;
import pl.exsio.nestedj.model.Tree;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class QueryBasedNestedNodeRetriever<ID extends Serializable, N extends NestedNode<ID>> implements NestedNodeRetriever<ID, N> {

    private final NestedNodeRetrievingQueryDelegate<ID, N> queryDelegate;

    public QueryBasedNestedNodeRetriever(NestedNodeRetrievingQueryDelegate<ID, N> queryDelegate) {
        this.queryDelegate = queryDelegate;
    }

    @Override
    public Tree<ID, N> getTree(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<N> getTreeAsList(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<N> getChildren(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<N> getParent(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<N> getParents(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<N> getPrevSibling(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<N> getNextSibling(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<NestedNodeInfo<ID>> getNodeInfo(ID nodeId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<N> findFirstRoot() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<N> findLastRoot() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
