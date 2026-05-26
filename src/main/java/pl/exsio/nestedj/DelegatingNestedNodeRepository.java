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
package pl.exsio.nestedj;

import pl.exsio.nestedj.delegate.*;
import pl.exsio.nestedj.ex.InvalidNodeException;
import pl.exsio.nestedj.ex.InvalidParentException;
import pl.exsio.nestedj.ex.RepositoryLockedException;
import pl.exsio.nestedj.model.NestedNode;
import pl.exsio.nestedj.model.NestedNodeInfo;
import pl.exsio.nestedj.model.Tree;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
public class DelegatingNestedNodeRepository<ID extends Serializable, N extends NestedNode<ID>> implements NestedNodeRepository<ID, N> {

    private final NestedNodeInserter<ID, N> inserter;

    private final NestedNodeMover<ID, N> mover;

    private final NestedNodeRemover<ID, N> remover;

    private final NestedNodeRetriever<ID, N> retriever;

    private final NestedNodeRebuilder<ID, N> rebuilder;

    private final Lock<ID, N> lock;

    private boolean allowNullableTreeFields = false;

    public DelegatingNestedNodeRepository(NestedNodeMover<ID, N> mover, NestedNodeRemover<ID, N> remover, NestedNodeRetriever<ID, N> retriever, NestedNodeRebuilder<ID, N> rebuilder, NestedNodeInserter<ID, N> inserter, Lock<ID, N> lock) {
        this.inserter = inserter;
        this.mover = mover;
        this.remover = remover;
        this.retriever = retriever;
        this.rebuilder = rebuilder;
        this.lock = lock;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsFirstChildOf(N node, N parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsLastChildOf(N node, N parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsNextSiblingOf(N node, N parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsPrevSiblingOf(N node, N parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void insertOrMove(N node, N parent, NestedNodeHierarchyManipulator.Mode mode) {
        if (parent.getId() == null) {
            throw new InvalidParentException("Cannot insert or move to a parent that has null id");
        }
        Optional<NestedNodeInfo<ID>> parentInfo = retriever.getNodeInfo(parent.getId());
        if (!parentInfo.isPresent()) {
            throw new InvalidParentException(String.format("Cannot insert or move to non existent parent. Parent id: %s", parent.getId()));
        }
        if (node.getId() != null) {
            Optional<NestedNodeInfo<ID>> nodeInfo = retriever.getNodeInfo(node.getId());
            if (nodeInfo.isPresent()) {
                boolean nodeInfoValid = isNodeInfoValid(nodeInfo.get());
                if (nodeInfoValid) {
                    this.mover.move(nodeInfo.get(), parentInfo.get(), mode);
                } else if (allowNullableTreeFields) {
                    this.inserter.insert(node, parentInfo.get(), mode);
                } else {
                    throw new InvalidNodeException(String.format("Current configuration doesn't allow nullable tree fields: %s", nodeInfo.get()));
                }
            } else {
                this.inserter.insert(node, parentInfo.get(), mode);
            }
        } else {
            this.inserter.insert(node, parentInfo.get(), mode);
        }
    }

    private boolean isNodeInfoValid(NestedNodeInfo<ID> nodeInfo) {
        return (nodeInfo.getLeft() != null && nodeInfo.getRight() != null && nodeInfo.getLeft() > 0 && nodeInfo.getRight() > 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSingle(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSubtree(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<N> getTreeAsList(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<N> getChildren(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<N> getParent(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<N> getPrevSibling(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<N> getNextSibling(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tree<ID, N> getTree(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<N> getParents(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rebuildTree() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyTree() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsFirstRoot(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAsLastRoot(N node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean differentNodes(N node, N firstRoot) {
        return !firstRoot.getId().equals(node.getId());
    }

    private void insertAsFirstNode(N node) {
        inserter.insertAsFirstNode(node);
    }

    public boolean isAllowNullableTreeFields() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAllowNullableTreeFields(boolean allowNullableTreeFields) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void lockNode(N node, TreeModifier modifier) {
        if (!lock.lockNode(node)) {
            throw new RepositoryLockedException(String.format("Nested Node Repository is locked for Node %s. Try again later.", node));
        }
        try {
            modifier.modifyTree();
        } finally {
            lock.unlockNode(node);
        }
    }

    private void lockRepository(TreeModifier modifier) {
        if (!lock.lockRepository()) {
            throw new RepositoryLockedException("Nested Node Repository is locked. Try again later.");
        }
        try {
            modifier.modifyTree();
        } finally {
            lock.unlockRepository();
        }
    }

    private interface TreeModifier {

        void modifyTree();
    }
}
