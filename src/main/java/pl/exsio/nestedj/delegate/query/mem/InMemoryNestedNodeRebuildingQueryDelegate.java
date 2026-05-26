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
package pl.exsio.nestedj.delegate.query.mem;

import pl.exsio.nestedj.config.mem.InMemoryNestedNodeRepositoryConfiguration;
import pl.exsio.nestedj.delegate.query.NestedNodeRebuildingQueryDelegate;
import pl.exsio.nestedj.ex.InvalidNodeException;
import pl.exsio.nestedj.model.NestedNode;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static pl.exsio.nestedj.model.NestedNode.ID;
import static pl.exsio.nestedj.model.NestedNode.PARENT_ID;

public class InMemoryNestedNodeRebuildingQueryDelegate<ID extends Serializable, N extends NestedNode<ID>> extends InMemoryNestedNodeQueryDelegate<ID, N> implements NestedNodeRebuildingQueryDelegate<ID, N> {

    public InMemoryNestedNodeRebuildingQueryDelegate(InMemoryNestedNodeRepositoryConfiguration<ID, N> configuration) {
        super(configuration);
    }

    @Override
    public void destroyTree() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public N findFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resetFirst(N first) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<N> getSiblings(ID first) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<N> getChildren(N parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private Comparator<N> getIdComparator() {
        return (o1, o2) -> {
            if (o1.getId() instanceof Comparable) {
                return ((Comparable) o1.getId()).compareTo(o2.getId());
            } else {
                return Integer.compare(o1.getId().hashCode(), o2.getId().hashCode());
            }
        };
    }
}
