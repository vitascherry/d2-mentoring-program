package com.example.training.totodemo;

import com.example.training.consolecommon.handler.Handler;
import com.example.training.graphy.Graphied;
import com.example.training.graphy.ObjectGraph;
import com.example.training.graphy.annotation.WithModules;
import com.example.training.totodemo.graphy.TotoDemoAnnotationDrivenModule;

@WithModules(TotoDemoAnnotationDrivenModule.class)
public class GraphyAnnotationDrivenBootstrap implements Graphied {

    private final Handler handler;

    private GraphyAnnotationDrivenBootstrap() {
        final ObjectGraph graph = getObjectGraph();
        this.handler = graph.getInstance(Handler.class);
    }

    public static void main(String[] args) {
        GraphyAnnotationDrivenBootstrap app = new GraphyAnnotationDrivenBootstrap();
        app.handler.handle(args);
    }
}
