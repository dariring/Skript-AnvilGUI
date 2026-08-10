package org.derewah.skriptanvilgui.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import org.bukkit.entity.Player;
import org.derewah.skriptanvilgui.anvilgui.Anvil;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClick;

public class EvtOnAnvilGUIClick {

    static{
        Skript.registerEvent("On AnvilGUI Click", SimpleEvent.class, BridgeAnvilClick.class, "anvil gui click");
        EventValues.registerEventValue(BridgeAnvilClick.class, String.class,
                bridgeAnvilClick -> bridgeAnvilClick.getAnvilState().getText(), EventValues.TIME_NOW);
        EventValues.registerEventValue(BridgeAnvilClick.class, Player.class,
                bridgeAnvilClick -> bridgeAnvilClick.getAnvilState().getPlayer(), EventValues.TIME_NOW);
        EventValues.registerEventValue(BridgeAnvilClick.class, Integer.class,
                BridgeAnvilClick::getAnvilSlot, EventValues.TIME_NOW);
        EventValues.registerEventValue(BridgeAnvilClick.class, Anvil.class,
                BridgeAnvilClick::getAnvil, EventValues.TIME_NOW);
    }

}
