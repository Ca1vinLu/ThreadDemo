    // IBookManager.aidl
package me.lyz.threaddemo.binder;

// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void getBookList();
    void addBook();
}
