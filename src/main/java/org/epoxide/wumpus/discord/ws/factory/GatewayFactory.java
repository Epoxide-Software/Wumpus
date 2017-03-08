package org.epoxide.wumpus.discord.ws.factory;

import org.epoxide.wumpus.factory.AnnotationFactory;
import org.epoxide.wumpus.factory.AnnotationTarget;
import org.epoxide.wumpus.factory.FactoryBus;
import org.epoxide.wumpus.factory.IAnnotationFactory;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AnnotationFactory
public class GatewayFactory implements IAnnotationFactory {
    private static final Map<Integer, Class> GATEWAY = new HashMap<>();

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return DiscordGateway.class;
    }

    @Override
    public void handleClass(Class clazz) {
        DiscordGateway event = (DiscordGateway) FactoryBus.getAnnoationFromArray(clazz.getAnnotations(), this);
        GatewayFactory.GATEWAY.put(event.opcode(), clazz);
    }

    @Override
    public List<AnnotationTarget> getTargets() {
        return Collections.singletonList(AnnotationTarget.CLASS);
    }

    public static Class getGateway(int op) {
        return GatewayFactory.GATEWAY.get(op);
    }
}
