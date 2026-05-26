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
package pl.exsio.nestedj.delegate.query.jpa;

import pl.exsio.nestedj.config.jpa.JpaNestedNodeRepositoryConfiguration;
import pl.exsio.nestedj.delegate.query.NestedNodeRemovingQueryDelegate;
import pl.exsio.nestedj.ex.InvalidNodeException;
import pl.exsio.nestedj.model.NestedNode;
import pl.exsio.nestedj.model.NestedNodeInfo;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Optional;
import static pl.exsio.nestedj.model.NestedNode.*;

public class JpaNestedNodeRemovingQueryDelegate<ID extends Serializable, N extends NestedNode<ID>> extends JpaNestedNodeQueryDelegate<ID, N> implements NestedNodeRemovingQueryDelegate<ID, N> {

    private final static Long UPDATE_INCREMENT_BY = 2L;

    public JpaNestedNodeRemovingQueryDelegate(JpaNestedNodeRepositoryConfiguration<ID, N> configuration) {
        super(configuration);
    }

    @Override
    public void setNewParentForDeletedNodesChildren(NestedNodeInfo<ID> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void performSingleDeletion(NestedNodeInfo<ID> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Optional<ID> findNodeParentId(NestedNodeInfo<ID> node) {
        if (node.getLevel() > 0) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ID> select = cb.createQuery(idClass);
            Root<N> root = select.from(nodeClass);
            select.select(root.get(ID)).where(getPredicates(cb, root, cb.lessThan(root.get(LEFT), node.getLeft()), cb.greaterThan(root.get(RIGHT), node.getRight()), cb.equal(root.<Long>get(LEVEL), node.getLevel() - 1)));
            try {
                return Optional.of(entityManager.createQuery(select).setMaxResults(1).getSingleResult());
            } catch (NoResultException ex) {
                throw new InvalidNodeException(String.format("Couldn't find node's parent, although its level is greater than 0. It seems the tree is malformed: %s", node));
            }
        }
        return Optional.empty();
    }

    @Override
    public void decrementSideFieldsBeforeSingleNodeRemoval(Long from, String field) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void pushUpDeletedNodesChildren(NestedNodeInfo<ID> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void decrementSideFieldsAfterSubtreeRemoval(Long from, Long delta, String field) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void performBatchDeletion(NestedNodeInfo<ID> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void decrementSideFields(Long from, Long delta, String field) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<N> update = cb.createCriteriaUpdate(nodeClass);
        Root<N> root = update.from(nodeClass);
        update.set(root.<Long>get(field), cb.diff(root.get(field), delta)).where(getPredicates(cb, root, cb.greaterThan(root.get(field), from)));
        entityManager.createQuery(update).executeUpdate();
    }
}
