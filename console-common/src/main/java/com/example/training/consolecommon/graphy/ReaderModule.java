package com.example.training.consolecommon.graphy;

import com.example.training.consolecommon.handler.Reader;
import com.example.training.consolecommon.handler.impl.ReaderImpl;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

public class ReaderModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(Reader.class, SingletonFactory.of(this::createReader));
    }

    protected Reader createReader(Linker linker) {
        return new ReaderImpl(System.in);
    }
}
