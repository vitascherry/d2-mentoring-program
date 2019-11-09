package com.example.training.consolecommon.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

public class ConsoleCommonModule implements Module {

    @Override
    public void configure(Linker linker) {
        new ReaderModule().configure(linker);
        new PrinterModule().configure(linker);
    }
}
