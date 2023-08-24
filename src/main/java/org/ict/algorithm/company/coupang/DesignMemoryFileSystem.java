package org.ict.algorithm.company.coupang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sniper
 * @date 19 Jul 2023
 * LC588, Medium
 */
public class DesignMemoryFileSystem {

    class MemoryFileSystemV1 {

        private List<FileNode> files;

        public MemoryFileSystemV1() {
            this.files = new ArrayList<>();
        }

        public void createFile(String fileName, byte[] data) {
            FileNode fileNode = new FileNode(fileName, data);
            this.files.add(fileNode);
        }

        public byte[] readFile(String fileName) {
            for (FileNode fileNode : this.files) {
                if (fileNode.fileName.equals(fileName)) {
                    return fileNode.data;
                }
            }
            return null;
        }

        public void deleteFile(String fileName) {
            for (int i = 0; i < this.files.size(); i++) {
                if (this.files.get(i).fileName.equals(fileName)) {
                    this.files.remove(i);
                    return;
                }
            }
        }
    }

    static class FileNode {
        String fileName;
        byte[] data;

        public FileNode(String fileName, byte[] data) {
            this.fileName = fileName;
            this.data = data;
        }
    }

    class MemoryFileSystemV2 {

        private final ConcurrentHashMap<String, byte[]> files;
        private final ReentrantReadWriteLock lock;

        public MemoryFileSystemV2() {
            this.files = new ConcurrentHashMap<>();
            this.lock = new ReentrantReadWriteLock();
        }

        public void createFile(String fileName, byte[] data) {
            this.lock.writeLock().lock();
            try {
                this.files.put(fileName, data);
            } finally {
                this.lock.writeLock().unlock();
            }
        }

        public byte[] readFile(String fileName) {
            this.lock.readLock().lock();
            try {
                return this.files.get(fileName);
            } finally {
                this.lock.readLock().unlock();
            }
        }

        public void deleteFile(String fileName) {
            this.lock.writeLock().lock();
            try {
                this.files.remove(fileName);
            } finally {
                this.lock.writeLock().unlock();
            }
        }
    }

    /**
     * This memory-level file system is implemented using a hash map.
     * The hash map maps file names to byte arrays.
     * The createFile() method creates a new file with the specified name and data.
     * The readFile() method reads the data from the specified file.
     * The deleteFile() method deletes the specified file.
     *
     * This is just a simple implementation of a memory-level file system.
     * There are many other ways to implement this,
     * and the specific implementation will depend on the specific needs of the application.
     */
    class MemoryFileSystem {

        private Map<String, byte[]> files;

        public MemoryFileSystem() {
            this.files = new HashMap<>();
        }

        public void createFile(String fileName, byte[] data) {
            this.files.put(fileName, data);
        }

        public byte[] readFile(String fileName) {
            return this.files.get(fileName);
        }

        public void deleteFile(String fileName) {
            this.files.remove(fileName);
        }
    }
}
