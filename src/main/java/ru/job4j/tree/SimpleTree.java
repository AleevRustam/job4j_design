package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentOptional = findBy(parent);
        Optional<Node<E>> childOptional = findBy(child);
        if (parentOptional.isPresent() && childOptional.isEmpty()) {
            Node<E> parentNode = parentOptional.get();
            if (parentNode.children.stream().noneMatch((node) -> node.value.equals(child))) {
                parentNode.children.add(new Node<>(child));
                return true;
            }

        }
        return false;

    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((node) -> node.value.equals(value));
    }

    public boolean isBinary() {
        return findByPredicate((node) -> node.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                return Optional.of(element);
            }
            data.addAll(element.children);
        }
        return Optional.empty();
    }
}
