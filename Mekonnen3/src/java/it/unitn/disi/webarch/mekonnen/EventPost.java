/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.mekonnen;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ephrem
 */
public class EventPost {

    private int id;
    private String event;

    static List<EventPost> events = new LinkedList<EventPost>();

    public EventPost(int maxId, String event) {
        this.id = maxId;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public static void addEvents(EventPost event) {
        events.add(event);

    }

    public static List<EventPost> getEvents() {
        return events;
    }

}
