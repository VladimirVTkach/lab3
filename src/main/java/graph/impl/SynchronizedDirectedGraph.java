package graph.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedDirectedGraph<T> extends NonSynchronizedDirectedGraph<T> {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void addVertex(T key) {
        lock.writeLock().lock();
        super.addVertex(key);
        lock.writeLock().unlock();
    }

    @Override
    public void removeVertex(T key) {
        lock.writeLock().lock();
        super.removeVertex(key);
        lock.writeLock().unlock();
    }

    @Override
    public void addEdge(T sourceKey, T destinationKey) {
        lock.writeLock().lock();
        super.addEdge(sourceKey, destinationKey);
        lock.writeLock().unlock();
    }

    @Override
    public void removeEdge(T sourceKey, T destinationKey) {
        lock.writeLock().lock();
        super.removeEdge(sourceKey, destinationKey);
        lock.writeLock().unlock();
    }

    @Override
    public boolean containsVertex(T key) {
        lock.readLock().lock();
        boolean result = super.containsVertex(key);
        lock.readLock().unlock();
        return result;
    }

    @Override
    public boolean containsEdge(T sourceKey, T destinationKey) {
        lock.readLock().lock();
        boolean result = super.containsEdge(sourceKey, destinationKey);
        lock.readLock().unlock();
        return result;
    }

    @Override
    public Optional<List<T>> findPath(T sourceKey, T destinationKey) {
        lock.readLock().lock();
        Optional<List<T>> result = super.findPath(sourceKey, destinationKey);
        lock.readLock().unlock();
        return result;
    }

    @Override
    public int getVerticesCount() {
        lock.readLock().lock();
        int result = super.getVerticesCount();
        lock.readLock().unlock();
        return result;
    }

    @Override
    public int getEdgesCount() {
        lock.readLock().lock();
        int result = super.getEdgesCount();
        lock.readLock().unlock();
        return result;
    }
}
