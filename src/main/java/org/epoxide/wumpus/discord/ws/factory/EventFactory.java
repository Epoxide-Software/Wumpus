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
public class EventFactory implements IAnnotationFactory {

    private static final Map<String, Class> EVENTS = new HashMap<>();

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return DiscordEvent.class;
    }

    @Override
    public void handleClass(Class clazz) {
        DiscordEvent event = (DiscordEvent) FactoryBus.getAnnoationFromArray(clazz.getAnnotations(), this);
        EventFactory.EVENTS.put(event.name(), clazz);
    }

    @Override
    public List<AnnotationTarget> getTargets() {
        return Collections.singletonList(AnnotationTarget.CLASS);
    }

    public static Class getEvent(String eventName) {
        return EventFactory.EVENTS.get(eventName);
    }
}
