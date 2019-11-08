package com.example.training.totodemo;

import com.example.training.graphy.Graphied;
import com.example.training.graphy.ObjectGraph;
import com.example.training.graphy.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.totodemo.graphy.TotoDemoModule;

@WithModules(TotoDemoModule.class)
public class GraphyBootstrap implements Graphied {

    private final Handler handler;

    public GraphyBootstrap() {
        final ObjectGraph graph = getObjectGraph();
        this.handler = graph.getInstance(Handler.class);
    }

    public static void main(String[] args) {
        GraphyBootstrap app = new GraphyBootstrap();
        app.handler.handle(args);
    }
}
