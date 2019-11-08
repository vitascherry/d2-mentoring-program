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
        return new ObjectGraph(linker);
    }
}
