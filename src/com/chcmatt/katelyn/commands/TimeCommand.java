package com.chcmatt.katelyn.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.Colors;
public class TimeCommand extends ListenerAdapter {
        public void onMessage(MessageEvent event) {
                if (event.getMessage().equals(".time"))
                        event.respond(Colors.BOLD + "The current time is: " + Colors.BOLD + new Date());
        }
}