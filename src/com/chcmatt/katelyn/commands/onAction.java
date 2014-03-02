package com.chcmatt.katelyn.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
public class onAction extends ListenerAdapter {
        public void onAction(ActionEvent event) throws Exception {
                event.respond(event.getUser().getNick() + " is thinking!");
        }
}