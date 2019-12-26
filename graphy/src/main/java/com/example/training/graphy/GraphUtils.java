package com.example.training.graphy;

import com.example.training.graphy.linker.InMemoryLinker;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GraphUtils {

    static ObjectGraph graphy(@NonNull Set<Module> modules) {
        final Linker linker = new InMemoryLinker();
        modules.forEach(module -> module.configure(linker));
        onExit(linker::closeAll);
        return new ObjectGraph(linker);
    }

    private static void onExit(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }
}
