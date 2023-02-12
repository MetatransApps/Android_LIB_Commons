package org.metatrans.commons.events;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;


public interface IEventSender {

    void init(Application_Base app);

    void send(final IEvent_Base event);
}
